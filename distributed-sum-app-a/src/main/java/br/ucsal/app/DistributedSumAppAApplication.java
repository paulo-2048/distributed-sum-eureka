package br.ucsal.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DistributedSumAppAApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedSumAppAApplication.class, args);
	}

}
