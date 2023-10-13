import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationCheckerTest {

    @ParameterizedTest
    @CsvSource({
            "user44, Password123!, Password123!, True",
            "user77, Password123!, Password123!, True",
            "user@dsd.com, Password123!, Password123!, True",
            "+2-123-456-7890, Password123!, Password123!, True"
    })
    void testSuccessfulRegister(String login, String password, String password2, String expectedResult) {
        String[] result = RegistrationChecker.checkRegister(login, password, password2);
        assertEquals(expectedResult, result[0]);
    }
    @ParameterizedTest
    @CsvSource({
            "user@domain.com, password123, password123, False, В пароле отсутствует минимум одна заглавная буква",
            "user123@, Password!123, Password!123, False, Email не удовлетворяет общему формату xxx@xxx.xxx",
            "+123-456-7890, password123, password123, False, Номер телефона не удовлетворяет заданному формату +x-xxx-xxx-xxxx",
            "user55, pass, pass, False, Длина пароля меньше 7 символов",
            "user10, password123, password124, False, Заданные пароли не совпадают",
            "user99, Password123!, Password123!, False, Пользователь с таким логином уже зарегистрирован",
            "us, password123, password123, False, Длина логина меньше 5 символов",
            "user77, password123, password12, False, Заданные пароли не совпадают",
            "user123, password123, password123, False, В пароле отсутствует минимум одна заглавная буква",
            "userdomaincom@, Password123!, Password123!, False, Email не удовлетворяет общему формату xxx@xxx.xxx",
            "+123-456-7890, password123, password123, False, Номер телефона не удовлетворяет заданному формату +x-xxx-xxx-xxxx",
            "user321, 1, 1, False, Длина пароля меньше 7 символов",
            "user10, password123, password124, False, Заданные пароли не совпадают",
            "user99, password123, password123, False, Пользователь с таким логином уже зарегистрирован",
            "us, password123, password123, False, Длина логина меньше 5 символов",
            "user77, password123, password12, False, Заданные пароли не совпадают",
            "user11, password123, password123, False, Пользователь с таким логином уже зарегистрирован"
    })
    void testFailedRegister(String login, String password, String password2, String expectedResult, String errorMessage) {
        String[] result = RegistrationChecker.checkRegister(login, password, password2);
        assertEquals(expectedResult, result[0]);
        assertEquals(errorMessage, result[1]);
    }
}

