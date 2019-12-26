package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.util.ExternalStoragePermissions;

public class NoteViewModel extends ViewModel {
    private MutableLiveData<String> note;
    private MutableLiveData<String> fullNote;

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

    public void addToFullNote(String note) {
        if (this.fullNote == null) {
            this.fullNote = new MutableLiveData<>();
            this.fullNote.setValue("");
        }
        this.fullNote.setValue(this.fullNote.getValue() + note + "\n\n");
    }

    public MutableLiveData<String> getFullNote() {
        if (fullNote == null) {
            fullNote = new MutableLiveData<>();
        }
        return fullNote;
    }
}

