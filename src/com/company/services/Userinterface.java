package com.company.services;

import com.company.entities.User;

import java.util.ArrayList;
import java.util.Scanner;

public interface Userinterface {
    public ArrayList<User> getUsers();

    public User getUserById(int index);

    public void updateUser(int index, User user);

    public void addUser(User user);

    public void deteleUser(int index);

    public User readUser();
}
