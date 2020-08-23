package com.world.covid_19_traker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.text.MeasuredText;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textview.MaterialTextView;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private PieChart pieChart;
    private MaterialTextView cases, recover, cricitial, active, todaycase, todaydeathes;
    private SimpleArcLoader arcLoader;
    private RelativeLayout countriesbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = findViewById(R.id.piechart);
        arcLoader = findViewById(R.id.loader);
        cases = findViewById(R.id.TotlaCasesText);
        recover = findViewById(R.id.TotalRecoverText);
        cricitial = findViewById(R.id.TotalCritical);
        active = findViewById(R.id.TotalActive);
        todaycase = findViewById(R.id.TotalTodayCases);
        todaydeathes = findViewById(R.id.TotalDeaths);

        countriesbutton = findViewById(R.id.TrackCountriesButtonID);
        countriesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CountriesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        get_data_mainpages();
    }

    private void get_data_mainpages(){

        arcLoader.start();
        String uri = DataManager.URI;

        final StringRequest request = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response.toString());
                    cases.setText(object.getString(DataManager.CASES));
                    recover.setText(object.getString(DataManager.RECOVERED));
                    cricitial.setText(object.getString(DataManager.CRITICAL));
                    active.setText(object.getString(DataManager.ACTIVE));
                    todaycase.setText(object.getString(DataManager.TODAYCASES));
                    todaydeathes.setText(object.getString(DataManager.TODAYDEATHS));

                    pieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(cases.getText().toString()), Color.parseColor("#ffeb3b")));
                    pieChart.addPieSlice(new PieModel("Recover", Integer.parseInt(recover.getText().toString()), Color.parseColor("#2979ff")));
                    pieChart.addPieSlice(new PieModel("Deaths", Integer.parseInt(todaydeathes.getText().toString()), Color.parseColor("#f44336")));
                    pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(active.getText().toString()), Color.parseColor("#006064")));
                    pieChart.startAnimation();
                    arcLoader.stop();
                } catch (JSONException e) {
                    e.printStackTrace();
                    arcLoader.stop();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                arcLoader.stop();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
}