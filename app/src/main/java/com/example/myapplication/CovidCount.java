package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CovidCount extends AppCompatActivity implements CovidRecycler.ItemClickListener{
    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(this, "clicked "+adapter.get(position), Toast.LENGTH_SHORT).show();
//        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//        CustomTabsIntent customTabsIntent = builder.build();
//        customTabsIntent.launchUrl(this, Uri.parse("twitter.com"));
    }

    public class BG extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection conn;
            try {
                url = new URL(urls[0]);
                conn = (HttpURLConnection) url.openConnection();
                InputStream in = conn.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
            }
            catch (Exception e){
                e.printStackTrace();
                return "something went wrong";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("apidata",s);
            loadRecyclerViewData();
        }
    }

    String api_url = "https://disease.sh/v3/covid-19/countries/";
    List<CovidDataModel> data;
    CovidRecycler adapter;
    RecyclerView cases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_count);
        cases = findViewById(R.id.StateData);
        BG bg = new BG();
        bg.execute("https://disease.sh/v3/covid-19/countries/");
        data = new ArrayList<>();
        loadRecyclerViewData();
        cases.addItemDecoration(new DividerItemDecoration(cases.getContext(),DividerItemDecoration.VERTICAL));
        cases.setHasFixedSize(true);
        cases.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CovidRecycler(data);
        cases.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnclickListener(this);
    }

    private void loadRecyclerViewData() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading...please wait");
        pd.show();
        RequestQueue rq= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, api_url, null, response -> {
            pd.dismiss();
            for(int i=0;i<response.length();i++){
                try {
                    JSONObject obj = response.getJSONObject(i);
                    Log.d("country Name",obj.getString("country")+" "+
                            obj.getString("active")+" "+
                            obj.getString("recovered")+" "+
                            obj.getString("deaths"));
                    CovidDataModel model = new CovidDataModel(
                                                obj.getString("country"),
                                                obj.getString("active"),
                                                obj.getString("recovered"),
                                                obj.getString("deaths"));
                    data.add(model);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            pd.dismiss();
            Log.d("Aipaye...",error.toString());
        });
        rq.add(jsonArrayRequest);
    }
}