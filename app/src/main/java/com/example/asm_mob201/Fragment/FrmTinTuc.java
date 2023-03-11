package com.example.asm_mob201.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.asm_mob201.DOM.XMLDOMParser;
import com.example.asm_mob201.R;
import com.example.asm_mob201.ReadRss.Rss1;
import com.example.asm_mob201.ReadRss.Rss3;
import com.example.asm_mob201.ReadRss.RssTamSu;
import com.example.asm_mob201.WebView1234.WebView123;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FrmTinTuc extends Fragment {

    private ArrayList<String> arrTitle, arrLink, arrListA;
    private ArrayAdapter adapter;
    ProgressBar progressBar;
    boolean test = false;
    Button btnTinTuc, btnGiaoDuc, btnTheThao;

    public FrmTinTuc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_tin_tuc, container, false);
        ListView listView = view.findViewById(R.id.listViewNews);
        progressBar = view.findViewById(R.id.progressBar);
        btnTinTuc = view.findViewById(R.id.btnTinTuc);
        btnGiaoDuc = view.findViewById(R.id.btnGiaoDuc);
        btnTheThao = view.findViewById(R.id.btnTamSu);

        arrLink = new ArrayList<>();
        arrTitle = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrTitle);
        listView.setAdapter(adapter);
        btnTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String ad1 = "https://vnexpress.net/rss/giao-duc.rss";
//                new ReadRss().execute(ad1);
//                adapter.clear();
                Intent intent = new Intent(getActivity(), Rss1.class);
                startActivity(intent);
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Intent intent = new Intent(getActivity(), WebView123.class);
//                        intent.putExtra("linkNews", arrLink.get(i));
//                        startActivity(intent);
//                    }
//                });

            }
        });
        btnTheThao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String ad2 = "https://vnexpress.net/rss/tam-su.rss";
//                new ReadRss().execute(ad2);
//                adapter.clear();
                    Intent intent = new Intent(getActivity() , Rss3.class);
                    startActivity(intent);
            }
        });
        btnGiaoDuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ad3 = "https://vnexpress.net/rss/the-thao.rss";
                new ReadRss().execute(ad3);
                adapter.clear();
                Intent intent = new Intent(getActivity(), RssTamSu.class);
                startActivity(intent);
            }
        });
        return view;
        //ádsad
        //ádasd
    }

    private class ReadRss extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder builder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return builder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String title = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                arrTitle.add(title);
                arrLink.add(parser.getValue(element, "link"));
            }
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        }
    }
}