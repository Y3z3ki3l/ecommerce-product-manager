package com.example.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Usa el perfil de pruebas
class EcommerceApplicationTests {

	@Test
	void contextLoads() {
	}

}
