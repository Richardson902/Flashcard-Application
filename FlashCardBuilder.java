import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The FlashCardBuilder class provides a GUI for creating new flashcards.
 * It allows users to input questions and answers and add them to a list of flashcards.
 */
public class FlashCardBuilder implements ActionListener {

    JFrame frame = new JFrame(); //The main frame of the GUI
    JLabel qLabel; //Label for the question input field
    JLabel aLabel; //Label for the answer input field
    JPanel mainPanel; //Main panel to hold all other panels
    JPanel qPanel; //Panel for the question input field
    JPanel aPanel; //Panel for the answer input field
    JPanel buttonPanel; //Panel for containing buttons
    JButton addButton; //Button to add a flashcard
    JButton saveButton; //Button to save and exit
    JTextArea qTextArea; //Text area for entering the question
    JTextArea aTextArea; //Text area for entering the answer
    ArrayList<FlashCard> flashCards; //List of flashcards

    /**
     * Constructs a FlashCardBuilder object with the specified list of flashcards.
     * @param flashCards the list of flashcards to add new flashcards to
     */
    FlashCardBuilder(ArrayList<FlashCard> flashCards) {
        
        this.flashCards = flashCards;
        frame.setSize(600, 400); //Set frame size
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Dispose frame on exit
        frame.setResizable(false); //Disable resize
        
        //add buttons
        addButton = new JButton("Add Card");
        saveButton = new JButton("Save & Exit");
        
        //Button properties
        addButton.setFocusable(false);
        addButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addButton.setEnabled(false); //Disable button until text is entered

        saveButton.setFocusable(false);
        saveButton.setFont(new Font("Arial", Font.PLAIN, 18));
        saveButton.setEnabled(false); //Disable button until a card is added

        //Set action listeners
        addButton.addActionListener(this);
        saveButton.addActionListener(this);

        //Add labels
        qLabel = new JLabel("Enter Question Here:");
        qLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        aLabel = new JLabel("Enter Answer Here:");
        aLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Add text fields
        qTextArea = new JTextArea();
        qTextArea.setPreferredSize(new java.awt.Dimension(400, 150));
        qTextArea.setLineWrap(true);
        qTextArea.setWrapStyleWord(true);
        
        aTextArea = new JTextArea();
        aTextArea.setPreferredSize(new java.awt.Dimension(400, 150));
        aTextArea.setLineWrap(true);
        aTextArea.setWrapStyleWord(true);

        //Create panels
        mainPanel = new JPanel(new FlowLayout());
        qPanel = new JPanel();
        aPanel = new JPanel();
        buttonPanel = new JPanel();

        //Set panel sizes
        qPanel.setPreferredSize(new java.awt.Dimension(580, 150));
        aPanel.setPreferredSize(new java.awt.Dimension(580, 150));
        buttonPanel.setPreferredSize(new java.awt.Dimension(580, 50));

        //Box layout for qPanel and aPanel
        qPanel.setLayout(new BoxLayout(qPanel, BoxLayout.Y_AXIS));
        aPanel.setLayout(new BoxLayout(aPanel, BoxLayout.Y_AXIS));

        //Add components to panels
        qPanel.add(qLabel);
        qPanel.add(qTextArea);

        aPanel.add(aLabel);
        aPanel.add(aTextArea);

        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);

        //Add panels to main panel
        frame.add(mainPanel);

        mainPanel.add(qPanel);
        mainPanel.add(aPanel);
        mainPanel.add(buttonPanel);

        frame.setVisible(true);
        
        //Document listener for enabling/disabling the add button based on text input.
        DocumentListener textAreaListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                enableButtonIfText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                enableButtonIfText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                enableButtonIfText();
            }
        };

        qTextArea.getDocument().addDocumentListener(textAreaListener);
        aTextArea.getDocument().addDocumentListener(textAreaListener);
    }

    /**
     * Enables the add button if both the question and answer text areas are not empty.
     */
    private void enableButtonIfText() {
        boolean questionNotEmpty = !qTextArea.getText().trim().isEmpty();
        boolean answerNotEmpty = !aTextArea.getText().trim().isEmpty();
        addButton.setEnabled(questionNotEmpty && answerNotEmpty);
    }

    /**
     * Handles button click events.
     * @param e the ActionEvent to handle
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton) {
            String question = qTextArea.getText();
            String answer = aTextArea.getText();
            FlashCard newFlashCard = new FlashCard(question, answer);
            flashCards.add(newFlashCard);
            saveButton.setEnabled(true); //Enable save button after adding a card

            //Clear text areas after adding a card
            qTextArea.setText("");
            aTextArea.setText("");
        }
        if(e.getSource() == saveButton) {
            frame.dispose(); //Dispose the frame
        }
    }
}
