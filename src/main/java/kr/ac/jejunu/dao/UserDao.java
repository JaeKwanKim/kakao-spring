package kr.ac.jejunu.dao;

import kr.ac.jejunu.model.User;

import java.sql.*;

/**
 * Created by JKKim on 2016. 3. 25..
 */
public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(long id) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        String sql = "select * from test where id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {

            connection = connectionMaker.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

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
        String sql = "insert into test(name, password) values(?,?)";

        Connection connection = connectionMaker.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());

        statement.executeUpdate();

        long id = getLastInsertId(connection);

        statement.close();
        connection.close();

        return id;
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


    public void delete(long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from test where id=?";
        try {

            connection = connectionMaker.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (ClassNotFoundException e){
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

    public void update(User user) {
        String sql = "update test set name=?, password=? where id=?";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionMaker.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());

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
