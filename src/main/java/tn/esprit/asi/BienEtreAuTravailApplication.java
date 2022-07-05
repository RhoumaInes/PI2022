package tn.esprit.asi;

import javax.management.MXBean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableScheduling
@SpringBootApplication
//@Configuration
//@EnableAspectJAutoProxy
//@MXBean(name=“entityManagerFactory”)
public class BienEtreAuTravailApplication {

	public static void main(String[] args) {
		SpringApplication.run(BienEtreAuTravailApplication.class, args);
		
	}

}
