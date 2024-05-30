package com.example.LAB5_Nhom4;

import com.example.LAB5_Nhom4.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;


@SpringBootApplication
public class Lab5Nhom4Application implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Lab5Nhom4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String sql = "SELECT * FROM customer";
		List<Customer> customers = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));

		customers.forEach(System.out::println);
	}
}
