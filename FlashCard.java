/**
 * The FlashCard class represents a flash card with a question and answer.
 * It provides methods to access and modify the question and answer.
 */

public class FlashCard {
    private String question; // The question on the flash card
    private String answer;  // The answer to the question

    /**
     * Constructs a FlashCard object with the specified question and answer.
     * @param question the qquestion to be set on the flashcard
     * @param answer the answer to the question
     */
    public FlashCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * Gets the question on the flashcard.
     * @return the question on the flashcard
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets the answer to the question on the flashcard.
     * @return the answer to the question on the flashcard
     */
    public String getAnswer() {
        return answer;
    }
}
