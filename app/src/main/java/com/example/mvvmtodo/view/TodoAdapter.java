package com.example.mvvmtodo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvvmtodo.R;
import com.example.mvvmtodo.model.ToDo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    List<ToDo> toDoList;
public TodoAdapter(List<ToDo> toDoList){
    this.toDoList = toDoList;
}
    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
ToDo toDo = toDoList.get(position);
holder.titleTxt.setText(toDo.getTitle());
holder.descriptionTxt.setText(toDo.getDescription());
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder{
 TextView titleTxt,descriptionTxt;
     public TodoViewHolder(@NonNull View itemView) {
         super(itemView);
         titleTxt = itemView.findViewById(R.id.list_item_title);
         descriptionTxt = itemView.findViewById(R.id.list_item_description);
     }
 }
 public void setToDoList(List<ToDo> toDoList){
   this.toDoList = toDoList;
   notifyDataSetChanged();

 }
}
