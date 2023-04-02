package com.app.partyplanets.ResttaurantAdapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.Data.Data;
import com.app.partyplanets.R;
import com.app.partyplanets.RestaurantData.FoodManue;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.UtilsMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends   RecyclerView.Adapter<FoodAdapter.ViewHolder>
{    List<FoodManue> menuList= new ArrayList<>();
     List<FoodManue> selectedItem= new ArrayList<>();
    String[] plate= new String[]{"Half plate","Full plate"};
    String[] quantity= new String[]{"1","2","3","4","5","6","7","8","9","10"};
    Context context;
    int minteger = 1;



    public interface onListClickedRowListner
    {
        void onListSelected(int status);
    }
   onListClickedRowListner listner;
    public FoodAdapter(List<FoodManue> menuList, Context context)
    {
        this.menuList = menuList;
        this.context=context;
        this.listner=listner;
    }
    public FoodAdapter( onListClickedRowListner listner )
    {
        this.listner=listner;
    }


    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.menu_list, parent, false);
        return  new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position)
    {   FoodManue slider=menuList.get(position);
        SharedPreferences myPreferences = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        String  userId = securelogincheckResponse.getId();
        SharedPreferences sharedPreferences = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String listungId=sharedPreferences.getString("listingId","");

        holder.title.setText(menuList.get(position).getFood_title());

        holder.image.setImageResource(R.drawable.imm);
        Glide.with(holder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl + slider.getFood_image())
                .fitCenter()
                .error(R.drawable.foo)
                .into(holder.image);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, plate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);
        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, quantity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.quantity.setAdapter(quantityAdapter);


        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                Log.d("kdjfkjf",slider.getPrice()+"   "+slider.getHalf_price());
             String selectItem= String.valueOf(holder.spinner.getSelectedItem());
             String quantity= String.valueOf(holder.quantity.getSelectedItem());
             if (selectItem.equalsIgnoreCase("Full plate"))
               {

                   slider.setPlate_quantity(position+"");
                   holder.price.setText("₹"+slider.getPrice());
                /*   if (slider.getPrice().isEmpty())
                   {
                       holder.addmenu.setVisibility(View.GONE);
                   }
                   else
                   {
                       holder.addmenu.setVisibility(View.VISIBLE);
                   }*/

               }
             else if(selectItem.equalsIgnoreCase("Half plate"))
             {   slider.setPlate_quantity(position+"");
                 holder.price.setText("₹ "+slider.getHalf_price());
                /* if (slider.getHalf_price().isEmpty())
                 {
                     holder.addmenu.setVisibility(View.GONE);
                 }
                 else
                 {
                     holder.addmenu.setVisibility(View.VISIBLE);
                 }*/
             }




                holder.addmenu.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        String selectItem= String.valueOf(holder.spinner.getSelectedItem());
                        String quantity= String.valueOf(holder.quantity.getSelectedItem());
                        UtilsMethod.INSTANCE.addMenu(context,slider.getId(),""+holder.spinner.getSelectedItemPosition(),userId,holder.integer_number.getText().toString(),listungId);
                        Toast.makeText(context, "This Menu  is add for menu list", Toast.LENGTH_SHORT).show();
                        holder.addmenu.setVisibility(View.GONE);
                        holder.remove.setVisibility(View.VISIBLE);


                    }
                });

                holder.remove.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                         //UtilsMethod.INSTANCE.addMenu(context,slider.getId(),""+holder.spinner.getSelectedItemPosition(),userId,quantity);
                        //listner.onListSelected(1);
                        UtilsMethod.INSTANCE.deleteMenu(context,userId,slider.getId());
                        holder.addmenu.setVisibility(View.VISIBLE);
                        holder.remove.setVisibility(View.GONE);

                    }
                });

                holder.increase.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        minteger = minteger + 1;
                        holder.integer_number.setText(""+minteger);
                        UtilsMethod.INSTANCE.addMenu(context,slider.getId(),""+holder.spinner.getSelectedItemPosition(),userId,holder.integer_number.getText().toString(),listungId);
                    }
                });

                holder.decrease.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if (minteger>1)
                        {
                            minteger = minteger - 1;
                            holder.integer_number.setText(""+minteger);
                            UtilsMethod.INSTANCE.addMenu(context,slider.getId(),""+holder.spinner.getSelectedItemPosition(),userId,holder.integer_number.getText().toString(),listungId);
                        }
                        else
                        {
                            Toast.makeText(context, "Minimum one menu is select !", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



    }

    @Override
    public int getItemCount()
    {
        return menuList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {   TextView title,price,integer_number;
        ImageView image,addmenu,remove;
        ImageButton increase,decrease;
        Spinner spinner,quantity;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title=itemView.findViewById(R.id.title);
            this.image=itemView.findViewById(R.id.image);
            this.spinner=itemView.findViewById(R.id.adults_spinner);
            this.price=itemView.findViewById(R.id.price);
            this.quantity=itemView.findViewById(R.id.quantity);
            this.addmenu=itemView.findViewById(R.id.addmenu);
            this.remove=itemView.findViewById(R.id.remove);
            this.increase=itemView.findViewById(R.id.increase);
            this.decrease=itemView.findViewById(R.id.decrease);
            this.integer_number=itemView.findViewById(R.id.integer_number);

        }
    }
}
