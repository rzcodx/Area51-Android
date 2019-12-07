package pe.area51.clase04_02.ui.registro_tipo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistroTipoModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RegistroTipoModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}