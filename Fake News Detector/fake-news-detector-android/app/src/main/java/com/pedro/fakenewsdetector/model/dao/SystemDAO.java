package com.pedro.fakenewsdetector.model.dao;

import android.content.Context;

import com.pedro.fakenewsdetector.model.SystemModel;
import com.pedro.fakenewsdetector.model.helper.DaoHelper;

import java.sql.SQLException;
import java.util.List;

public class SystemDAO extends DaoHelper<SystemModel> implements IDAO<SystemModel> {
    public SystemDAO(Context c) {
        super(c, SystemModel.class);
        populate();
    }

    public void populate() {
        try {
            this.getDao().createIfNotExists(new SystemModel(false));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean create(SystemModel obj) {
        try {
            return this.getDao().create(obj) > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<SystemModel> list() {
        try {
            return this.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SystemModel getById(int id) {
        try {
            return this.getDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(SystemModel obj) {
        try {
            return this.getDao().update(obj) > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(SystemModel obj) {
        try {
            return this.getDao().delete(obj) > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public SystemModel get() {
        return getById(1);
    }
}
