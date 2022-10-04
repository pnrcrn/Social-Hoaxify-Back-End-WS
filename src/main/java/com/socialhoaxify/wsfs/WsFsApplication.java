package com.socialhoaxify.wsfs;

import com.socialhoaxify.wsfs.model.UserInformation;
import com.socialhoaxify.wsfs.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)//bütün endpointleri secure etme özelliği kalkmış oluyor
@SpringBootApplication
public class WsFsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsFsApplication.class, args);
	}

	//h2 ile sürekli kayıt oluşturmaktansa çalıştırırken bizim için testi kolay olsun diye user objesi oluşturduk
	//spring bean de bunu runtime anında derleyip obje oluşturup user bigilerini run edecek
	@Bean
	CommandLineRunner createInitialUsers(UserService userService){
		return ((args) -> {
				UserInformation userInformation=new UserInformation();
				userInformation.setUsername("user1");
				userInformation.setDisplayName("hello kity");
				userInformation.setPassword("Password12");
				userService.save(userInformation);
		});


//		return new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//				UserInformation userInformation=new UserInformation();
//				userInformation.setUsername("user1");
//				userInformation.setDisplayName("hello kity");
//				userInformation.setPassword("Password12");
//				userService.save(userInformation);
//			}
//		};

	}
}
