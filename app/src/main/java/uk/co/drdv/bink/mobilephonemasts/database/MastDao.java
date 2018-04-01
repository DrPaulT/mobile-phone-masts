package uk.co.drdv.bink.mobilephonemasts.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MastDao {

    @Insert(onConflict = REPLACE)
    long insert(Mast mast);

    @Query("SELECT COUNT(*) FROM mast")
    long countRows();

    @Query("SELECT * FROM mast "
            + "ORDER BY CAST(current_rent AS DECIMAL) "
            + "LIMIT 5")
    LiveData<Mast[]> selectBottom5();

    @Query("SELECT * FROM mast "
            + "ORDER BY CAST(current_rent AS DECIMAL) "
            + "DESC LIMIT 5")
    LiveData<Mast[]> selectTop5();

    @Query("SELECT tenant_name AS tenantName, COUNT(*) AS num FROM mast "
            + "WHERE lease_start_date <= '2018-04-01' "
            + "AND lease_end_date >= '2018-04-01' "
            + "GROUP BY tenant_name "
            + "ORDER BY 2 DESC")
    LiveData<TenantCount[]> selectTenantCount();

    @Query("SELECT *  FROM mast "
            + "WHERE lease_start_date >= '1999-06-01' "
            + "AND lease_start_date <= '2007-08-31' "
            + "ORDER BY lease_start_date")
    LiveData<Mast[]> selectLeaseDates();
}