package kr.ac.jejunu.dao;

import kr.ac.jejunu.model.User;

import java.sql.*;

/**
 * Created by JKKim on 2016. 3. 25..
 */
public class UserDao {
    private ConnectionMaker connectionMaker;

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {

            connection = connectionMaker.getConnection();
            statement = statementStrategy.makeStatement(connection);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (ClassNotFoundException e1){
            e1.printStackTrace();
            throw e1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally{
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            if(statement !=null)
                try {
                    statement.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return user;
    }

    public long add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionMaker.getConnection();
            statement = statementStrategy.makeStatement(connection);

            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        long id = getLastInsertId(connection);

        statement.close();
        connection.close();

        return id;
    }

    public void delete(long id) {
        StatementStrategy statementStrategy = new DeleteUserStatementStratgy(id);
        Connection connection = null;
        PreparedStatement statement = null;
        jdbcContextWithStatementStarategy(statementStrategy, connection, statement);

    }

    public void update(User user) {
        StatementStrategy statementStrategy = new UpdateUserStatementStratgy(user);
        Connection connection = null;
        PreparedStatement statement = null;
        jdbcContextWithStatementStarategy(statementStrategy, connection, statement);
    }

    private void jdbcContextWithStatementStarategy(StatementStrategy statementStrategy, Connection connection, PreparedStatement statement) {
        try {
            connection = connectionMaker.getConnection();
            statement = statementStrategy.makeStatement(connection);
            statement.executeUpdate();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(statement !=null)
                try {
                    statement.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    private  long getLastInsertId(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select last_insert_id()");

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement.close();

        return id;
    }

    public void removeAll() {
        String sql = "DELETE from test";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionMaker.getConnection();
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(statement !=null)
                try {
                    statement.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
