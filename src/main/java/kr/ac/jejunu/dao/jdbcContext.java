package kr.ac.jejunu.dao;

import kr.ac.jejunu.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbcContext {
    DataSource dataSource;

    public jdbcContext() {
    }

    public long jdbcContextWithStatementStrategyForInsert(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = statementStrategy.makeStatement(connection);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long id = getLastInsertId(connection);

        statement.close();
        connection.close();

        return id;
    }

    public User jdbcContextwithStatementStrategyForQuery(StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            statement = statementStrategy.makeStatement(connection);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return user;
    }

    public void jdbcContextWithStatementStarategy(StatementStrategy statementStrategy) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = statementStrategy.makeStatement(connection);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public long getLastInsertId(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select last_insert_id()");

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement.close();

        return id;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}