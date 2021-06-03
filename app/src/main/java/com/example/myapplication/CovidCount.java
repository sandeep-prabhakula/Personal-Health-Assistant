package com.example.myapplication;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CovidCount extends AppCompatActivity implements CovidRecycler.ItemClickListener{
    @Override
    public void onItemClick(View v, int position) {
//        Toast.makeText(this, "clicked "+adapter.get(position), Toast.LENGTH_SHORT).show();
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse("https://twitter.com"));
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
            cases.setAdapter(adapter);
        }, error -> {
            pd.dismiss();
            Log.d("Aipaye...",error.toString());
        });
        rq.add(jsonArrayRequest);
    }
}