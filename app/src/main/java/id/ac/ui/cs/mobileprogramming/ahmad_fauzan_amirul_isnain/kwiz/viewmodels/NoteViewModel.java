package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.util.ExternalStoragePermissions;

public class NoteViewModel extends ViewModel {
    private MutableLiveData<String> note;

    public void setNote(String note) {
        if (this.note == null) {
            this.note = new MutableLiveData<>();
        }
        this.note.setValue(note);
    }

    public MutableLiveData<String> getNote() {
        if (note == null) {
            note = new MutableLiveData<>();
        }
        return note;
    }
}

