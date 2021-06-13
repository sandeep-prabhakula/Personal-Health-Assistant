package com.example.myapplication;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CovidCount extends AppCompatActivity {

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
        adapter.notifyDataSetChanged();
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
                    JSONObject fl = obj.getJSONObject("countryInfo");
                    Log.d("yay!",fl.getString("iso2"));
                    CovidDataModel model = new CovidDataModel(
                                                obj.getString("country"),
                                                obj.getString("active"),
                                                obj.getString("recovered"),
                                                obj.getString("deaths"),
                                                fl.getString("iso2"),
                                                fl.getString("flag"));
                    data.add(model);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            cases.setAdapter(adapter);
        }, error -> {
            pd.dismiss();
            if(error instanceof NetworkError){
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(jsonArrayRequest);
    }
}