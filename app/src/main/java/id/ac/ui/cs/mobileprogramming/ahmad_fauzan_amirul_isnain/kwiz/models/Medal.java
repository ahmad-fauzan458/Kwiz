package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Medal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int drawable;

    private int minScore;

    public Medal(String name, int drawable, int minScore){
        this.name = name;
        this.drawable = drawable;
        this.minScore = minScore;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDrawable() {
        return drawable;
    }

    public int getMinScore() {
        return minScore;
    }
}
