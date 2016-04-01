package kr.ac.jejunu;

import kr.ac.jejunu.dao.UserDao;
import kr.ac.jejunu.model.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTest {
    long id;
    String name;
    String password;
    UserDao userDao;

    @Before
    public void setup() {
        id = 1L;
        name = "김재관";
        password = "1234";
        userDao = new UserDao();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "헐크";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

        long id = userDao.add(user);

        User resultUser = userDao.get(id);

        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }
}
