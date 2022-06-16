package com.tdw.pojo;


public class User {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private Integer identify;//身份
    private String token;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getIdentify() {
        return identify;
    }

    public void setIdentify(Integer identify) {
        this.identify = identify;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", identify=" + identify +
                ", token='" + token + '\'' +
                '}';
    }

    public User() {
    }

    public User(Integer id, String username, String password, String phone, Integer identify, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.identify = identify;
        this.token = token;


    }

}
