import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The FlashCardGUI class represents the GUI for dispolaying flash cards.
 * It extends JFrame and implements ActionListener for handling button clicks.
 */
public class FlashCardGUI extends JFrame implements ActionListener {

    JButton nextCard; //Button to show the next flashcard
    JButton showAnswer; //Button to show ther answer of the current flashcard
    JButton shuffle; //Button to shuffle the flashcards
    JButton addCard; //Button to add a new flashcard
    JPanel mainPanel; //Main panel to hold all other panels
    JPanel qPanel; //Panel for displaying the question
    JPanel aPanel; //Panel for displaying the answer
    JPanel buttonPanel; //Panel for containing buttons
    JLabel qContent; //Label to display the question content
    JLabel aContent; //Label to display the answer content
    ArrayList<FlashCard> flashCards; //List of flashcards
    private int currentIndex = -1; //Index of the current flashcard being displayed

    /**
     * Constructs a FlashCardGUI objects with the specified list of flashcards.
     * @param flashCards the list of the flashcards to display
     */
    FlashCardGUI(ArrayList<FlashCard> flashCards) {
        this.flashCards = flashCards;
        this.setTitle("Flash Card App"); //Sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes frame on exit
        this.setResizable(false); //Disables resize
        this.setSize(1000, 700); //Sets x,y height of frame


        //Add buttons
        nextCard = new JButton("Next Card");
        showAnswer = new JButton("Show Answer");
        shuffle = new JButton("Shuffle Cards");
        addCard = new JButton("Add Card");
        
        //Button properties
        nextCard.setFocusable(false);
        nextCard.setFont(new Font("Arial", Font.PLAIN, 28));
        
        showAnswer.setFocusable(false);
        showAnswer.setFont(new Font("Arial", Font.PLAIN, 28));
        showAnswer.setEnabled(false); //Disable button until next card is clicked

        shuffle.setFocusable(false);
        shuffle.setFont(new Font("Arial", Font.PLAIN, 28));

        addCard.setFocusable(false);
        addCard.setFont(new Font("Arial", Font.PLAIN, 28));
        
        //Set action listeners
        nextCard.addActionListener(this);
        showAnswer.addActionListener(this);
        shuffle.addActionListener(this);
        addCard.addActionListener(this);
        

        //Create panels
        mainPanel = new JPanel(new FlowLayout());
        qPanel = new JPanel();
        aPanel = new JPanel();
        buttonPanel = new JPanel();

        //Panel background colors
        qPanel.setBackground(Color.white);
        aPanel.setBackground(Color.white);

        //Panel sizes
        qPanel.setPreferredSize(new Dimension(980, 250));
        aPanel.setPreferredSize(new Dimension(980, 250));
        buttonPanel.setPreferredSize(new Dimension(980, 200));

        //Box layout for qPanel and aPanel
        qPanel.setLayout(new BoxLayout(qPanel, BoxLayout.Y_AXIS));
        aPanel.setLayout(new BoxLayout(aPanel, BoxLayout.Y_AXIS));

        //Add headers to qPanel
        JLabel qLabel = new JLabel("Question: ");
        qLabel.setFont(new Font("Arial", Font.BOLD, 32));
        qLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        qPanel.add(qLabel);

        //Add some space between header and content
        qPanel.add(new JLabel(" "));
        
        //Add question content to qpanel
        qContent = new JLabel();
        qContent.setFont(new Font("Arial", Font.PLAIN, 24));
        qContent.setAlignmentX(Component.CENTER_ALIGNMENT);

        qPanel.add(qContent);
        
        //Add headers to aPanel
        JLabel aLabel = new JLabel("Answer: ");
        aLabel.setFont(new Font("Arial", Font.BOLD, 32));
        aLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        aPanel.add(aLabel);

        //Add some space between header and content
        aPanel.add(new JLabel(" "));
        
        //Add answer content to aPanel
        aContent = new JLabel();
        aContent.setFont(new Font("Arial", Font.PLAIN, 24));
        aContent.setAlignmentX(Component.CENTER_ALIGNMENT);

        aPanel.add(aContent);

        //Add buttons to button panel
        buttonPanel.add(nextCard);
        buttonPanel.add(showAnswer);
        
        buttonPanel.add(shuffle);
        buttonPanel.add(addCard);

        //Add panels to main panel
        mainPanel.add(qPanel);
        mainPanel.add(aPanel);
        mainPanel.add(buttonPanel);

        //Add main panel to frame
        this.add(mainPanel);

        //Set frame to visible
        this.setVisible(true);
    }

    /**
     * Handles button click events.
     * @param e the ActionEvent object representing the button click event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextCard) {
            currentIndex++; //Increment index
            aContent.setVisible(false); //Hide answer when next card is clicked
            showAnswer.setEnabled(true); //Enable answer button

            //Reset index if end of list
            if(currentIndex >= flashCards.size()) {
                currentIndex = 0;
            }

            qContent.setText(flashCards.get(currentIndex).getQuestion()); //Display question

        }
        if(e.getSource() == showAnswer) {
            aContent.setVisible(true); //Show answer when button is clicked
            aContent.setText(flashCards.get(currentIndex).getAnswer()); //Display answer
            showAnswer.setEnabled(false); //Disables answer button after clicked for the current card
        }
        if(e.getSource() == shuffle) {
            Collections.shuffle(flashCards); //Shuffle the flashcards
            currentIndex = -1; //Reset index
            nextCard.doClick(); //Simulate next card click
        }
        if(e.getSource() == addCard) {
            new FlashCardBuilder(flashCards); //Open flashcard builder
        }
    }
    
}
