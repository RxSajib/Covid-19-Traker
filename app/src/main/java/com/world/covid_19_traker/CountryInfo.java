package com.world.covid_19_traker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;
import com.world.covid_19_traker.Model.CountryDetails;

public class CountryInfo extends AppCompatActivity {

    private int position;
    private MaterialTextView cases, today_cases, recover, active, test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        Intent intent = getIntent();
        position = intent.getIntExtra("Position", 0);

        cases = findViewById(R.id.CasesID);
        today_cases = findViewById(R.id.TodayCases);
        recover = findViewById(R.id.Recover);
        active = findViewById(R.id.Active);
        test = findViewById(R.id.Teast);

        getSupportActionBar().setTitle(CountriesActivity.countryDetailsList.get(position).getCountryname());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        get_data();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void get_data(){
        cases.setText(CountriesActivity.countryDetailsList.get(position).getCases());
        today_cases.setText(CountriesActivity.countryDetailsList.get(position).getTodaycases());
        recover.setText(CountriesActivity.countryDetailsList.get(position).getRecover());
        active.setText(CountriesActivity.countryDetailsList.get(position).getActive());
        test.setText(CountriesActivity.countryDetailsList.get(position).getTest());
    }


}