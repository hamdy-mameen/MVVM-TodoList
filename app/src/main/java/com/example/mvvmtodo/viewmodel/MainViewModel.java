package com.example.mvvmtodo.viewmodel;

import android.app.Application;

import com.example.mvvmtodo.model.ToDo;
import com.example.mvvmtodo.repository.TodoImplementer;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<List<ToDo>> mutableLiveData;
    private MutableLiveData<String> message;
    private TodoImplementer repository;

    public MainViewModel(Application application){
        super(application);
        repository = new TodoImplementer(application);
        mutableLiveData = new MutableLiveData<>();
        message = new MutableLiveData<>();
        try {
            mutableLiveData.setValue(repository.getAllToDos().getValue());
        } catch (Exception e) {
            message.setValue(e.getMessage());
        }
    }
    public void insert (String title,String description){
        try {
            repository.insertToDo(title,description);
            message.setValue("Inserted Successfully");
        } catch (Exception e) {
           message.setValue(e.getMessage());

        }
    }
    public LiveData<List<ToDo>> getAllToDos(){

        return mutableLiveData;
    }
    public void delete(long id){
        try {
            repository.deleteToDo(id);
        } catch (Exception e) {
            message.setValue(e.getMessage());
        }
    }
    public LiveData<String> getErrorStatus(){
        return message;
    }
}
