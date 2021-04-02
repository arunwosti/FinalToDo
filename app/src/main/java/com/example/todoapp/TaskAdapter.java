package com.example.todoapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.data.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {


    private List<Task> taskList;
    private Context context;

    public TaskAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Task> data) {
        taskList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.onBind(task);
    }

    @Override
    public int getItemCount() {
        if (taskList != null)
            return taskList.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView descTextView;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_task, parent, false));
            titleTextView = itemView.findViewById(R.id.title_tv);
            descTextView = itemView.findViewById(R.id.descripttion_tv);
            titleTextView.setOnClickListener(this);
        }

        public void onBind(Task task) {
            titleTextView.setText(task.getTitle());
            descTextView.setText(task.getDescription());

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, UpdateTodo.class);
            Task task = taskList.get(getAdapterPosition());
            intent.putExtra("task_id", task.getId());
            context.startActivity(intent);
        }
    }
}
