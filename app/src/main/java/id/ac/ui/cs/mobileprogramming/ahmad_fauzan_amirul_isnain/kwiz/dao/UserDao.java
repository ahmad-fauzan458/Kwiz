package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.models.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * from user ORDER BY score DESC")
    LiveData<List<User>> getAllUsers();
}
