package TeamProject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Records {
    private static Records records;
    private int totalScore;
    private List<String> incorrectAnswers;
    private static final String FILE_NAME = "records.txt";

    private Records() {
        totalScore = 0;
        incorrectAnswers = new ArrayList<>();
        loadRecords();
    }

    public static Records createRecords() {
        if (records == null) records = new Records();
        return records;
    }

    public void addIncorrectAnswer(String question) {
        incorrectAnswers.add(question);
        saveRecords();
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
        System.out.println("Total Score: " + totalScore);
    }

    public void addScore(int score) {
        totalScore += score;
        saveRecords();
    }

    public int getTotalScore() {
        return totalScore;
    }
    private void saveRecords() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("TotalScore: " + totalScore + "\n");
            for (String question : incorrectAnswers) {
                writer.write(question + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
        }
    }
    
    private void loadRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null && line.startsWith("TotalScore:")) {
                totalScore = Integer.parseInt(line.split(":")[1].trim());
            }
            while ((line = reader.readLine()) != null) {
                incorrectAnswers.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading records: " + e.getMessage());
        }
    }
}
