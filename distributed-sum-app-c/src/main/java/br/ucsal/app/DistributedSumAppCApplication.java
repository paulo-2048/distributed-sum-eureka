package br.ucsal.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DistributedSumAppCApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedSumAppCApplication.class, args);
	}

}
