package tn.esprit.asi;

import javax.management.MXBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.esprit.asi.entities.Role;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.entities.UserRole;
import tn.esprit.asi.services.IUserRoleService;
import tn.esprit.asi.services.UserRoleService;
import tn.esprit.asi.services.UserService;

import java.util.Date;

@EnableSwagger2
@EnableScheduling
@SpringBootApplication
//@Configuration
@EnableAspectJAutoProxy
//@MXBean(name=“entityManagerFactory”)
@EnableDiscoveryClient
public class BienEtreAuTravailApplication {

	public static void main(String[] args) {
		SpringApplication.run(BienEtreAuTravailApplication.class, args);
	}

//	private static void InitData() {
//		UserRoleService userRoleService = new UserRoleService();
//		UserService userService = new UserService();
//		UserRole userRole = userRoleService.CreateRole(new UserRole(Role.ROOT));
//		userRoleService.CreateRole(new UserRole(Role.SUPERADMIN));
//		userRoleService.CreateRole(new UserRole(Role.ADMIN));
//		userRoleService.CreateRole(new UserRole(Role.USER));
//
//		User user = new User("Beat", "bienetreautravail@outlook.com", "123", "Beat", "Team", 3, new Date(2022, 9, 15), "project", "Creator", "Informatique", "tunis", "CHARGUIA 2");
//		try {
//			userService.CreateAdmin(user);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//
//	}

}
