package com.innowisegroup.storage;

import com.innowisegroup.AppConstants;
import com.innowisegroup.entity.User;
import com.innowisegroup.exceptions.NoSuchUserException;
import com.innowisegroup.storage.parsers.Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class FileProvider implements DatabaseProvider<User> {

    private static Set<User> inMemoryDB = new HashSet<>();
    private static AtomicLong counterID = new AtomicLong(0);
    private String defaultFilePath;
    private Parser<User> lineParser;

    public FileProvider(Parser<User> lineParser, String defaultFilePath) {
        this.lineParser = lineParser;
        this.defaultFilePath = defaultFilePath;
    }

    public List<User> findAll() {
        return new ArrayList<>(inMemoryDB);
    }

    public void save(User user) {
        user.setId(counterID.incrementAndGet());
        inMemoryDB.add(user);
    }

    public void deleteByID(Long id) throws NoSuchUserException {
        User userForDelete = inMemoryDB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchUserException(AppConstants.NO_SUCH_USER_IN_DB));
        inMemoryDB.remove(userForDelete);
    }

    public User update(User user) {
        inMemoryDB.add(user);
        return user;
    }

    public User get(Long id) throws NoSuchUserException {
        return inMemoryDB
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchUserException(AppConstants.NO_SUCH_USER_IN_DB));
    }

    public void exportDatabaseToFile() throws IOException {
        try (BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(defaultFilePath), StandardCharsets.UTF_8))) {
            for (User user : inMemoryDB) {
                String userData = lineParser.parseObjectToLine(user);
                fileWriter.write(userData);
            }
        } catch (FileNotFoundException fnfe) {
            throw fnfe;
        } catch (IOException ioe) {
            throw ioe;
        }
    }

    public List<User> importDataFromFile() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(defaultFilePath)))) {
            List<User> users = new ArrayList<>();
            while (bufferedReader.ready()) {
                String data = bufferedReader.readLine();
                User user = lineParser.parseLineToObject(data);
                users.add(user);
            }
            return users;
        } catch (FileNotFoundException fnfe) {
            throw fnfe;
        } catch (IOException ioe) {
            throw ioe;
        }
    }
}
