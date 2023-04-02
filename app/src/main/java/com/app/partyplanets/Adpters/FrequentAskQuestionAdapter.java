package com.app.partyplanets.Adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.partyplanets.Data.FrequentQuestionModel;
import com.app.partyplanets.R;

import java.util.List;

public class FrequentAskQuestionAdapter extends RecyclerView.Adapter<FrequentAskQuestionAdapter.ViewHolder>
 {
    Context context;
  List<FrequentQuestionModel> frequentQuestionModels;

  public FrequentAskQuestionAdapter(Context context, List<FrequentQuestionModel> frequentQuestionModels) {
   this.context=context;
   this.frequentQuestionModels=frequentQuestionModels;
  }


  @NonNull
  @Override
  public FrequentAskQuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
  {
   LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
   View listItem= layoutInflater.inflate(R.layout.questionand_query, parent, false);
   return  new  ViewHolder(listItem);
  }

  @Override
  public void onBindViewHolder(@NonNull FrequentAskQuestionAdapter.ViewHolder holder, int position)
  {
   FrequentQuestionModel data=frequentQuestionModels.get(position);
   holder.question.setText(data.getQuestion());

  }

  @Override
  public int getItemCount() {
   return frequentQuestionModels.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder
  {
   TextView question;
   public ViewHolder(@NonNull View itemView)
   {
    super(itemView);
    question=itemView.findViewById(R.id.question);
   }
  }
 }
