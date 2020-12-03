package com.example.intergratedapplicationhub.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intergratedapplicationhub.R;

import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ViewHolder>
{
    private List<Application> applications;
    ItemClicked activity;

    public interface ItemClicked{
        void onItemClicked(int index);
    }
    public ApplicationAdapter(Context context,List<Application>list){
        applications = list;
        activity = (ItemClicked) context;
    }
    public class ViewHolder extends  RecyclerView.ViewHolder
    {
        TextView tvSurname,tvIDNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tvSurname = itemView.findViewById(R.id.tvSurname);
            tvIDNumber = itemView.findViewById(R.id.tvIDNumber);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(applications.indexOf((Application)view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public ApplicationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.application_row_layout,viewGroup,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationAdapter.ViewHolder viewHolder, int position) {
        viewHolder.itemView.setTag(applications.get(position));
        viewHolder.tvIDNumber.setText(applications.get(position).getUserEmail());
        viewHolder.tvSurname.setText(applications.get(position).getUniversityName());

    }

    @Override
    public int getItemCount() {
        return applications.size();
    }
}
