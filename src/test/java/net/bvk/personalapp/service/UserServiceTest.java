package net.bvk.personalapp.service;

import net.bvk.personalapp.entity.User;
import net.bvk.personalapp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserServiceTest(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Test
    public void testAdd(){
        assertEquals(3, (2+1));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Bibek",
            "Karuna",
            "ram"
    })
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUsername(name));
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }

    @Test
    public void testUserJournalEntry(){
        User user = userRepository.findByUsername("Bibek");
        assertFalse(user.getJournalEntries().isEmpty());
        assertEquals("Evening", user.getJournalEntries().get(0).getTitle());
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "3,3,6"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }
}
