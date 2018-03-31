package uk.co.drdv.bink.mobilephonemasts.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MastDao {

    @Insert(onConflict = REPLACE)
    long[] insertAll(Mast... masts);

    @Insert(onConflict = REPLACE)
    long insert(Mast mast);

    @Query("SELECT COUNT(*) FROM mast")
    long countRows();

    @Query("SELECT * FROM mast ORDER BY CAST(current_rent AS DECIMAL) LIMIT 5")
    LiveData<Mast[]> selectBottom5();

    @Query("SELECT * FROM mast ORDER BY CAST(current_rent AS DECIMAL) DESC LIMIT 5")
    LiveData<Mast[]> selectTop5();
}