package com.pedro.fakenewsdetector.model.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.pedro.fakenewsdetector.model.FakeNewsModel;
import com.pedro.fakenewsdetector.model.SystemModel;

import java.sql.SQLException;

public class DbHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "fake_news_detector.db";
    private static final int DATABASE_VERSION = 3;

    public DbHelper(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, FakeNewsModel.class);
            TableUtils.createTableIfNotExists(connectionSource, SystemModel.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, FakeNewsModel.class, true);
            TableUtils.dropTable(connectionSource, SystemModel.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}