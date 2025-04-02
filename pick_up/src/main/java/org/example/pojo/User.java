package org.example.pojo;

public class User {
    private int user_id;
    private String phone_number;
    private String password;
    private String name;
    private String nickname;
    private String contact_info;
    private String address;


    //setter和getter

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    //构造函数

    public User() {
    }

    public User(int user_id, String phone_number, String password, String name, String nickname, String contact_info, String address) {
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.contact_info = contact_info;
        this.address = address;
    }
}
