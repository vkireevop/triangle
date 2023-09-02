import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        String a = "12";
//        String b = "12";
//        String c = "12";
//       ArrayList<CoordinantForTriangle> result = CoordinantChecker(a,b,c);
//        for (CoordinantForTriangle item : result) {
//            System.out.println(item);
//        }

    }
    public static void triangleChecker (String a, String b, String c) {

        // conver to float

        // exptions

        if (a == b && b==c )
        {
            System.out.println("Треугольник равностороний");
        } else if ((a == b && c < a + b) || (b == c && a < a+b) || (a == c && b < a+c)) {
            System.out.println("Треугольник равнобедренный");
        } else if (c < a+b && a< a+c && b < a+c)
        {
            System.out.println( "Треугольник разностороний");
        } else {
            System.out.println(" Это не треугольник");
        }
    }
    public static ArrayList<CoordinantForTriangle> CoordinantChecker( String a, String b, String c)
    {
        triangleChecker(a,b,c);
        CoordinantForTriangle A = new CoordinantForTriangle( 0, 0 );
        CoordinantForTriangle B = new CoordinantForTriangle(a, 0);
        float angleC = (float) Math.acos((a*a+b*b-c*c)/(2*a*b));
        float xC = (float) (a * Math.cos(angleC));
        float yC = (float) (a * Math.sin(angleC));
        CoordinantForTriangle C = new CoordinantForTriangle(xC,yC);
        ArrayList<CoordinantForTriangle> result = new ArrayList<>();
        result.add(A);
        result.add(B);
        result.add(C);
        return result;
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
    }
