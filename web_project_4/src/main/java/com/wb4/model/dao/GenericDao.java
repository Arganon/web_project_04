package com.wb4.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    Optional<T> find(Integer id);
    List<T> findAll();
    boolean create(T e);
    boolean update(T e);
    boolean delete(Integer id);
}
