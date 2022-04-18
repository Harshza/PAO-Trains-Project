package com.company.entities;

public class User {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;

    public User(){}

    public User(int id, String name, String phoneNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void userInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Phone number: " + this.phoneNumber);
        System.out.println("Email address: " + this.email);
        System.out.println("Home address: " + this.address);
    }

}
