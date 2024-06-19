package com.puntoventa.config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


/*@Configuration
@ComponentScan(basePackages = "com.puntoventa.*")*/
public class AppConfig {

	
	
	  @Bean public DataSource dataSource() { DriverManagerDataSource dataSource =
	  new DriverManagerDataSource(); //MySQL database we are using
	  dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	  dataSource.setUrl("jdbc:mysql://localhost:3306/puntoventa");//change url
	  dataSource.setUsername("root");//change userid
	  dataSource.setPassword("");//change pwd
	  
	  return dataSource; }
	  
	  @Bean public JdbcTemplate jdbcTemplate() { JdbcTemplate jdbcTemplate = new
	  JdbcTemplate(); jdbcTemplate.setDataSource(dataSource()); return
	  jdbcTemplate; }
	  
	 

	
}