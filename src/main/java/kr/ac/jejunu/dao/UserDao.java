package kr.ac.jejunu.dao;

import kr.ac.jejunu.model.User;

import java.sql.*;

/**
 * Created by JKKim on 2016. 3. 25..
 */
public class UserDao {
    private jdbcContext jdbcContext;

    public User get(long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        return jdbcContext.jdbcContextwithStatementStrategyForQuery(statementStrategy);
    }

    public long add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
        return jdbcContext.jdbcContextWithStatementStrategyForInsert(statementStrategy);
    }

    public void delete(long id) {
        StatementStrategy statementStrategy = new DeleteUserStatementStratgy(id);
        jdbcContext.jdbcContextWithStatementStarategy(statementStrategy);
    }

    public void update(User user) {
        StatementStrategy statementStrategy = new UpdateUserStatementStratgy(user);
        jdbcContext.jdbcContextWithStatementStarategy(statementStrategy);
    }

    public void setJdbcContext(jdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
