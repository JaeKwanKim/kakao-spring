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

    @Before
    public void setup() {
        id = 1L;
        name = "김재관";
        password = "1234";
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
}
