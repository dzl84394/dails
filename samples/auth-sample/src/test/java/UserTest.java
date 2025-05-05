import cn.dails.AuthLauncher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = AuthLauncher.class)
public class UserTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testPasswordMatch() {
        String rawPassword = "123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("加密后的密码: " + encodedPassword);
//        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }


}
