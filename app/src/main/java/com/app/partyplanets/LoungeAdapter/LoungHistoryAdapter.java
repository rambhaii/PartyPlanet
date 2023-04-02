package com.app.partyplanets.LoungeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.Data.RoomHistory;
import com.app.partyplanets.Lawns.ItemsModel;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LoungHistoryAdapter extends RecyclerView.Adapter<LoungHistoryAdapter.ViewHolder>
 {
  Context context;
  public int selectedPosition=-1;
  String dated;
  ArrayList<RoomHistory> roomHistories;
  public LoungHistoryAdapter(Context  context, ArrayList<RoomHistory> selectedRoomModels ,String date)
  {
   this.context=context;
   this.dated=date;
   this.roomHistories=selectedRoomModels;
  }




  @NonNull
  @Override
  public LoungHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
  {
   LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
   View listItem= layoutInflater.inflate(R.layout.loung_menu_list, parent, false);
   return  new ViewHolder(listItem);
  }

  @Override
  public void onBindViewHolder(@NonNull LoungHistoryAdapter.ViewHolder holder, int position) {
   RoomHistory sliderItem = roomHistories.get(position);


   ItemsModel itemsModel=sliderItem.getFooditems().get(Integer.parseInt(sliderItem.getCat_id()));
   holder.tv_title.setText(itemsModel.getSub_title());

   Glide.with(holder.itemView)
           .load(ApplicationConstant.INSTANCE.baseUrl+itemsModel.getImage())
           .fitCenter()
           .error(R.drawable.dosa)
           .into(holder.image);





  }

  @Override
  public int getItemCount() {
   return roomHistories.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder
  {



   TextView tv_title,subtitle;
   private RelativeLayout isExpendable;
   private ImageView imageView,image;

   public ViewHolder(@NonNull View itemView)
   {
    super(itemView);
    tv_title=itemView.findViewById(R.id.tv_title);
    image=itemView.findViewById(R.id.image);


   }
  }
 }
