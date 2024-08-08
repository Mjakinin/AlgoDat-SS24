/**
 * This class implements and evaluates game situations of a TicTacToe game.
 */
public class TicTacToe {



    /**
     * Returns an evaluation for player at the current board state.
     * Arbeitet nach dem Prinzip der Alphabeta-Suche. Works with the principle of Alpha-Beta-Pruning.
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
     * @return          rating of game situation from player's point of view
    **/
    public static int alphaBeta(Board board, int player)
    {
        return alphaBeta(board, player, Integer.MIN_VALUE, Integer.MAX_VALUE, 0) * player;
    }

    private static int alphaBeta(Board board, int player, int alpha, int beta, int depth)
    {
        if(board.isGameWon())
        {
            return (board.nFreeFields() + 1) * -player;
        }
        if(board.nFreeFields() == 0)
        {
            return 0;
        }


        if(player == 1)
        {
            int maxEval = Integer.MIN_VALUE;

            for(Position move : board.validMoves())
            {
                board.doMove(move, player);
                int eval = alphaBeta(board, -player, alpha, beta, depth + 1);
                board.undoMove(move);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);

                if(beta <= alpha)
                {
                    break; //Beta cutoff
                }
            }
            return maxEval;

        }
        else
        {
            int minEval = Integer.MAX_VALUE;

            for(Position move : board.validMoves())
            {
                board.doMove(move, player);
                int eval = alphaBeta(board, -player, alpha, beta, depth + 1);
                board.undoMove(move);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);

                if(beta <= alpha)
                {
                    break; //Alpha cutoff
                }
            }
            return minEval;
        }

    }



        /* Beispiel BestDifference
        if (board.isGameWon())
        {
            return board.score();
        }
        for (int move : Game.moves) {
            game.doMove(move);
            int score = scorePlayerB(alpha,
                    beta);
            game.undoMove(move);
            if (score > alpha) {
                alpha = score;
                if (alpha >= beta) break;
            }
        }
        return alpha;

         */




    
    /**
     * Vividly prints a rating for each currently possible move out at System.out.
     * (from player's point of view)
     * Uses Alpha-Beta-Pruning to rate the possible moves.
     * formatting: See "Beispiel 1: Bewertung aller Zugmöglichkeiten" (Aufgabenblatt 4).
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
    **/
    public static void evaluatePossibleMoves(Board board, int player)
    {
        if(player == 1)
        {
            System.out.println("Evaluation for player 'x':");
        }
        else
        {
            System.out.println("Evaluation for player 'o':");
        }

        for(int i = 0; i < board.getN(); i++)
        {
            for(int j = 0; j < board.getN(); j++)
            {
                Position position = new Position(j, i);

                if(board.getField(position) == 0)
                {
                    board.doMove(position, player);
                    int bewertung = -alphaBeta(board, -player);
                    board.undoMove(position);

                    System.out.print(String.format("    " + bewertung));
                }
                else
                {
                    char spieler;
                    if(board.getField(position) == 1)
                    {
                        spieler = 'x';
                    }
                    else
                    {
                        spieler = 'o';
                    }
                    System.out.print(String.format("    " + spieler));
                }

            }

            System.out.println();
        }

    }


    public static void main(String[] args)
    {
        Board board1 = new Board(3);
        // x . x
        // . x o
        // o . o
        board1.setField(new Position(0, 0), 1); // x
        board1.setField(new Position(2, 0), 1); // x
        board1.setField(new Position(1, 1), 1); // x
        board1.setField(new Position(2, 2), -1); // o
        board1.setField(new Position(2, 1), -1); // o
        board1.setField(new Position(0, 2), -1); // o


        board1.print();

        System.out.println(alphaBeta(board1, 1)); //-3 muss Ergebnis sein


        Board board = new Board(3);
        // . o .
        // . . .
        // . . x
        board.setField(new Position(0, 1), -1); // o
        board.setField(new Position(2, 2), 1); // x

        evaluatePossibleMoves(board, 1);

    }

}

