package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.todoapp.data.Task;

public class UpdateTodo extends AppCompatActivity {
    private long taskId;
    private MainViewModel viewModel;
    private TextView titleTextView;
    private TextView descTextView;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todo);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Intent intent = getIntent();
        taskId = intent.getLongExtra("task_id", 0);

        titleTextView = findViewById(R.id.update_task_title);
        descTextView = findViewById(R.id.update_task_desc);

        task = viewModel.getTask(taskId);

        titleTextView.setText(task.getTitle());
        descTextView.setText(task.getDescription());
    }

    public void updateTask(View view) {
        task.setTitle(titleTextView.getText().toString());
        task.setDescription(descTextView.getText().toString());
        viewModel.update(task);
        finish();
    }

    public void deleteTask(View view) {
        viewModel.delete(task);
        finish();
    }
}