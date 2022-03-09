package com.example.imagegallery.fragment;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.imagegallery.ApiClient;
import com.example.imagegallery.ImagesResponse;
import com.example.imagegallery.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {

    private List<ImagesResponse> list = new ArrayList<>();
    private GridView gridView;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        getAllImages();
        gridView = view.findViewById(R.id.gridview);
        list = new ArrayList<>();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view.getContext(), "Item" + i, Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("key", list.get(i).getUrl());// Put anything what you want

                FullScreenFragment fullScreenFragment = new FullScreenFragment();
                fullScreenFragment.setArguments(bundle);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.FrameLayout, fullScreenFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    public void getAllImages() {

        Call<List<ImagesResponse>> imageResponse = ApiClient.getInterface().getAllImage();

        imageResponse.enqueue(new Callback<List<ImagesResponse>>() {
            @Override
            public void onResponse(Call<List<ImagesResponse>> call, Response<List<ImagesResponse>> response) {

                if (response.isSuccessful()) {
                    String massage = "Request Success";
                    list = response.body();

                    CustomAdapter adapter = new CustomAdapter(list, getContext());
                    gridView.setAdapter(adapter);
                    Toast.makeText(getContext(), massage, Toast.LENGTH_SHORT).show();
                } else {
                    String massage = "an error occurred. try again";
                    Toast.makeText(getContext(), "Error With" + massage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ImagesResponse>> call, Throwable t) {
                String massage = t.getLocalizedMessage();
                Log.d("Tag", massage);
                Toast.makeText(getContext(), "Error With" + massage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdapter extends BaseAdapter {
        public List<ImagesResponse> imagesResponseList;
        public Context context;
        public LayoutInflater layoutInflater;

        public CustomAdapter(List<ImagesResponse> imagesResponseList, Context context) {
            this.imagesResponseList = imagesResponseList;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return imagesResponseList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = layoutInflater.inflate(R.layout.item, viewGroup, false);
            }
            TextView name = view.findViewById(R.id.name);
            ImageView imageView = view.findViewById(R.id.imageId);

            String imageUrl = imagesResponseList.get(i).getUrl();
            Log.d("Url", imageUrl);

            name.setText(imagesResponseList.get(i).getAuthor());
            try {
                Glide.with(view.getContext())
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder).into(imageView);
            }catch (Exception e){
                Log.d("Error",e.getMessage().toString());
            }

            return view;
        }
    }
}