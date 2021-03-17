package com.innowisegroup.storage;

import com.innowisegroup.exceptions.NoSuchUserException;

import java.io.IOException;
import java.util.List;

public interface DatabaseProvider<T> {

    List<T> findAll();

    void save(T t);

    void deleteByID(Long id) throws NoSuchUserException;

    T update(T t);

    T get(Long id) throws NoSuchUserException;
}
