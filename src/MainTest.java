import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @ParameterizedTest
    @CsvSource(value = {
            "150, 100, 100",
            "100, 150, 100",
            "100, 100, 150"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void testTriangleType1(String a,String b, String c) {
        Assertions.assertEquals("Треугольник равнобедренный", Main.getTriangleInfo(a,b,c).getTypeTriangle());
    }
    @Test
    void testTriangleType2() {
        ResultEntity actual = Main.getTriangleInfo("100","100","100");
        Assertions.assertEquals("Треугольник равносторонний",actual.typeTriangle);
    }
    @Test
    void testTriangleType3() {
        ResultEntity actual = Main.getTriangleInfo("15","10","20");
        Assertions.assertEquals("Треугольник разносторонний",actual.typeTriangle);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "201, 100, 100",
            "100, 201, 100",
            "100, 100, 201",
            "200, 100, 100",
            "100, 200, 100",
            "100, 100, 200",
            "0, 0, 0",
            "0, 100, 150",
            "150, 0, 100",
            "150,100,0"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void testTriangleType4(String a,String b,String c) {
        Assertions.assertEquals("Не треугольник", Main.getTriangleInfo(a,b,c).getTypeTriangle());
    }
    @ParameterizedTest
    @CsvSource(value = {
            "150,100,a",
            "100,a,150",
            "a,100,150",
            " , , ,",

    }, ignoreLeadingAndTrailingWhitespace = false)
    void testTriangleType5(String a, String b, String c) {
        Assertions.assertEquals(" ", Main.getTriangleInfo(a,b,c).getTypeTriangle());
    }
}