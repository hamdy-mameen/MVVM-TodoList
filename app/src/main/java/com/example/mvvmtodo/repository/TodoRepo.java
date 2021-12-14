package com.example.mvvmtodo.repository;

import com.example.mvvmtodo.model.ToDo;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface TodoRepo  {
    MutableLiveData<List<ToDo>> getAllToDos() throws Exception;
    void insertToDo(String title,String description) throws Exception;
    void deleteToDo(long id) throws Exception;
}
