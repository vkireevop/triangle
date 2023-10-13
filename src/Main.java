import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("triangle_log.txt",true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String[] result = RegistrationChecker.checkRegister("user44", "Password123!", "Password123!");
        System.out.println(result[0] + " - " + result[1]);

        result = RegistrationChecker.checkRegister("user11", "password123", "password123");
        System.out.println(result[0] + " - " + result[1]);

    }



    public static ResultEntity getTriangleInfo(String a, String b, String c)
    {

        try {
            float A = Float.parseFloat(a);
            float B = Float.parseFloat(b);
            float C = Float.parseFloat(c);

            List<CoordinantForTriangle> vertices = calculateVertices(A, B, C);
            String triangleType = determineTriangleType(A,B,C);


            if (!triangleType.equals(" ")) {
                LOGGER.info("Тип треугольника: " + determineTriangleType(A, B, C));
                LOGGER.info("Координаты вершин:");

                for (int i = 0; i < 3; i++) {
                    LOGGER.info("Вершина " + (char)('А' + i) + ": (" + vertices.get(i).getX() + ", " + vertices.get(i).getY() + ")");
                }
                LOGGER.info("----------------------------------------");
                return new ResultEntity(determineTriangleType(A,B,C), new CoordinantForTriangle(vertices.get(0).getX(),vertices.get(0).getY()),
                        new CoordinantForTriangle(vertices.get(1).getX(),vertices.get(1).getY()),new CoordinantForTriangle(vertices.get(2).getX(),vertices.get(2).getY()));
            } else {
                LOGGER.info("Тип треугольника: Не треугольник");
                LOGGER.info("Координаты вершин: (-1, -1), (-1, -1), (-1, -1)");
                LOGGER.info("----------------------------------------");

            }
            return null;

        } catch (NumberFormatException e) {

            LOGGER.warning("Введены некорректные данные: " + e.getMessage());
            LOGGER.info("Тип треугольника: ");
            LOGGER.info("Координаты вершин: (-2, -2), (-2, -2), (-2, -2)");
            LOGGER.info("----------------------------------------");

            return new ResultEntity(" ", new CoordinantForTriangle(-2,-2), new CoordinantForTriangle(-2,-2)
            , new CoordinantForTriangle(-2,-2));
        }


    }
    public static List<CoordinantForTriangle> calculateVertices (float A, float B, float C) {
        CoordinantForTriangle Ac = new CoordinantForTriangle( 0, 0 );
        CoordinantForTriangle Bc = new CoordinantForTriangle(A, 0);
        float angleC = (float) Math.acos((A * A + B * B - C * C)/(2 * A * B));
        float xC = (float) (A * Math.cos(angleC));
        float yC = (float) (A * Math.sin(angleC));
        CoordinantForTriangle Cc = new CoordinantForTriangle(xC,yC);
        double scaleFactor = 100.0 / Math.max(A, Math.max(B, C));
        Ac.setLocation(Math.round(Ac.getX() * scaleFactor), Math.round(Ac.getY() * scaleFactor));
        Bc.setLocation(Math.round(Bc.getX() * scaleFactor), Math.round(Bc.getY() * scaleFactor));
        Cc.setLocation(Math.round(Cc.getX() * scaleFactor), Math.round(Cc.getY() * scaleFactor));
        ArrayList<CoordinantForTriangle> result = new ArrayList<>();
        result.add(Ac);
        result.add(Bc);
        result.add(Cc);
        return result;
    }
    public static String determineTriangleType(float A, float B, float C) {

        if (A != 0 && B != 0 && C!=0) {
            if (A == B && B == C) {
                return "Треугольник равносторонний";
            } else if ((A == B && C < A + B) || (B == C && A < C + B) || (A == C && B < A + C)) {
                return "Треугольник равнобедренный";
            } else if (C < A + B && A < B + C && B < A + C) {
                return "Треугольник разносторонний";
            } else {
                return "Не треугольник";
            }
        } else {
            return "Не треугольник";
        }
    }

}
class ResultEntity {
    String typeTriangle;
    CoordinantForTriangle A;
    CoordinantForTriangle B;
    CoordinantForTriangle C;

    public ResultEntity(String typeTriangle, CoordinantForTriangle a, CoordinantForTriangle b, CoordinantForTriangle c) {
        this.typeTriangle = typeTriangle;
        A = a;
        B = b;
        C = c;
    }

    public String getTypeTriangle() {
        return typeTriangle;
    }

    public void setTypeTriangle(String typeTriangle) {
        this.typeTriangle = typeTriangle;
    }

    public CoordinantForTriangle getA() {
        return A;
    }

    public void setA(CoordinantForTriangle a) {
        A = a;
    }

    public CoordinantForTriangle getB() {
        return B;
    }

    public void setB(CoordinantForTriangle b) {
        B = b;
    }

    public CoordinantForTriangle getC() {
        return C;
    }

    public void setC(CoordinantForTriangle c) {
        C = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultEntity that = (ResultEntity) o;
        return Objects.equals(typeTriangle, that.typeTriangle) && Objects.equals(A, that.A) && Objects.equals(B, that.B) && Objects.equals(C, that.C);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeTriangle, A, B, C);
    }
}

class CoordinantForTriangle {

        float x;
        float y;
        CoordinantForTriangle( float x, float y)
        {
            this.x = x;
            this.y = y;

        }
        @Override
        public String toString(){
            return x + " " + y;
        }
        public void setLocation (float x, float y) {
            this.x = x;
            this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinantForTriangle that = (CoordinantForTriangle) o;
        return Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
