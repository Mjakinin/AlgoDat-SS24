import java.util.Arrays;

public class Triangle extends ConvexPolygon {

    public Triangle(Vector2D a, Vector2D b, Vector2D c) {
        super(new Vector2D[]{a, b, c});
    }

    public Triangle(Triangle triangle) {
        super(Arrays.copyOf(triangle.vertices, triangle.vertices.length));
    }

    @Override
    public double area() {
        Vector2D a = vertices[0];
        Vector2D b = vertices[1];
        Vector2D c = vertices[2];

        Vector2D side1 = new Vector2D(b.getX() - a.getX(), b.getY() - a.getY());
        Vector2D side2 = new Vector2D(c.getX() - b.getX(), c.getY() - b.getY());
        Vector2D side3 = new Vector2D(a.getX() - c.getX(), a.getY() - c.getY());

        double s1 = side1.length();
        double s2 = side2.length();
        double s3 = side3.length();
        double s = (s1 + s2 + s3) / 2;
        return Math.sqrt(s * (s - s1) * (s - s2) * (s - s3));
    }

    public static void main(String[] args) {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(10, 0);
        Vector2D c = new Vector2D(5, 5);
        Triangle triangle = new Triangle(a, b, c);
        double area = triangle.area();
        System.out.printf("Die Fläche des Dreiecks 'triangle' {%s, %s, %s} beträgt %.1f LE^2.\n", a, b, c, area);
        Triangle triangle2 = new Triangle(triangle);
        System.out.println("triangle2 ist eine Kopie per Copy-Konstruktor von 'triangle': " + triangle2);
        a.setX(-5);
        System.out.println("Eckpunkt 'a', der zur Definition von 'triangle' verwendet wurde, wird geändert.");
        System.out.println("Nun ist der Wert von 'triangle2': " + triangle2);
        /*
        Die erwartete Ausgabe ist:
        Die Fläche des Dreiecks 'triangle' {(0.0, 0.0), (10.0, 0.0), (5.0, 5.0)} beträgt 25,0 LE^2.
        triangle2 ist eine Kopie per Copy-Konstruktor von 'triangle': Triangle{[(0.0, 0.0), (10.0, 0.0), (5.0, 5.0)]}
        Eckpunkt 'a', der zur Definition von 'triangle' verwendet wurde, wird geändert.
        Nun ist der Wert von 'triangle2': Triangle{[(-5.0, 0.0), (10.0, 0.0), (5.0, 5.0)]}
        */
    }
}

