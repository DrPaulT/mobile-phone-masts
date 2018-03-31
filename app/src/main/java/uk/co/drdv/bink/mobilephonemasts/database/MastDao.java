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

    // TODO    Add ORDER BY and LIMIT 5.
    @Query("SELECT * FROM mast")
    LiveData<Mast[]> selectAll();
}