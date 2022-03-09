package com.example.imagegallery.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.imagegallery.ApiClient;
import com.example.imagegallery.ImagesResponse;
import com.example.imagegallery.MyRecycleAdapter;
import com.example.imagegallery.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {

    private List<ImagesResponse> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyRecycleAdapter myRecycleAdapter;


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

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();

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
                    myRecycleAdapter = new MyRecycleAdapter(getContext(),list);
                    recyclerView.setAdapter(myRecycleAdapter);
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

}