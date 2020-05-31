import java.util.Scanner;

public class RunnerBlackJack {

    public static void main(String[] args) {
        GameBlackJack game = new GameBlackJack();


        Scanner scannerIn = new Scanner(System.in);  // Creating a Scanner object

        System.out.println("Welcome to Black Jack");
        System.out.println("Enter player name");
        String userName = scannerIn.nextLine();  // Read user input

        // Checking for user name entered. This catches if user presses enter without typing name
        while (userName.length() == 0) {
            System.out.println("One player name is required");
            userName = scannerIn.nextLine();  // Read user input
        }

        // adding player 1. Then all subsequent players
        while (userName.length() > 0) {
            Player player = new Player(userName);
            game.addPlayer(player);
            System.out.println(game.displayPlayers());
            System.out.println("Enter another player name or press enter to begin");
            userName = scannerIn.nextLine();  // Read user input
        }

        // start of new game. play variable is a signifier for multiple games.
        String play = "y";
        while (play.equals("y")){
            // new game is created with new deck that is shuffled.
            Deck deck = new Deck();
            deck.shuffleDeck();
            game.newGameDeal(deck);

            // runs through each player asking them to stick or twist.
            for (Player player : game.getPlayers()){
                int score = game.getScore(player);
                System.out.println(player.getName() + " has " + score +": " + player.displayHand());
                System.out.println("Stick or twist? please type s or t");
                String choice = scannerIn.nextLine();  // Read user input
                while (!choice.equals("s") && !choice.equals("t")) {
                    System.out.println("Incorrect input please type s or t to stick or twist");
                    choice = scannerIn.nextLine();  // Read user input
                }

                // player in loop for twist until they decide to stick.
                while (choice.equals("t")) {
                    player.addCard(deck.dealCard());
                    score = game.getScore(player);
                    System.out.println(player.getName() + " has " + score +": " + player.displayHand());
                    if (score > 21){
                        System.out.println(player.getName() + " is bust, hand scored " + score);
                        choice = "bust";
                    } else {
                        System.out.println("Stick or twist? please type s or t");
                        choice = scannerIn.nextLine();  // Read user input
                    }

                }
            }

            // all players have played. Dealer is now automatically played.
            game.dealerPlay(deck);

            for (Player player : game.getPlayers()) {
                if (game.checkWin(player)){
                    System.out.println(player.getName() + " won");

                }else {
                    System.out.println(player.getName() + " lost");
                }
            }

            System.out.println("Play again, type y");
            play = scannerIn.nextLine();  // Read user input

        }
    }
}
