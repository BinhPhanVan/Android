package com.example.helloworld_v1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//observer- singleton
public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> number;

    public LiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<Integer>();
            number.setValue(0);
        }
        return number;
    }

    public void increaseNumber() {
        number.setValue(number.getValue() + 1);
    }
    public void decreaseNumber(){
        number.setValue((number.getValue())-1);
    }
}
