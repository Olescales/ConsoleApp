package com.innowisegroup.service;

import com.innowisegroup.exceptions.NoSuchUserException;

import java.io.IOException;
import java.util.List;

public interface CRUD<T> {

    List<T> findAll();

    void add(T t);

    void delete(Long id) throws NoSuchUserException;

    T update(T t);

    T get(Long id) throws NoSuchUserException;
}
