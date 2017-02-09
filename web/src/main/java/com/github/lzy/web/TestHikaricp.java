package com.github.lzy.web;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-16
 */
public class TestHikaricp {
	public static void main(String[] args) throws SQLException {
		HikariConfig config = new HikariConfig();
		HikariDataSource ds = new HikariDataSource(config);
		Connection connection = ds.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("show tables");
		ResultSet resultSet = preparedStatement.executeQuery();
		System.out.println(resultSet);
	}
}
