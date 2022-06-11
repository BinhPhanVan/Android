package com.example.layoutmanagertwonumber;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<String>> list;
    public LiveData<ArrayList<String>> getList() {
        if (list == null) {
            list = new MutableLiveData<ArrayList<String>>();
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add("0");
            list.setValue(tmp);
        }
        return list;
    }
    public void join(String str)
    {
        ArrayList<String> tmp = list.getValue();
        tmp.add(str);
        list.setValue(tmp);
    }
    public void remove(int index)
    {
        ArrayList<String> tmp = list.getValue();
        tmp.remove(index);
        list.setValue(tmp);
    }
}
