package com.app.partyplanets.LawnModel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NestedLawnFoodItemAdpptar  extends   RecyclerView.Adapter<NestedLawnFoodItemAdpptar.ViewHolder>
{
    ArrayList<Fooditem> fooditems;
    ArrayList<Fooditem> selecteditems=new ArrayList<>();
    Context context;
    int selectedCount;
    public NestedLawnFoodItemAdpptar(ArrayList<Fooditem> fooditems, Context context,int selectedCount)
    {
        this.context=context;
        this.fooditems=fooditems;
        this.selectedCount=selectedCount;
    }

    @NonNull
    @Override
    public NestedLawnFoodItemAdpptar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.foodmenu, parent, false);
        return  new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NestedLawnFoodItemAdpptar.ViewHolder holder, int position)
    {
        Fooditem item=fooditems.get(position);
        holder.checkBox.setChecked(item.isSelected);

        Glide.with(holder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl+item.getImage())
                .fitCenter()
                .error(R.drawable.dosa)
                .into(holder.img);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view)
                                               {
                                                   Log.d("dhfoihgoeg: ",String.valueOf(selecteditems.size()));
                                                   if(selectedCount>selecteditems.size())
                                                   {
                                                       selecteditems.add(item);
                                                       item.setSelected(true);
                                                       holder.checkBox.setChecked(true);
                                                   }
                                                   else
                                                   {
                                                       if (selecteditems.contains(item))
                                                       {
                                                           selecteditems.remove(item);
                                                           holder.checkBox.setChecked(false);
                                                           item.setSelected(false);
                                                       }
                                                       else
                                                       {
                                                           item.setSelected(false);
                                                           Toast.makeText(context, "You can not select more item", Toast.LENGTH_SHORT).show();
                                                           holder.checkBox.setChecked(false);
                                                       }

                                                   }
//                                    ((LawnBookingActivity)context).selectedFoodMenu(selecteditems);
                                               }

                                           }
        );
//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                Log.d("sdvbgskvdsou: ",item.choose_limit);
////                if(Integer.parseInt(item.choose_limit)==selecteditems.size())
////                {
////                    Log.d("sdvbgskvdsou: ",item.choose_limit);
////                    holder.checkBox.setChecked(true);
////                }
////                else {
////                    Toast.makeText(context, "You can not select more item", Toast.LENGTH_SHORT).show();
////                    holder.checkBox.setChecked(false);
////                }
//            }
//        });



        holder.checkBox.setText(item.getTitle());

    }

    @Override
    public int getItemCount() {
        return fooditems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkBox);
            img=itemView.findViewById(R.id.image);
        }
    }
}
