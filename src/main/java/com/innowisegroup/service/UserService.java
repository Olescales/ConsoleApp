package com.innowisegroup.service;

import com.innowisegroup.entity.User;
import com.innowisegroup.exceptions.NoSuchUserException;
import com.innowisegroup.storage.DatabaseProvider;

import java.util.List;

public class UserService implements IUserService {

    private DatabaseProvider<User> databaseProvider;

    public UserService(DatabaseProvider<User> databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    @Override
    public List<User> findAll() {
        return databaseProvider.findAll();
    }

    @Override
    public void add(User user) {
        databaseProvider.save(user);
    }

    @Override
    public void delete(Long id) throws NoSuchUserException {
        databaseProvider.deleteByID(id);
    }

    @Override
    public User update(User user) {
        return databaseProvider.update(user);
    }

    @Override
    public User get(Long id) throws NoSuchUserException {
        return databaseProvider.get(id);
    }
}
