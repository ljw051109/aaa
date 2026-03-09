package com.smart.dao;

import com.smart.endity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String FILE_PATH = "data/users.txt";

    public List<User> readUsersFromFile() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    User user = new User(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void writeUsersToFile(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                bw.write(user.getId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getSex() + "," + user.getPhone() + "," + user.getAddress());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeUserToFile(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(user.getId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getSex() + "," + user.getPhone() + "," + user.getAddress());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}