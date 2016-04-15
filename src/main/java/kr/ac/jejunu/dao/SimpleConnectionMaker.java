package kr.ac.jejunu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker implements ConnectionMaker {
    private String ClassName;
    private String url;
    private String userName;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    private String password;
    public SimpleConnectionMaker() {
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(ClassName);
        return DriverManager.getConnection(url, userName, password);//, "root", "");
    }
}