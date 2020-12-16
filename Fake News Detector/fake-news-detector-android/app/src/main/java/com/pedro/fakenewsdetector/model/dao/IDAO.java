package com.pedro.fakenewsdetector.model.dao;

import java.util.List;

public interface IDAO<T> {
    boolean create(T obj);
    List<T> list();
    T getById(int id);
    boolean update(T obj);
    boolean delete(T obj);
}
