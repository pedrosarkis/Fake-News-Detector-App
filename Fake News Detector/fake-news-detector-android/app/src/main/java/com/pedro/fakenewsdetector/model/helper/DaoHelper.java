package com.pedro.fakenewsdetector.model.helper;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class DaoHelper<T> {
    protected Class className;
    public static DbHelper instance = null;

    public DaoHelper(Context c, Class className) {
        this.className = className;
        if(instance == null) instance = new DbHelper(c.getApplicationContext());
    }

    public Dao<T, Integer> getDao(){
        try {
            return instance.getDao(className);
        } catch (SQLException e) {
            return null;
        }
    }
}
