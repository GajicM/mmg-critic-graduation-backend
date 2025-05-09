package raf.diplomski.mmgcritic;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "raf.diplomski.mmgcritic.repositories")
@SecurityScheme(name = "mmgApi", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class MmgCriticApplication {

    public static void main(String[] args) {
        SpringApplication.run(MmgCriticApplication.class, args);
    }

}
