package com.github.FranMarin123.model.dao;

import java.io.Closeable;

public interface DAO<T,K,F> extends Closeable {
    T save(T objectToSave);
    T delete(T objectToDelete);
    T findByX(K key,F field);
    T findById(int key);

}
