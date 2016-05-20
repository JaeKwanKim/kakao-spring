package kr.ac.jejunu.dao;

import kr.ac.jejunu.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by JKKim on 2016. 3. 25..
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public User get(long id){
        String sql = "select * from test where id = ?";
//        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{id}, userMapper);
        } catch (EmptyResultDataAccessException e) {}
        return user;
    }

    public long add(User user) {
        String sql = "insert into test(name, password) values(?,?)";
//        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            return statement;
        }, generatedKeyHolder);
        return (long) generatedKeyHolder.getKey();
    }

    public void delete(long id) {
        String sql = "delete from test where id=?";
//        StatementStrategy statementStrategy = new DeleteUserStatementStratgy(id);
        jdbcTemplate.update(sql, new Object[] {id});
    }

    public void update(User user) {
        String sql = "update test set name=?, password=? where id=?";
//        StatementStrategy statementStrategy = new UpdateUserStatementStratgy(user);
        jdbcTemplate.update(sql, new Object[]{user.getName(), user.getPassword(), user.getId()});
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> userMapper = (resultSet, i) -> {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        return user;
    };
}
