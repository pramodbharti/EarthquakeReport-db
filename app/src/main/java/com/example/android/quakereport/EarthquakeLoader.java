package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by pramod on 7/1/18.
 */

/**
 * Loads a list of earthquakes by using an AsyncTask to perform
 * the network request to the given URL.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /**Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    /** Query Url */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     * @param context of the activity
     * @param url to load from
     */
    public EarthquakeLoader(Context context, String url)
    {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     * @return Earthquake list data.
     */
    @Override
    public List<Earthquake> loadInBackground() {
        if(mUrl==null)
            return null;
        return QueryUtils.fetchEarthquakeData(mUrl);
    }

}
