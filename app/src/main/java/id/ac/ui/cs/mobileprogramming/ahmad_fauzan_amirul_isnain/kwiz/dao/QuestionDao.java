package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.models.Question;

@Dao
public interface QuestionDao {

    @Insert
    void insert(Question question);

    @Update
    void update(Question question);

    @Delete
    void delete(Question question);

    @Query("SELECT * FROM question")
    List<Question> getAllQuestions();
}
