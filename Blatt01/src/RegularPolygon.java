// Diese Klasse implementiert nur *zentrierte* reguläre Polygone, also mit midpoint = (0, 0).

import org.w3c.dom.ls.LSOutput;

public class RegularPolygon extends ConvexPolygon {

    private int N;
    private double radius;

    public RegularPolygon(int N, double radius) {
        super(null);
        Vector2D[] vertices = new Vector2D[N];
        for (int i = 0; i < N; i++) {
            vertices[i] = new Vector2D(radius * Math.cos(2 * Math.PI * i / N), radius * Math.sin(2 * Math.PI * i / N));
        }
        super.vertices = vertices;
        this.N = N;
        this.radius = radius;
    }


    public RegularPolygon(RegularPolygon polygon) {
        super(new Vector2D[polygon.vertices.length]);
        for (int i = 0; i < polygon.vertices.length; i++) {
            this.vertices[i] = new Vector2D(polygon.vertices[i]);
        }
        this.N = polygon.N;
        this.radius = polygon.radius;
    }


    public void resize(double newradius) {
        for (int i = 0; i < vertices.length; i++) {
            Vector2D vector = vertices[i];
            vector.setX((newradius / this.radius) * vector.getX());
            vector.setY((newradius / this.radius) * vector.getY());
        }
        this.radius = newradius;
    }


    @Override
    public String toString() {
        return "RegularPolygon{N=" + N + ", radius=" + radius + "}";
    }

    public static void main(String[] args) {
        RegularPolygon pentagon = new RegularPolygon(5, 1);
        System.out.println("Der Flächeninhalt des " + pentagon + " beträgt " + pentagon.area() + " LE^2.");
        //RegularPolygon otherpentagon = pentagon;      // Dies funktioniert nicht!
        RegularPolygon otherpentagon = new RegularPolygon(pentagon);
        pentagon.resize(10);
        System.out.println("Nach Vergrößerung: " + pentagon + " mit Fläche " + pentagon.area() + " LE^2.");
        System.out.println("Die Kopie: " + otherpentagon + " mit Fläche " + otherpentagon.area() + " LE^2.");
        /*
        Die erwartete Ausgabe ist:
        Der Flächeninhalt des RegularPolygon{N=5, radius=1.0} beträgt 2.377641290737883 LE^2.
        Nach Vergrößerung: RegularPolygon{N=5, radius=10.0} mit Fläche 237.7641290737884 LE^2.
        Die Kopie: RegularPolygon{N=5, radius=1.0} mit Fläche 2.377641290737883 LE^2.
        */
    }
}

