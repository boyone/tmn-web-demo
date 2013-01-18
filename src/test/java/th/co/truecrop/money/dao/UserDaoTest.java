package th.co.truecrop.money.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import th.co.truecrop.money.config.TestJdbcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJdbcConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class UserDaoTest {
	
	@Autowired
	private DataSource dataSource;
	
	//@Autowired
	//private EmbeddedDatabase embeddedDatabase;
	
	JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Test	
	public void isConnected() throws SQLException {
		Connection con = DataSourceUtils.getConnection(dataSource);
		assertEquals(false, con.isClosed());
		con.close();
		assertEquals(true, con.isClosed());
	}
	
	@Test
	@DatabaseSetup("/dbtest/users.xml")
	public void countUser() {
		//jdbcTemplate.query("select * from user");
		int count = jdbcTemplate.queryForInt("select count(*) from users");
		assertEquals(2, count);
	}
	
	@Test
	@DatabaseSetup("/dbtest/users2.xml")
	public void countUser2() {
		//jdbcTemplate.query("select * from user");
		int count = jdbcTemplate.queryForInt("select count(*) from users");
		assertEquals(1, count);
	}
	
}
