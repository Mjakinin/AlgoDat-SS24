import java.util.LinkedList;

/**
 * ParitialSolution provides at least the functionality which is required
 * for the use in searching for solutions of the game in a search tree.
 * It can store a game situation (Board) and a sequence of mooves.
 */
public class PartialSolution {

    /* TODO */
    /* Add object variables, constructors, methods as required and desired.      */

    Board board;
    private LinkedList<Move> moveSequence;



    public PartialSolution(Board board) {
        this.board = board;
        this.moveSequence = new LinkedList<>();
    }

    public void addMove(Move move) {
        moveSequence.add(move);
        board.doMove(move);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
        {
            return true;
        }

        if(!(o instanceof PartialSolution))
        {
            return false;
        }

        PartialSolution other = (PartialSolution) o;
        return board.equals(other.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }


    /**
     * Return the sequence of moves which resulted in this partial solution.
     *
     * @return The sequence of moves.
     */
    public LinkedList<Move> moveSequence() {
        /* TODO */
        return moveSequence;
    }

    @Override
    public String toString() {
        String str = "";
        int lastRobot = -1;
        for (Move move : moveSequence()) {
            if (lastRobot == move.iRobot) {
                str += " -> " + move.endPosition;
            } else {
                if (lastRobot != -1) {
                    str += ", ";
                }
                str += "R" + move.iRobot + " -> " + move.endPosition;
            }
            lastRobot = move.iRobot;
        }
        return str;
    }
}

