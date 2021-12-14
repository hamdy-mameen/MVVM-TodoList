package com.example.mvvmtodo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvvmtodo.R;
import com.example.mvvmtodo.model.ToDo;
import com.example.mvvmtodo.model.TodoDB;
import com.example.mvvmtodo.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
MainViewModel viewModel;
RecyclerView recyclerView;
TodoAdapter todoAdapter;
Button addBtn;
EditText titleInput,descriptionInput;
SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        titleInput = findViewById(R.id.title);
        descriptionInput = findViewById(R.id.description);
        addBtn = findViewById(R.id.addBtn);
        todoAdapter = new TodoAdapter(new ArrayList<ToDo>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(todoAdapter);
        TodoDB todoDB = new TodoDB(this);
        sqLiteDatabase = todoDB.getWritableDatabase();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getAllToDos().observe(this, new Observer<List<ToDo>>() {
            @Override
            public void onChanged(List<ToDo> toDoList) {
                if(toDoList==null)
                    Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
               todoAdapter.setToDoList(toDoList);
            }
        });
        viewModel.getErrorStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInput.getText().toString().trim();
                String description = descriptionInput.getText().toString().trim();
                if(!title.isEmpty() && !description.isEmpty()) {
                   viewModel.insert(title,description);
                   titleInput.getText().clear();
                   descriptionInput.getText().clear();
                }else {
                    Toast.makeText(MainActivity.this, "add title and description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}