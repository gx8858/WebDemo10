package cn.itcast.demo4;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.utils.MyJdbcUtil;

public class UserDao {
	
	/**
	 * ͨ���û����������ѯ�����û���dao�㣩
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUser(String username,String password){
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			return runner.query("select * from t_user where username = ? and password = ?", new BeanHandler<User>(User.class), username,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
