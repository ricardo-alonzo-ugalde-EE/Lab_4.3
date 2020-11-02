package org.electricuniverse.lab_43;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 *6.3: We want to be able to notify the Activity when an item is touched so that the Activity creates a fragment
 * to display the details of the tapped item. For this, we need to create a Fragment class that will display the
 * details of the movie selected by the user
 **/
public class MovieDetailFragment extends Fragment
{
    public static MovieDetailFragment newInstance(int i, String t, String y, float r, String d)
    {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putInt("id",i);
        args.putString("title", t);
        args.putString("year", y);
        args.putFloat("rating", r);
        args.putString("description", d);
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    /**
     *6.4: Now implement the onCreateView method. This is where you need to inflate your view hierarchy
     * from the layout as we did in the last lab.
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        final View v = inflater.inflate(R.layout.fragment_movie_detail,container,false);
        ImageView imageView = v.findViewById(R.id.large_image);
        imageView.setImageResource(args.getInt("id"));
        EditText editText = v.findViewById(R.id.title_text);
        editText.setText(args.getString("title"));
        EditText yearText = v.findViewById(R.id.year_text);
        yearText.setText(args.getString("year"));
        RatingBar ratingBar = v.findViewById(R.id.movie_rating);
        ratingBar.setRating(args.getFloat("rating"));
        TextView descriptionText = v.findViewById(R.id.description);
        descriptionText.setText(args.getString("description"));
        return v;
    }

}