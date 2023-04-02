package com.app.partyplanets.Adpters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.DashBoad.RoomActivity;
import com.app.partyplanets.DashBoad.RoomsHistry;
import com.app.partyplanets.Data.Galleryimages;
import com.app.partyplanets.Data.RoomData;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RoomsAdapter extends  RecyclerView.Adapter<RoomsAdapter.ViewHolder> {

    public interface onListClickedRowListner
    {
       void onListSelected(ArrayList<RoomData> room_list,int toatal_price,int actual_price,ArrayList<String> roomId,ArrayList<String> roomCount);


    }
    onListClickedRowListner listner;

    private Context context;
    private List<RoomData> msubslider = new ArrayList<>();
    Galleryimages galleryimages;

   /* public RoomsAdapter(Context context, List<RoomData> msubslider)
    {
        this.context = context;
        this.msubslider = msubslider;
    }*/

    ArrayList<RoomData> room_list=new ArrayList<>();
    public RoomsAdapter(Context context, List<RoomData> msubslider,onListClickedRowListner listner)
    {
        this.context = context;
        this.msubslider = msubslider;
        this.listner = listner;

    }


    @NonNull
    @Override
    public RoomsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.rooms, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomsAdapter.ViewHolder viewHolder, int position)
    {
        RoomData sliderItem = msubslider.get(position);
        viewHolder.title.setText(sliderItem.getRoom_type());
        viewHolder.bedtype.setText(sliderItem.getBed_type());



       if (sliderItem.getAmenities().size()>0)
       {   Log.d("dhfghjg","hgjchvcv");
           Amenties adapter = new Amenties(context, sliderItem.getAmenities());
           GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
           viewHolder.recyclerView.setLayoutManager(layoutManager);
           viewHolder.recyclerView.setAdapter(adapter);
           adapter.notifyDataSetChanged();

       }

        galleryimages=sliderItem.getGalleryimages().get(0);
        String img=galleryimages.getImage();
        Log.d("hfgsdfgysdgywetr7",img);
        Glide.with(viewHolder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl + img)
                .placeholder(R.drawable.imm)
                .fitCenter()
                .into(viewHolder.image);
        Log.d("dfghjdfgugu34",""+galleryimages.getImage());
        viewHolder.room_size.setText("Size :" + sliderItem.getRoom_size() + " ft²");

        viewHolder.offer_price.setText("₹. "+ sliderItem.getOffer_price());
        viewHolder.current_price.setText("₹. " + sliderItem.getPrice());
        viewHolder.roomAvailability.setText("only "+sliderItem.getTotal_rooms()+" room left on party planet ");

        Log.d("nbdschsgdhcsdhcy",sliderItem.getId());
        Log.d("jdkfisdig", "" + sliderItem.getTotal_rooms());



        viewHolder.linearlayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RoomActivity.actualprice.setVisibility(View.VISIBLE);
                viewHolder.linearlayout.setVisibility(View.GONE);
                viewHolder.layout.setVisibility(View.VISIBLE);
                ArrayList<String> listView = new ArrayList<>();
                for (int i = 1; i <= sliderItem.getTotal_rooms(); i++)
                {
                    listView.add("" + i + " Room");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listView);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                viewHolder.spinner.setAdapter(adapter);
                viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        int totalprice=0;
                        int room=0;
                        ArrayList<String> roomCount=new ArrayList<>();
                        ArrayList<String> roomId=new ArrayList<>();
                        int actualprice=0;


                        if (room_list.contains(sliderItem))
                        {
                            int index=room_list.indexOf(sliderItem);
                            room_list.get(index).setSlected_room(viewHolder.spinner.getSelectedItemPosition()+1);
                        } else
                        {
                            sliderItem.setSlected_room(viewHolder.spinner.getSelectedItemPosition()+1);
                            room_list.add(sliderItem);
                         }
                        for(RoomData i:room_list)
                        {
                            totalprice = (i.getSlected_room() * Integer.parseInt(i.getOffer_price())) + totalprice;
                            room=room+i.getSlected_room();
                            roomId.add(String.valueOf(i.getId()));
                            actualprice=(i.getSlected_room()*Integer.parseInt(i.getPrice()))+actualprice;
                            roomCount.add(""+i.getSlected_room());
                         // roomId= Integer.parseInt(i.getId());

                        }
                        RoomActivity.price.setText("Rs."+String.valueOf(totalprice));
                        RoomActivity.totalroom.setText(String.valueOf(room)+" Rooms");
                        RoomActivity.actualprice.setText("Rs."+String.valueOf(actualprice));
                        listner.onListSelected(room_list,totalprice,actualprice,roomId,roomCount);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }
        });




        viewHolder.btn_remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (room_list.contains(sliderItem))
                {
                    room_list.remove(sliderItem);
                }

                int totalprice=0;
                int room=0;

                int actualprice=0;
                ArrayList<String> roomId=new ArrayList<>();
                ArrayList<String> roomCount=new ArrayList<>();

                for(RoomData iss:room_list)
                {
                     totalprice = (iss.getSlected_room() * Integer.parseInt(iss.getOffer_price())) + totalprice;
                     room=room+iss.getSlected_room();
                     actualprice=(iss.getSlected_room()*Integer.parseInt(iss.getPrice()))+actualprice;
                     roomId.add(String.valueOf(iss.getId()));
                     roomCount.add(""+iss.getSlected_room());
                    // roomId= Integer.parseInt(iss.getId());
                }
                RoomActivity.price.setText("Rs."+String.valueOf(totalprice));
                RoomActivity.totalroom.setText(String.valueOf(room)+" Room");
                RoomActivity.actualprice.setText("Rs."+String.valueOf(actualprice));

                listner.onListSelected(room_list,totalprice,actualprice,roomId,roomCount);
                viewHolder.linearlayout.setVisibility(View.VISIBLE);
                viewHolder.layout.setVisibility(View.GONE);

            }
        });



    }

    @Override
    public int getItemCount() {
        return msubslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title, bedtype, room_size, breakfast, pricefodays, roomAvailability, offer_price, current_price;
        LinearLayout linearlayout;
        RelativeLayout layout;
        Button btn_remove;
        Spinner spinner;
        TextView select;
        ImageView image;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.bedtype = itemView.findViewById(R.id.bedtype);
            this.room_size = itemView.findViewById(R.id.room_size);
            this.offer_price = itemView.findViewById(R.id.offer_price);
            this.current_price = itemView.findViewById(R.id.current_price);
            this.layout = itemView.findViewById(R.id.layout);
            this.linearlayout = itemView.findViewById(R.id.linearlayout);
            this.btn_remove = itemView.findViewById(R.id.btn_remove);
            this.spinner = itemView.findViewById(R.id.spinner);
            this.select = itemView.findViewById(R.id.select);
            this.roomAvailability = itemView.findViewById(R.id.roomAvailability);
            this.image = itemView.findViewById(R.id.image);
            this.recyclerView = itemView.findViewById(R.id.recyclerview4);
        }
    }

}
