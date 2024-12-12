import com.sb.dao.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate template;

    @Test
    public void insertTest(){
//        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//        JdbcTemplate template = (JdbcTemplate) app.getBean("jdbcTemplate");
        int row = template.update("insert into user values(?,?,?)",5,"xiaoming","123123" );
        System.out.println(row);
    }

    @Test
    public void updateTest(){
         template.update("update user set password=898 where id=5");
    }
    @Test
    public void deleteTest(){
        template.update("delete from user where id=5");
    }
    @Test
    public void queryTest(){
        List<User> query = template.query("select * from user ",
                new BeanPropertyRowMapper<User>(User.class));
        System.out.println(query);
    }
    @Test
    public void queryOneTest(int id){
        User user = template.queryForObject("select * from user where id=4",
                new BeanPropertyRowMapper<User>(User.class));
        System.out.println(user);
    }
    @Test
    public void queryCountTest(){
        Integer integer = template.queryForObject("select count(*) from user", int.class);
        System.out.println(integer);
    }
}
