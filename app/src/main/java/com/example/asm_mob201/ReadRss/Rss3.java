package com.example.asm_mob201.ReadRss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.asm_mob201.DOM.XMLDOMParser;
import com.example.asm_mob201.R;
import com.example.asm_mob201.WebView1234.WebView3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Rss3 extends AppCompatActivity {
    private ArrayList<String> arrTitle, arrLink;
    private ArrayAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss3);
        //
        ListView lstTieuDe = findViewById(R.id.listViewNews);
        progressBar = findViewById(R.id.progressBar);

        arrTitle = new ArrayList<>();
        arrLink = new ArrayList<>();

        progressBar.setVisibility(View.VISIBLE);
        new ReadRSS().execute("https://vnexpress.net/rss/giai-tri.rss");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrTitle);
        lstTieuDe.setAdapter(adapter);

        lstTieuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Rss3.this, WebView3.class);
                intent.putExtra("link3", arrLink.get(i));
                startActivity(intent);
            }
        });

    }
    private class ReadRSS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String title = "";
            for (int i = 0; i < ((NodeList) nodeList).getLength(); i++) {
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