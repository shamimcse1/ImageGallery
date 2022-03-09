package com.example.imagegallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.ViewHolderClass> {


    private Context context;
    private List<ImagesResponse> imageList;


    public MyRecycleAdapter(Context context, List<ImagesResponse> imageList) {
        this.context = context;
        this.imageList = imageList;
    }


    public void setMyRecycleAdapter(List<ImagesResponse> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        ViewHolderClass linearViewHolderClass = new ViewHolderClass(view);
        return linearViewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        holder.author.setText(imageList.get(position).getAuthor());
        Glide.with(context).load(imageList.get(position).getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {

        private TextView author;
        private ImageView imageView;


        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageId);
            author = itemView.findViewById(R.id.name);
        }
    }
}
