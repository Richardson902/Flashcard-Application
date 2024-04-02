import java.util.ArrayList;

/**
 * The FlashCardApp class serves as an entry point for the Flash Card application.
 */
public class FlashCardApp {

    /**
     * The main method of the FlashCardApp class.
     * It initializes an ArrayList of FlashCard objects with sample data
     * and launches the FlashCardGUI to display the flash cards.
     * @param args
     */
    public static void main(String[] args) {
        //Create an array list of FlashCard objects
        ArrayList<FlashCard> flashCards = new ArrayList<>();

        //Sample flashcards
        flashCards.add(new FlashCard("question1", "answer1"));
        flashCards.add(new FlashCard("question2", "answer2"));
        flashCards.add(new FlashCard("question3", "answer3"));

        //Create a new instance of FlashCardGUI to display the flash cards
        new FlashCardGUI(flashCards);
        
    }
}
