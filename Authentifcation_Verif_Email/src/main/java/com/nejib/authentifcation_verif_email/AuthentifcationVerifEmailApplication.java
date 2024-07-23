package com.nejib.authentifcation_verif_email;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication


@EnableAspectJAutoProxy
@RequiredArgsConstructor
@ComponentScan(basePackages={"com.nejib.authentifcation_verif_email" ,"com.nejib.authentifcation_verif_email.CorsCongiguration"})
public class AuthentifcationVerifEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthentifcationVerifEmailApplication.class, args);
    }

}
