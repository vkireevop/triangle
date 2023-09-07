import java.util.ArrayList;
import java.util.List;
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
try {
        String sideA = "1000";
        String sideB = "1000";
        String sideC = "1000";

        List<CoordinantForTriangle> vertices = calculateVertices(sideA, sideB, sideC);
        String triangleType = determineTriangleType(sideA,sideB,sideC);
        if (!triangleType.equals(" ")) {
            LOGGER.info("Тип треугольника: " + determineTriangleType(sideA, sideB, sideC));

            LOGGER.info("Координаты вершин:");
            for (int i = 0; i < 3; i++) {
                LOGGER.info("Вершина " + (char)('А' + i) + ": (" + vertices.get(i).getX() + ", " + vertices.get(i).getY() + ")");
            }
            LOGGER.info("----------------------------------------");
        } else {
            LOGGER.info("Тип треугольника: Не треугольник");
            LOGGER.info("Координаты вершин: (-1, -1), (-1, -1), (-1, -1)");
            LOGGER.info("----------------------------------------");
        }

    } catch (NumberFormatException e) {
        LOGGER.warning("Введены некорректные данные: " + e.getMessage());
        LOGGER.info("Тип треугольника: ");
        LOGGER.info("Координаты вершин: (-2, -2), (-2, -2), (-2, -2)");
        LOGGER.info("----------------------------------------");
    }

}


    public static List<CoordinantForTriangle> calculateVertices (String a, String b, String c) {

        float A = Float.parseFloat(a);
        float B = Float.parseFloat(b);
        float C = Float.parseFloat(c);
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
    public static String determineTriangleType(String a, String b, String c) {

        float A = Float.parseFloat(a);
        float B = Float.parseFloat(b);
        float C = Float.parseFloat(c);

        if (A == B && B == C) {
            return "Треугольник равносторонний";
        } else if ((A == B && C < A + B) || (B == C && A < C + B) || (A == C && B < A + C)) {
            return "Треугольник равнобедренный";
        } else if (C < A + B && A < B + C && B < A + C) {
            return "Треугольник разносторонний";
        } else {
            return " ";
        }
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
