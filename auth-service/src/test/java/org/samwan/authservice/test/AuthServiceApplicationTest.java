package org.samwan.authservice.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.samwan.authservice.models.User;
import org.samwan.authservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 *  annotation tells Spring Boot to look for a main configuration class (one with @SpringBootApplication, for instance)
 *  and use that to start a Spring application context. You can run this test in your IDE or on the command line (by running ./mvnw test or ./gradlew test), and it should pass.
 */
@SpringBootTest
public class AuthServiceApplicationTest {

    //@Autowired
    private UserRepository userRepository = mock(UserRepository.class);
    @Test
    public void contextLoads() throws Exception{
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("samwax2010@gmail.com");
        userRepository.save(user);

        assertThat(user.getId()).isEqualTo(1L);
    }
}
