package com.example.taskmaster;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Ignore;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Tasks.class}, version = 1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TasksDao tasksDao();

    @Ignore
    private static volatile AppDatabase INSTANCE;

//    private static final int NUMBER_OF_THREADS = 1;
//    static final ExecutorService databaseWriteExecutor =
//            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "tasksdb1").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

}
