package kr.ac.jejunu.dao;

import kr.ac.jejunu.model.User;

import java.sql.*;

/**
 * Created by JKKim on 2016. 3. 25..
 */
public class UserDao {
    public User get(long id) throws SQLException, ClassNotFoundException {
        String sql = "select * from test where id = ?";

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        statement.close();
        connection.close();

        return user;
    }

    public long add(User user) throws SQLException, ClassNotFoundException {
        String sql = "insert into test(name, password) values(?,?)";

        Connection connection = getConnection();
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
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false","root","");
    }
}
