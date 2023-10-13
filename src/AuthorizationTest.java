import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class AuthorizationTest {

    @ParameterizedTest
    @CsvSource({
            "user11, password123, password123, False, Пользователь с таким логином уже зарегистрирован",
            "user44, password123, password123, True, ",
            "user55, password1, password1, False, Длина пароля меньше 7 символов",
            "user66, password123, password124, False, Заданные пароли совпадают",
            "user77, password123, password123, True, ",
            "user88, password123, password123, False, Логин содержит символы, отличные от латиницы, цифр и знака подчеркивания"
    })
    void testCheckRegister(String login, String password, String password2, String expectedResult, String errorMessage) {
        String[] result =  Authorization.checkRegister(login, password, password2);
        assertEquals(expectedResult, result[0]);
        assertEquals(errorMessage, result[1]);
    }

    @ParameterizedTest
    @CsvSource({
            ", False, Пустая строка в качестве логина",
            "user, False, Длина логина меньше 5 символов",
            "username, True, "
    })
    void testCheckLoginLength(String login, String expectedResult, String errorMessage) {
        String[] result = Authorization.checkLoginLength(login);
        assertEquals(expectedResult, result[0]);
        assertEquals(errorMessage, result[1]);
    }

    @ParameterizedTest
    @CsvSource({
            "user11, False, Пользователь с таким логином уже зарегистрирован",
            "user44, True, "
    })
    void testCheckExistUser(String login, String expectedResult, String errorMessage) {
        String[] result = Authorization.checkExistUser(login);
        assertEquals(expectedResult, result[0]);
        assertEquals(errorMessage, result[1]);
    }

    @ParameterizedTest
    @CsvSource({
            "user11, False, Логин содержит символы, отличные от латиницы, цифр и знака подчеркивания",
            "user@mail.com, True, ",
            "+123-456-7890, False, Номер телефона не удовлетворяет заданному формату +x-xxx-xxx-xxxx"
    })
    void testCheckLoginContent(String login, String expectedResult, String errorMessage) {
        String[] result = Authorization.checkLoginContent(login);
        assertEquals(expectedResult, result[0]);
        assertEquals(errorMessage, result[1]);
    }

    @ParameterizedTest
    @CsvSource({
            ", False, Пустая строка в качестве пароля",
            "pass, False, Длина пароля меньше 7 символов",
            "password, True, "
    })
    void testCheckPasswordLength(String password, String expectedResult, String errorMessage) {
        String[] result = Authorization.checkPasswordLength(password);
        assertEquals(expectedResult, result[0]);
        assertEquals(errorMessage, result[1]);
    }

    @ParameterizedTest
    @CsvSource({
            "password, password, True, OK",
            "password1, password2, False, Заданные пароли совпадают"
    })
    void testCheckPasswordEquality(String password, String password2, String expectedResult, String errorMessage) {
        String[] result = Authorization.checkPasswordEquality(password, password2);
        assertEquals(expectedResult, result[0]);
        assertEquals(errorMessage, result[1]);
    }

    @ParameterizedTest
    @CsvSource({
            "password123, False, В пароле присутствуют недопустимые символы",
            "Password123!, True, "
    })
    void testCheckPasswordContent(String password, String expectedResult, String errorMessage) {
        String[] result = Authorization.checkPasswordContent(password);
        assertEquals(expectedResult, result[0]);
        assertEquals(errorMessage, result[1]);
    }
}
