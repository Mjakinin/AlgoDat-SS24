import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        // Erstelle ein neues Board mit der Größe 3x3
        Board board = new Board(4);

        // Setze einige Züge
        try {
            board.doMove(new Position(0, 0), 1);
            board.doMove(new Position(1, 1), -1);
            board.doMove(new Position(0, 2), 1);
            board.doMove(new Position(1, 3), -1);
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

        // Gib das aktuelle Board aus
        System.out.println("Aktuelles Board:");
        board.print();

        // Überprüfe, ob das Spiel gewonnen wurde
        if (board.isGameWon()) {
            System.out.println("Spiel gewonnen!");
        } else {
            System.out.println("Spiel läuft noch.");
        }

        // Gib die Anzahl der freien Felder aus
        System.out.println("Freie Felder: " + board.nFreeFields());

        // Gib alle gültigen Züge aus
        System.out.println("Gültige Züge:");
        for (Position move : board.validMoves()) {
            System.out.println(move);
        }

        // Mache einen Zug rückgängig
        board.undoMove(new Position(1, 2));

        // Gib das Board nachdem ein Zug rückgängig gemacht wurde aus
        System.out.println("Board nachdem ein Zug rückgängig gemacht wurde:");
        board.print();
    }
}
