package kr.ac.jejunu;

import kr.ac.jejunu.dao.UserDao;
import kr.ac.jejunu.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTest {
    private long id;
    private String name;
    private String password;
    private UserDao userDao;

    @Before
    public void setup() {
        name = "김재관";
        password = "1234";
//        userDao = new DaoFactory().userDao();
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("DaoFactory.xml");
        userDao = (UserDao) context.getBean("userDao");
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        User user = new User(name, password);
        id = userDao.add(user);
        user = userDao.get(id);

        validate(name, password, id, user);
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

        validate(name, password, idd, resultUser);
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "헐크";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

        long idd = userDao.add(user);

        userDao.delete(idd);

        User resultUser = userDao.get(idd);
        assertThat(resultUser, nullValue());
    }
//    @Test
//    public void addForHanla() throws SQLException, ClassNotFoundException {
//        User user = new User();
//        String name = "헐크";
//        String password = "2222";
//
//        user.setName(name);
//        user.setPassword(password);
//
//        long idd = userDao.add(user);
//
//        User resultUser = userDao.get(idd);
//
//        validate(name, password, idd, resultUser);
//    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "헐크";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

        long idd = userDao.add(user);

        name = "허윤호";
        password = "1111";
        user.setName(name);
        user.setPassword(password);
        user.setId(idd);

        userDao.update(user);

        User resultUser = userDao.get(idd);

        validate(name, password, idd, resultUser);
    }

    private void validate(String name, String password, long idd, User resultUser) {
        assertThat(resultUser.getId(), is(idd));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }

}
