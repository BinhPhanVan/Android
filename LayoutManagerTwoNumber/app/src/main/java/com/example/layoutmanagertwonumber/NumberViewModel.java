
package com.example.layoutmanagertwonumber;

        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;
        import androidx.lifecycle.ViewModel;

public class NumberViewModel extends ViewModel {
    private MutableLiveData<Integer> number;
    public LiveData<Integer> getNumber()
    {
        if (number == null) {
            number = new MutableLiveData<Integer>();
            number.setValue(0);
        }
        return number;
    }
}

