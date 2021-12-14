package com.example.mvvmtodo.repository;

import android.app.Application;

import com.example.mvvmtodo.model.ToDo;
import com.example.mvvmtodo.model.ToDoDbOperations;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class TodoImplementer implements TodoRepo {
    private ToDoDbOperations todoDB;
    MutableLiveData<List<ToDo>> mutableLiveData;
    public TodoImplementer(Application application){
        todoDB = new ToDoDbOperations(application);
        mutableLiveData = new MutableLiveData<>();
    }
    @Override
    public MutableLiveData<List<ToDo>> getAllToDos() throws Exception {
        if(todoDB.getAllToDos().size()==0)
            throw new Exception("empty list");
        mutableLiveData.setValue(todoDB.getAllToDos());
        return mutableLiveData;
    }

    @Override
    public void insertToDo(String title, String description) throws Exception {
      boolean isAdded = todoDB.insert(title,description);
      if(!isAdded)
          throw new Exception(" insert failed something went wrong");
       mutableLiveData.setValue(todoDB.getAllToDos());
    }

    @Override
    public void deleteToDo(long id) throws Exception {
     boolean isdDeleted = todoDB.delete(id);
     if(!isdDeleted)
         throw new Exception("delete failed something went wrong");
        mutableLiveData.setValue(todoDB.getAllToDos());
    }
}
