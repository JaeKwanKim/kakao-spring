package kr.ac.jejunu.dao;

import kr.ac.jejunu.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by JKKim on 2016. 4. 8..
 */
public class AddUserStatementStrategy implements StatementStrategy {
    User user;
    public AddUserStatementStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        String sql = "insert into test(name, password) values(?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        return statement;
    }
}
