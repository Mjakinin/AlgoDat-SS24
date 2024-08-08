import java.util.*;

/**
 * The class {@code Bettelmann} simulated the card game 'Bettelmann'. You can construct objects
 * either by providing the piles of cards of the two players, or by requesting a shuffled
 * distribution of cards.
 */
public class Bettelmann {
    private Deque<Card> closedPile1;
    private Deque<Card> closedPile2;
    private int winner = -1;

    /**
     * Constructor which initializes both players with empty piles.
     */
    public Bettelmann()
    {
        closedPile1 = new LinkedList<>();
        closedPile2 = new LinkedList<>();
    }

    /**
     * Constructor which initializes both players with the provided piles of cards.
     *
     * @param pile1 pile of cards of player 1.
     * @param pile2 pile of cards of player 2.
     */
    public Bettelmann(Deque<Card> pile1, Deque<Card> pile2) {
        closedPile1 = pile1;
        closedPile2 = pile2;
    }

    /**
     * Returns the closed pile of player 1 (required for the tests).
     *
     * @return The closed pile of player 1.
     */
    public Deque<Card> getClosedPile1() {
        return closedPile1;
    }

    /**
     * Returns the closed pile of player 2 (required for the tests).
     *
     * @return The closed pile of player 2.
     */
    public Deque<Card> getClosedPile2() {
        return closedPile2;
    }

    /**
     * Play one round of the game. This includes drawing more cards, when both players
     * have drawn cards of the same rank. At the end of the round, the player with the
     * higher ranked card wins the trick, so all drawn cards from that round are added
     * to the bottom of her/his closed pile of cards.
     */
    public void playRound() {

        ergebnis();

        Queue<Card> openPile1 = new LinkedList<>();
        Queue<Card> openPile2 = new LinkedList<>();

        Card PlayerCard1 = closedPile1.poll();
        Card PlayerCard2 = closedPile2.poll();

        openPile1.offer(PlayerCard1);
        openPile2.offer(PlayerCard2);

        int comparedInt = PlayerCard1.compareTo(PlayerCard2);



        while (comparedInt == 0) {
            //Karten sind gleich

            if (closedPile1.isEmpty() || closedPile2.isEmpty())
            {
                ergebnis();
                return;
            }

            PlayerCard1 = closedPile1.poll();
            PlayerCard2 = closedPile2.poll();

            openPile1.offer(PlayerCard1);
            openPile2.offer(PlayerCard2);

            comparedInt = PlayerCard1.compareTo(PlayerCard2);
        }



        if (comparedInt > 0)
        {
            //Spieler1 gewinnt

            while (!openPile1.isEmpty()) {
                closedPile1.offer(openPile1.poll());
            }

            if(openPile1.isEmpty())
            {
                while (!openPile2.isEmpty()) {
                    closedPile1.offer(openPile2.poll());
                }
            }
        }


        if (comparedInt < 0)
        {
            //Spieler2 gewinnt

            while (!openPile2.isEmpty()) {
                closedPile2.offer(openPile2.poll());
            }

            if(openPile2.isEmpty())
            {
                while (!openPile1.isEmpty()) {
                    closedPile2.offer(openPile1.poll());
                }
            }
        }



        ergebnis();


    }

    public void ergebnis()
    {
        if(closedPile1.isEmpty() && closedPile2.isEmpty())
        {
            winner = 0; //Unentschieden
        }
        else if(closedPile1.isEmpty())
        {
            winner = 2; //Spieler 2 gewinnt
        }
        else if(closedPile2.isEmpty())
        {
            winner = 1; //Spieler 1 gewinnt
        }
    }

    /**
     * Returns the winner of the game after the end, or -1 during the game.
     *
     * @return the winner of game (1 or 2), or -1 while the game is ongoing.
     */
    public int getWinner() {
        return winner;
    }

    /**
     * Deal the given deck of cards alternately to the two players.
     * Side effect: The deck is empty after calling this method.
     *
     * @param deck The deck of cards that is distributed to the players.
     */
    public void distributeCards(Stack<Card> deck) {
        closedPile1.clear();
        closedPile2.clear();
        // use addFirst() because the last distributed card should be drawn first
        while (!deck.isEmpty()) {
            Card card = deck.pop();
            closedPile1.addFirst(card);
            if (!deck.isEmpty()) {
                card = deck.pop();
                closedPile2.addFirst(card);
            }
        }
    }

    /**
     * Shuffle a deck of cards and distribute it evenly to the two players.
     */
    public void distributeCards() {
        Stack<Card> deck = new Stack<>();
        for (int i = 0; i < Card.nCards; i++){
            deck.add(new Card(i));
        }
        Collections.shuffle(deck);
        distributeCards(deck);
    }

    /**
     * Returns a String representation of closed piles of cards of the two players.
     *
     * @return String representation of the state of the game.
     */
    @Override
    public String toString() {
        return "Player 1: " + closedPile1 + "\nPlayer 2: " + closedPile2;
    }

    public static void main(String[] args) {
/*
        // Game with a complete, shuffled deck
        Bettelmann game = new Bettelmann();
        game.distributeCards();
*/

        // For testing, you may also use specific distribtions and a small number of cards like this:
        int[] deckArray = {28, 30, 6, 23, 17, 14};
        Stack<Card> deck = new Stack<>();
        for (int id : deckArray) {
            deck.push(new Card(id));
        }
        Bettelmann game = new Bettelmann();
        game.distributeCards(deck);

        // This part is the same for both of the above variants
        System.out.println("Initial situation (top card first):\n" + game);
        int round = 0;
        while (round < 1000000 && game.getWinner()<0) {
            round++;
            game.playRound();
            System.out.println("State after round " + round + ":\n" + game);
        }
    }
}

