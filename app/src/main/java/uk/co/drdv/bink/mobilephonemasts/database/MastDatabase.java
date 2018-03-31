package uk.co.drdv.bink.mobilephonemasts.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Mast.class}, version = 1)
public abstract class MastDatabase extends RoomDatabase {
    public abstract MastDao mastDao();
}