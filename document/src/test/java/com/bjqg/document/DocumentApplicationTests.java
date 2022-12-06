package com.bjqg.document;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DocumentApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void salaryTest(){
		BigDecimal salary = new BigDecimal("5500");
		BigDecimal crease = new BigDecimal("1.1");
		for (int i = 1; i < 20; i++) {
			salary = salary.multiply(crease);
			if (salary.compareTo(new BigDecimal("10000")) == 1){
				System.out.println("第"+i+"年工资为："+salary);
				break;
			}
		}
	}

}
