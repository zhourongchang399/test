package org.example.entity;

public class UserAccount {
    //用户id
    //用户名
    //密码
    int userid;
    String username;
    String password;

    public UserAccount(int userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public UserAccount() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

}
