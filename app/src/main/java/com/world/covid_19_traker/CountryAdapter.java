package com.world.covid_19_traker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;
import com.world.covid_19_traker.Model.CountryDetails;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<CountryDetails> {

    private List<CountryDetails> countryDetailsList;
    private Context context;

    public CountryAdapter(Context context, List<CountryDetails> countryDetailsList) {
        super(context, R.layout.country_layout, countryDetailsList);

        this.countryDetailsList = countryDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_layout, null, true);
        ImageView imageView = view.findViewById(R.id.CountryImage);
        MaterialTextView countryname = view.findViewById(R.id.Countryname);

        Picasso.with(context).load(countryDetailsList.get(position).getFlag()).placeholder(R.drawable.image_placeholder).into(imageView);
        countryname.setText(countryDetailsList.get(position).getCountryname());

        return view;

    }
}
