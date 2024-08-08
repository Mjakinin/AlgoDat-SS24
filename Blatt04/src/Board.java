import java.util.InputMismatchException;
import java.util.List;

import static java.lang.Math.abs;
/**
 * This class represents a generic TicTacToe game board.
 */
public class Board {
    private int n;
    private int[][] board;
    private int freeFields;


    /**
     *  Creates Board object, am game board of size n * n with 1<=n<=10.
     */
    public Board(int n)
    {
        if(n < 1 || n > 10)
        {
            throw new InputMismatchException("Ungültige Boardgröße");
        }

        this.n = n;
        this.board = new int[n][n];
        this.freeFields = n * n;
    }
    
    /**
     *  @return     length/width of the Board object
     */
    public int getN() { return n; }
    
    /**
     *  @return     number of currently free fields
     */
    public int nFreeFields()
    {
        return freeFields;
    }
    
    /**
     *  @return     token at position pos
     */
    public int getField(Position pos) throws InputMismatchException
    {
        if (pos.x < 0 || pos.x > n || pos.y < 0 || pos.y > n) {
            throw new InputMismatchException("Ungültige Position");
        }


        return board[pos.x][pos.y];
    }

    /**
     *  Sets the specified token at Position pos.
     */    
    public void setField(Position pos, int token) throws InputMismatchException
    {
        if (pos.x < 0 || pos.x > n || pos.y < 0 || pos.y > n) {
            throw new InputMismatchException("Ungültige Position");
        }


        if(token != 0 && token != -1 && token != 1)
        {
            throw new InputMismatchException("Ungültiger Token");
        }

        if(board[pos.x][pos.y] == 0 && token != 0)
        {
            freeFields--;   // X oder O wird gesetzt
        }
        else if(board[pos.x][pos.y] != 0 && token == 0)
        {
            freeFields++;   // freies Feld wird erzeugt
        }

        board[pos.x][pos.y] = token;
    }

    /**
     *  Places the token of a player at Position pos.
     */
    public void doMove(Position pos, int player)
    {
        if(player != 1 && player != -1)
        {
            throw new InputMismatchException("Ungültiger Spieler");
        }

        if(getField(pos) != 0)
        {
            throw new InputMismatchException("Das Feld ist bereits belegt");
        }

        setField(pos, player);
    }

    /**
     *  Clears board at Position pos.
     */
    public void undoMove(Position pos)
    {
        setField(pos, 0);
    }
    
    /**
     *  @return     true if game is won, false if not
     */
    public boolean isGameWon()
    {
        for(int i = 0; i < n; i++)
        {
            int reihe = 0;

            for(int j = 0; j < n; j++)
            {
                reihe += board[i][j];
            }

            if(Math.abs(reihe) == n) //Betrag der Tokens muss eine Reihe ergeben
            {
                return true;
            }
        }

        for(int j = 0; j < n; j++)
        {
            int spalte = 0;

            for(int i = 0; i < n; i++)
            {
                spalte += board[i][j];
            }

            if(Math.abs(spalte) == n) //Betrag der Tokens muss eine Spalte ergeben
            {
                return true;
            }
        }



        int diagonal1 = 0;

        for(int i = 0; i < n; i++)
        {
            diagonal1 += board[i][i];
        }

        if(Math.abs(diagonal1) == n)  //Betrag der Tokens muss eine Diagonale ergeben (Links oben nach rechts unten)
        {
            return true;
        }




        int diagonal2 = 0;

        for(int i = 0; i < n; i++)
        {
            diagonal2 += board[i][n - 1 - i];
        }

        if(Math.abs(diagonal2) == n)  //Betrag der Tokens muss eine Diagonale ergeben (Links unten nach rechts oben)
        {
            return true;
        }

        return false;
    }


    /**
     *  @return     set of all free fields as some Iterable object
     */
    public Iterable<Position> validMoves()
    {
        Position[] moves = new Position[freeFields];
        int index = 0;

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(board[i][j] == 0)
                {
                    moves[index++] = new Position(i, j);
                }
            }
        }

        return List.of(moves);
    }

    /**
     *  Outputs current state representation of the Board object.
     *  Practical for debugging.
     */
    public void print()
    {
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                System.out.print(board[i][j] + " ");
            }

            System.out.println();
        }
    }
}


