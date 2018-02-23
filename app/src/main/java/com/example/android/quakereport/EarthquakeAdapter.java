package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pramod on 23/12/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";
    public EarthquakeAdapter(Activity context, List<Earthquake> earthquake) {
        super(context, 0, earthquake);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list, parent, false);
        }

        Earthquake currentItem = getItem(position);

        String magnitude = formatDecimal(currentItem.getMagnitude());
        TextView magTextView = listView.findViewById(R.id.magnitude);


        int felt = currentItem.getFelt();
        TextView feltTextView = listView.findViewById(R.id.felt);
        String didFelt = getContext().getString(R.string.felt) + felt;
        feltTextView.setText(didFelt);

        GradientDrawable gd = (GradientDrawable) magTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentItem.getMagnitude());
        gd.setColor(magnitudeColor);

        magTextView.setText(magnitude);

        String primaryLocation;
        String locationOffset;
        String originalLocation = currentItem.getLocation();

        if(originalLocation.contains(LOCATION_SEPARATOR)){
            String [] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }
        else
        {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView offsetView = listView.findViewById(R.id.near);
        offsetView.setText(locationOffset);
        TextView primaryView = listView.findViewById(R.id.location);
        primaryView.setText(primaryLocation);

        Date dateRaw = new Date(currentItem.getTimeInMilliseconds());

        String formattedDate = formatDate(dateRaw);
        TextView date = listView.findViewById(R.id.date);
        date.setText(formattedDate);

        String formattedTime = formatTime(dateRaw);
        TextView time = listView.findViewById(R.id.time);
        time.setText(formattedTime);



        return listView;
    }

    private String formatDate(Date date) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("LLL dd, yyyy");
        return simpleDate.format(date);
    }

    private String formatTime(Date date) {
        SimpleDateFormat simpleTime = new SimpleDateFormat("hh:mm a");
        return simpleTime.format(date);
    }

    private String formatDecimal(double d) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(d);
    }

    private int getMagnitudeColor(double mag) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(mag);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
}
