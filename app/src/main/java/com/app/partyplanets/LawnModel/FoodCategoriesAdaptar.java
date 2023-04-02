package com.app.partyplanets.LawnModel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.partyplanets.R;

import java.util.ArrayList;

public class FoodCategoriesAdaptar extends RecyclerView.Adapter<FoodCategoriesAdaptar.ViewHolder>
{   ArrayList<Foodcategoryitem> foodcategoryitems;
    Context context;

    public FoodCategoriesAdaptar(ArrayList<Foodcategoryitem> foodcategoryitems, Context context)
    {
        this.foodcategoryitems=foodcategoryitems;
        this.context=context;
    }

    @NonNull
    @Override
    public FoodCategoriesAdaptar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.food_menu_view, parent, false);
        return  new  ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCategoriesAdaptar.ViewHolder holder, int position)
    {
        Foodcategoryitem item=foodcategoryitems.get(position);
        Log.d("Ychoose", item.getChoose_limit());
        holder.categories.setText(item.getCat_name()+"( You can choose "+item.getChoose_limit()+" menu )");
        NestedLawnFoodItemAdpptar adapter=new NestedLawnFoodItemAdpptar(item.getFooditems(),context,Integer.parseInt(item.choose_limit));
        //  GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        holder.recyclerView.setLayoutManager( new StaggeredGridLayoutManager(2,LinearLayout.VERTICAL));
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return foodcategoryitems.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout layout_view1;
        TextView categories;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_view1=itemView.findViewById(R.id.layout_view1);
            categories = itemView.findViewById(R.id.textviewfhf);
            recyclerView = itemView.findViewById(R.id.categoriges_food);
        }
    }
}
