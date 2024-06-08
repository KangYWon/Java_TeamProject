package TeamProject;
import java.util.ArrayList;
import java.util.List;

public class Records {
    private static Records records;
    private int totalScore;
    private List<String> incorrectAnswers;

    private Records() {
        totalScore = 0;
        incorrectAnswers = new ArrayList<>();
    }

    public static Records createRecords() {
        if (records == null) records = new Records();
        return records;
    }

    public void addIncorrectAnswer(String question) {
        incorrectAnswers.add(question);
    }
    
    public void displayRecords() {
        if (incorrectAnswers.isEmpty()) {
            System.out.println("No incorrect answers recorded.");
        } else {
            System.out.println("Incorrect Answers:");
            for (String question : incorrectAnswers) {
                System.out.println(question);
            }
        }
    }

    public void addScore(int score) {
        totalScore += score;
    }

    public int getTotalScore() {
        return totalScore;
    }
}