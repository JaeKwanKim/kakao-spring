package kr.ac.jejunu;

import kr.ac.jejunu.dao.DaoFactory;
import kr.ac.jejunu.dao.UserDao;
import kr.ac.jejunu.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTest {
    private long id;
    private String name;
    private String password;
    private UserDao userDao;

    @Before
    public void setup() {
        id = 1L;
        name = "김재관";
        password = "1234";
//        userDao = new DaoFactory().userDao();
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = (UserDao) context.getBean("userDao");
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void getForHanla() throws SQLException, ClassNotFoundException {
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

        long idd = userDao.add(user);

        User resultUser = userDao.get(idd);

        assertThat(resultUser.getId(), is(idd));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }

    @Test
    public void addForHanla() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "헐크";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

        long idd = userDao.add(user);

        User resultUser = userDao.get(idd);

        assertThat(resultUser.getId(), is(idd));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }


    private void validate(String name, String password) {

    }
}
