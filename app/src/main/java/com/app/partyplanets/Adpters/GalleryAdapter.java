package com.app.partyplanets.Adpters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.DashBoad.FullScreenDaiolg;
import com.app.partyplanets.Data.Galleryimages;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>
{

    private Context context;
    private List<Galleryimages> msubslider = new ArrayList<>();
    public interface onListClickedRowListner
    {
        void onListSelected(int position);

    }
  onListClickedRowListner listner;

    public GalleryAdapter(Context context, List<Galleryimages> msubslider, onListClickedRowListner listner)
    {
        this.context = context;
        this.msubslider = msubslider;
        this.listner = listner;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.gallery_images, parent, false);
        return  new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder viewHolder, int position)
    {
        Galleryimages sliderItem = msubslider.get(position);

       //   sliderItem.getGalleryimages().
      //  viewHolder.name.setText(sliderItem.getStatus());
        Log.d("bsfhsyfyueruiewry327877",ApplicationConstant.INSTANCE.baseUrl +"/"+ sliderItem.getImage());
        Glide.with(viewHolder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl +"/"+ sliderItem.getImage())
                .fitCenter()
                .into(viewHolder.imageView);
     viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
          //   showDialog(msubslider.toString());
             showDialog( new Gson().toJson(msubslider));
            // showDialog(ApplicationConstant.INSTANCE.baseUrl + sliderItem.getImage());
         }
     });

    }

    @Override
    public int getItemCount()
    {
        return msubslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.image);
            this.name=itemView.findViewById(R.id.name);
        }
    }


    public void showDialog(String  url) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FullScreenDaiolg newFragment = new FullScreenDaiolg();
        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", url);
       newFragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment)
                .addToBackStack(null).commit();

    }
   /* public void showDialog(String url) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FullScreenDaiolg newFragment = new FullScreenDaiolg();
        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", url);
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment)
                .addToBackStack(null).commit();

    }*/









}
