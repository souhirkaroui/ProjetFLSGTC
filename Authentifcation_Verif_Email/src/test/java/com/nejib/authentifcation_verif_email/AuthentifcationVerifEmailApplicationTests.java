package com.nejib.authentifcation_verif_email;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:testdb")
class AuthentifcationVerifEmailApplicationTests {

    @Test
    void contextLoads() {
    }

}
