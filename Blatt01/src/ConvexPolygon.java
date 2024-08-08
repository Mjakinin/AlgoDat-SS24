import java.util.Arrays;


public class ConvexPolygon extends Polygon {

    public ConvexPolygon(Vector2D[] vertices) {
        this.vertices = vertices;
    }

    @Override
    public double area() {

        //vectices[0] festhalten
        int i = 1;
        double lsg = 0;

        while (i < vertices.length - 1) {
            // Herons Formel zur Bestimmung des Flächeninhalts eines Dreiecks
            // Verwendung dieses Codes (mit notwendiger Anpassung) in Triangle -> area() erlaubt :)
            Vector2D a = vertices[0];            //FESTGELEGT !!!
            Vector2D b = vertices[i];
            Vector2D c = vertices[i + 1];
            Vector2D side1 = new Vector2D(b.getX() - a.getX(), b.getY() - a.getY());
            Vector2D side2 = new Vector2D(c.getX() - b.getX(), c.getY() - b.getY());
            Vector2D side3 = new Vector2D(a.getX() - c.getX(), a.getY() - c.getY());
            double s1 = side1.length();
            double s2 = side2.length();
            double s3 = side3.length();
            double s = (s1 + s2 + s3) / 2;
            double area = Math.sqrt(s * (s - s1) * (s - s2) * (s - s3));

            lsg += area;

            i++;
        }
        return lsg;
    }

    @Override
    public double perimeter() {
        int i = 0;
        double lsg = 0;

        while (i < vertices.length - 1) {
            // Herons Formel zur Bestimmung des Flächeninhalts eines Dreiecks
            // Verwendung dieses Codes (mit notwendiger Anpassung) in Triangle -> area() erlaubt :)
            Vector2D a = vertices[i];
            Vector2D b = vertices[i + 1];
            Vector2D side1 = new Vector2D(b.getX() - a.getX(), b.getY() - a.getY());
            double s1 = side1.length();
            double s = s1;

            lsg += s;
            i++;
        }

        //Letze Seite berechnen
        Vector2D a = vertices[i];
        Vector2D b = vertices[0];
        Vector2D side1 = new Vector2D(b.getX() - a.getX(), b.getY() - a.getY());
        double s1 = side1.length();
        double s = s1;

        lsg += s;

        return lsg;
    }

    @Override
    public String toString() {
        return "ConvexPolygon(" + Arrays.toString(vertices) + ")";
    }

    public static Polygon[] somePolygons() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(10, 0);
        Vector2D c = new Vector2D(5, 5);
        Vector2D d = new Vector2D(10, -5);
        Vector2D e = new Vector2D(12, 2);
        Vector2D f = new Vector2D(3, 17);

        Polygon[] polygons = new Polygon[4];
        polygons[0] = new ConvexPolygon(new Vector2D[]{a, b, c});
        polygons[1] = new ConvexPolygon(new Vector2D[]{a, d, e, f});
        polygons[2] = new RegularPolygon(5, 1);
        polygons[3] = new RegularPolygon(6, 1);

        return polygons;
    }

    public static double totalArea(Polygon[] polygons) {
        double totalArea = 0;
        for (Polygon polygon : polygons) {
            totalArea += polygon.area();
        }
        return totalArea;
    }
}


