package com.app.partyplanets.ResttaurantAdapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.Data.Data;
import com.app.partyplanets.R;
import com.app.partyplanets.RestaurantData.Food;
import com.app.partyplanets.Restaurants.RestauMenuBooking;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.UtilsMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FoodMenuViewAdpter extends   RecyclerView.Adapter<FoodMenuViewAdpter.ViewHolder>
{

    private Context context;
    private List<Food> msubslider = new ArrayList<>();
    private List<Food> list = new ArrayList<>();
    String restaurantId;

    public FoodMenuViewAdpter(Context context, List<Food> msubslider,String restaurantId)
    {
        this.context = context;
        this.msubslider = msubslider;
        this.restaurantId=restaurantId;
    }

    @NonNull
    @Override
    public FoodMenuViewAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.shownenulist, parent, false);
        return  new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodMenuViewAdpter.ViewHolder holder, int position)
    {
        Food menu=msubslider.get(position);
        int amt=0;
        holder.title.setText(menu.getTitle());
        holder.quantity.setText(menu.getQty());
        holder.plate.setText(menu.getSize()+" Plate ");
        holder.price.setText("₹ "+menu.getTotal_amt());
        SharedPreferences myPreferences = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        String  userId = securelogincheckResponse.getId();

        ((RestauMenuBooking) context).getTotalAmn(Integer.parseInt(menu.getTotal_amt()));

        Glide.with(holder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl + menu.getImage())
                .fitCenter()
                .error(R.drawable.foo)
                .into(holder.image);


        holder.remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

               ((RestauMenuBooking) context).getTotalAmn(Integer.parseInt("-"+(menu.getTotal_amt())));
            //    UtilsMethod.INSTANCE.deleteMenu(context,userId,menu.getFood_menu_id());
                UtilsMethod.INSTANCE.deletefoodMenu(context,userId,menu.getFood_menu_id(),restaurantId);
                msubslider.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());


                //notifyItemChanged(holder.getLayoutPosition());

            }
        });

    }

    @Override
    public int getItemCount() {
        return msubslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    { ImageView image,remove;
      TextView title,quantity,price,plate;
        public ViewHolder(@NonNull View itemView)
        {  super(itemView);
             this.image=itemView.findViewById(R.id.image);
             this.title=itemView.findViewById(R.id.title);
             this.quantity=itemView.findViewById(R.id.quantity);
             this.price=itemView.findViewById(R.id.price);
             this.plate=itemView.findViewById(R.id.plate);
             this.remove=itemView.findViewById(R.id.remove);
        }
    }
}
