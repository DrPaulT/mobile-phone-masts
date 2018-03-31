package uk.co.drdv.bink.mobilephonemasts;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uk.co.drdv.bink.mobilephonemasts.database.MastDatabase;

public class MpmApplication extends Application {

    public static MastDatabase mastDatabase;

    private static final String DATABASE_NAME = "mobile-phone-masts-db";

    @Override
    public void onCreate() {
        super.onCreate();
        createDatabase();
    }

    private void createDatabase() {
        mastDatabase = Room.databaseBuilder(getApplicationContext(),
                MastDatabase.class, DATABASE_NAME).build();
    }
}
