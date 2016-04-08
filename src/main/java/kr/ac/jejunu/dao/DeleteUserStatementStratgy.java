package kr.ac.jejunu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by JKKim on 2016. 4. 8..
 */
public class DeleteUserStatementStratgy implements StatementStrategy {
    long id;
    public DeleteUserStatementStratgy(long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        String sql = "delete from test where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        return statement;
    }
}
