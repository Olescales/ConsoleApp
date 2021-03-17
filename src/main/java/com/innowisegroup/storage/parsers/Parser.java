package com.innowisegroup.storage.parsers;

public interface Parser<T> {

    T parseLineToObject(String line);

    String parseObjectToLine(T t);
}
