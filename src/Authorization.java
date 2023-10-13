import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Authorization {

    public static String[] checkRegister(String login, String password, String password2) {
        String[] result;

        result = checkLoginLength(login);
        if (!result[0].equals("True")) return result;

        result = checkExistUser(login);
        if (!result[0].equals("True")) return result;

        result = checkLoginContent(login);
        if (!result[0].equals("True")) return result;

        result = checkPasswordLength(password);
        if (!result[0].equals("True")) return result;

        result = checkPasswordEquality(password, password2);
        if (!result[0].equals("True")) return result;

        return checkPasswordContent(password);
    }

    public static String[] checkLoginLength(String login) {
        int length = login.length();

        if (length <= 0)
            return new String[]{"False", "Пустая строка в качестве логина"};
        else if (length <= 5)
            return new String[]{"False", "Длина логина меньше 5 символов"};
        else
            return new String[]{"True", ""};
    }

    public static String[] checkExistUser(String login) {
        List<String> registeredUsers = new ArrayList<>();
        registeredUsers.add("user11");
        registeredUsers.add("user22");
        registeredUsers.add("user33");

        if (registeredUsers.contains(login.toLowerCase()))
            return new String[]{"False", "Пользователь с таким логином уже зарегистрирован"};
        else
            return new String[]{"True", ""};
    }

    public static String[] checkLoginContent(String login) {
        boolean reLogin = Pattern.matches("^[a-zA-Z0-9_]+", login);
        boolean reMail = Pattern.matches("^\\w+@\\w+\\.\\w+$", login);
        boolean rePhone = Pattern.matches("^\\+\\d{1,3}-\\d{3}-\\d{3}-\\d{4}$", login);

        if (rePhone || reMail || reLogin)
            return new String[]{"True", ""};

        if (login.contains("+") && !rePhone)
            return new String[]{"False", "Номер телефона не удовлетворяет заданному формату +x-xxx-xxx-xxxx"};

        if (login.contains("@") && !reMail)
            return new String[]{"False", "Email не удовлетворяет общему формату xxx@xxx.xxx"};

        if (!reLogin)
            return new String[]{"False", "Логин содержит символы, отличные от латиницы, цифр и знака подчеркивания"};
        else
            throw new RuntimeException("Неизвестная ошибка валидации логина. Этот кейс логически невозможен.");
    }

    public static String[] checkPasswordLength(String password) {
        int length = password.length();

        if (length <= 0)
            return new String[]{"False", "Пустая строка в качестве пароля"};
        else if (length < 7)
            return new String[]{"False", "Длина пароля меньше 7 символов"};
        else
            return new String[]{"True", ""};
    }

    public static String[] checkPasswordEquality(String password, String password2) {
        if (!password.equals(password2))
            return new String[]{"True", "OK"};
        else
            return new String[]{"False", "Заданные пароли совпадают"};
    }

    public static String[] checkPasswordContent(String password) {
        boolean reCommonPasswd = Pattern.matches("^[А-Яа-я0-9!@#\\$%\\^&\\*\\(\\)_\\+`~\\-=\\[\\]\\{\\}\\|\\\\:;\",\\.<>\\?/]+$", password);
        boolean reUpLetter = Pattern.matches(".*[А-Я].*", password);
        boolean reDownLetter = Pattern.matches(".*[а-я].*", password);
        boolean reDigit = Pattern.matches(".*\\d.*", password);
        boolean reSpecialSymbol = Pattern.matches(".*[!@#\\$%\\^&\\*\\(\\)_\\+`~\\-=\\[\\]\\{\\}\\|\\\\:;\",\\.<>\\?/].*", password);

        if (reCommonPasswd && reUpLetter && reDownLetter && reDigit && reSpecialSymbol)
            return new String[]{"True", ""};

        if (!reCommonPasswd)
            return new String[]{"False", "В пароле присутствуют недопустимые символы"};

        if (!reUpLetter)
            return new String[]{"False", "В пароле отсутствует минимум одна заглавная буква"};

        if (!reDownLetter)
            return new String[]{"False", "В пароле отсутствует минимум одна строчная буква"};

        if (!reDigit)
            return new String[]{"False", "В пароле отсутствует минимум одна цифра"};

        if (!reSpecialSymbol)
            return new String[]{"False", "В пароле отсутствует минимум один специальный символ"};
        else
            throw new RuntimeException("Неизвестная ошибка валидации пароля. Этот кейс логически невозможен.");
    }
}

