package com.app.partyplanets.Adpters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.Data.ReviewList;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllReviewsAdapter extends  RecyclerView.Adapter<AllReviewsAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<ReviewList> msubslider = new ArrayList<>();

    public AllReviewsAdapter(Context context, ArrayList<ReviewList> msubslider) {
        this.context = context;
        this.msubslider = msubslider;
    }

    @NonNull
    @Override
    public AllReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.showreview_users, parent, false);
        return  new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AllReviewsAdapter.ViewHolder viewHolder, int position) {
        ReviewList sliderItem = msubslider.get(position);
        viewHolder.description.setText("- " + sliderItem.getReviews());


        String checkoutDate=sliderItem.getUpdated_at();

        Log.d("jkdfmcvb",checkoutDate);
        try {

           /* SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          */
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

            Date time1= null;


            time1 = fmt.parse(checkoutDate);
            String sd = newFormat.format(time1);
            Log.d("dfkgnfkjg",sd);
            viewHolder.date.setText(""+sd+"");
        }
        catch (ParseException e) {
            e.printStackTrace();
        }





        viewHolder.name.setText(sliderItem.getFirst_name()+""+sliderItem.getLast_name());
        Glide.with(viewHolder.itemView)
                .load(ApplicationConstant.INSTANCE.baseUrl + sliderItem.getImage())
                .fitCenter()
                .error(R.drawable.nature)
                .into(viewHolder.img);
    }

    @Override
    public int getItemCount()
    {
        return msubslider.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,date,description,country;
        CircleImageView img;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.name=itemView.findViewById(R.id.name);
            this.date=itemView.findViewById(R.id.date);
            this.description=itemView.findViewById(R.id.description);
            this.img=itemView.findViewById(R.id.profile);


        }
    }
}
