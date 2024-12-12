import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

class FirstTestTest {

    @Test
    //c3p0链接数据库
    public void poolTest() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mytest?serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("0000");
        Connection connection = dataSource.getConnection();
        System.out.println("connect.....");
        System.out.println(connection);
        connection.close();
    }
    @Test
    //druid链接数据库
    public void druidTest() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mytest?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("0000");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }
    @Test
    public void springC3p0Test() throws Exception{
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext" +
                ".xml");
        DataSource dataSource = (DataSource) app.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}