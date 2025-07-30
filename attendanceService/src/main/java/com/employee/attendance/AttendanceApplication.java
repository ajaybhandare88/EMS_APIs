package com.employee.attendance;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class AttendanceApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(AttendanceApplication.class, args);
	}

	@Bean
	public ModelMapper modelmapper()
	{
		ModelMapper m=new ModelMapper();
		return m;
	}

}
