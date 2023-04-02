package com.app.partyplanets.LoungeAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.LawnModel.FoodCategoriesAdaptar;
import com.app.partyplanets.LawnModel.LawnData;
import com.app.partyplanets.Lounges.LoungeBooking;
import com.app.partyplanets.R;

import java.util.ArrayList;

public class LoungAdaptar extends RecyclerView.Adapter<LoungAdaptar.ViewHolder>
{
    public int selectedPosition=-1;
    private Context context;
    public ArrayList<LawnData> lawnData = new ArrayList<>();

    public LoungAdaptar(Context context, ArrayList<LawnData> lawnData)
    {
        this.context = context;
        this.lawnData = lawnData;
    }

    @NonNull
    @Override
    public LoungAdaptar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.lawn_foodlist, parent, false);
        return  new ViewHolder(listItem);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public void onBindViewHolder(@NonNull LoungAdaptar.ViewHolder holder, int position)
    {

        LawnData listdata=lawnData.get(position);
        boolean isExpendable=(position==selectedPosition);
        holder.recyclerView.setVisibility(isExpendable ? View.VISIBLE :View.GONE);
        holder.radioButton.setText("Per Plate ₹ "+listdata.getAmount_type());
        if (isExpendable)
        {
            holder.imageView.setImageResource(R.drawable.ic_up_arrow);
            holder.radioButton.setChecked(isExpendable);
            ((LoungeBooking)context).selectedFoodMenu(listdata);
        }
        else
        {
            holder.imageView.setImageResource(R.drawable.ic_arrow__1_);
            holder.radioButton.setChecked(isExpendable);
        }


        FoodCategoriesAdaptar adapter=new FoodCategoriesAdaptar(listdata.getFoodcategoryitems(),context);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(adapter);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition=holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });



    }

    @Override
    public int getItemCount()
    {
        return lawnData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        CheckBox radioButton;
        RelativeLayout linearLayout;
        RecyclerView recyclerView;
        TextView tv_title;
        private RelativeLayout isExpendable;
        private ImageView imageView,image;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            radioButton=itemView.findViewById(R.id.tv_title);

            recyclerView=itemView.findViewById(R.id.recy_child);

            tv_title=itemView.findViewById(R.id.tv_title);
            isExpendable=itemView.findViewById(R.id.isExpendable);
            linearLayout=itemView.findViewById(R.id.linearlayout);
            image=itemView.findViewById(R.id.image);
            imageView=itemView.findViewById(R.id.arrow);
            //radioButton = (RadioButton) itemView.findViewById(R.id.radio);

        }
    }
}
