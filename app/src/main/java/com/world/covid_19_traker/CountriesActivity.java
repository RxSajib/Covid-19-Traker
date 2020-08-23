package com.world.covid_19_traker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.world.covid_19_traker.Model.CountryDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountriesActivity extends AppCompatActivity {

    private ListView countrylist;
    private EditText search_name;
    public static List<CountryDetails> countryDetailsList = new ArrayList<>();
    private CountryDetails countryDetails;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);


        countrylist = findViewById(R.id.CuntryListID);

        countrylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), CountryInfo.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Position", i);
                startActivity(intent);
            }
        });

        search_name = findViewById(R.id.SearchByCountryes);
        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fatch_data();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void fatch_data(){
        String uri = DataManager.CountryDetailsAPI;

        StringRequest request = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String Countryname = jsonObject.getString(DataManager.COUNTRYNAME);
                        String Cases = jsonObject.getString(DataManager.COUNTRY_CASES);
                        String TodayCases = jsonObject.getString(DataManager.COUNTRY_TODAYCASES);
                        String Deaths = jsonObject.getString(DataManager.COUNTRY_DEATHS);
                        String Today_Deaths = jsonObject.getString(DataManager.COUNTRY_TODAYDEATHS);
                        String Recovered = jsonObject.getString(DataManager.COUNTRY_RECOVERED);
                        String Today_Recovered = jsonObject.getString(DataManager.COUNTRY_TODAY_RECOVERED);
                        String Active = jsonObject.getString(DataManager.COUNTRY_ACTIVE);
                        String Tests = jsonObject.getString(DataManager.COUNTRY_TESTS);

                        JSONObject jsonObject1 = jsonObject.getJSONObject(DataManager.COUNTRY_INFO);
                        String imageuri = jsonObject1.getString(DataManager.FLAGCOUNTRY);

                        countryDetails = new CountryDetails(imageuri, Countryname, Cases, TodayCases, Recovered, Active, Tests);
                        countryDetailsList.add(countryDetails);
                    }


                    countryAdapter = new CountryAdapter(CountriesActivity.this, countryDetailsList);
                    countrylist.setAdapter(countryAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(CountriesActivity.this);
        requestQueue.add(request);
    }
}