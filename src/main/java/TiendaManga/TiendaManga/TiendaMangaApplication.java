package TiendaManga.TiendaManga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "TiendaManga.Repository")
@EntityScan(basePackages = "TiendaManga.Model")
public class TiendaMangaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaMangaApplication.class, args);
	}

	

}
