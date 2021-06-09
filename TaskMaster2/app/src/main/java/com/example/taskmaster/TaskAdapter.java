package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {


    Context context;
   public List<Tasks> tasksList;


    public TaskAdapter(Context context,List<Tasks> tasksList) {
        this.context = context ;
        this.tasksList = tasksList;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public Tasks tasks;
        public View itemView;
        ItemClickListener itemClickListener;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getLayoutPosition());

        }
        public void setItemClickListener(ItemClickListener listener){
            this.itemClickListener = listener;
        }

    }


    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent,false);


// event listener action
        return new TaskViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.tasks = tasksList.get(position);


        TextView title = holder.itemView.findViewById(R.id.fragmentTitle);
        TextView body = holder.itemView.findViewById(R.id.fragmentbody);
        body.setVisibility(View.GONE);
        TextView state = holder.itemView.findViewById(R.id.fragmentstate);

        title.setText(holder.tasks.getTitle());
        body.setText(holder.tasks.getBody());
        state.setText(holder.tasks.getState());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int postion) {
                String str_title = tasksList.get(postion).getTitle();
                String str_body  = tasksList.get(postion).getBody();
                String str_state  = tasksList.get(postion).getState();
                 int id = tasksList.get(position).getId();
              String fileName = tasksList.get(position).getFile();

                Intent intent = new Intent(context, TaskDetailPage.class);
                intent.putExtra("title", str_title);
                intent.putExtra("body", str_body);
                intent.putExtra("state", str_state);
                intent.putExtra("id", id);
                intent.putExtra("fileName",fileName);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }



}





























