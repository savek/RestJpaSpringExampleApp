package com.savek.RestJpaSpringExampleApp;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RestJpaSpringExampleAppApplication.class,
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceTest {
}
