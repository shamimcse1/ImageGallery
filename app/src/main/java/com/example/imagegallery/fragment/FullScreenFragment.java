package com.example.imagegallery.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.imagegallery.R;
import com.squareup.picasso.Picasso;


public class FullScreenFragment extends Fragment {


    public FullScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_full_screen, container, false);
        Bundle bundle = this.getArguments();
        String url;
        ImageView imageView = view.findViewById(R.id.imageview);
        if (bundle != null) {
            url = bundle.getString("key");
            Glide.with(view.getContext()).load(url)
                    .placeholder(R.drawable.placeholder).into(imageView);
        }

        return view;
    }
}