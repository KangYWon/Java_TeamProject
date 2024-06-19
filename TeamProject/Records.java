package TeamProject;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Records {
    private static Records records;
    private int totalScore, maxScore;
    private List<String> incorrectAnswers;
    private static final String FILE_NAME = "records.txt";

    private Records() {
        totalScore = 0;
        maxScore = 0;
        incorrectAnswers = new ArrayList<>();
        loadRecords(); // load the previous records when the program starts
    }

    // Create Records object with Singleton pattern
    public static Records createRecords() {
        if (records == null) records = new Records();
        return records;
    }

    // Add incorrect problem
    public void addIncorrectAnswer(String question) {
        incorrectAnswers.add(question);
        saveRecords();
    }

    // Display records
    public void displayRecords() {
        System.out.println("Total Score: " + totalScore);
        System.out.println("Max Score in 30 secs: " + maxScore);
        if (incorrectAnswers.isEmpty()) {
            System.out.println("No incorrect answers recorded.");
        } else {
            System.out.println("Incorrect Answers:");
            for (String question : incorrectAnswers) {
                System.out.println(question);
            }
        }
    }

    // Add and save score from total score
    public void addScore(int score) {
        totalScore += score;
        saveRecords();
    }

    // Update max score
    public void updateScore(int score) {
        if (score > maxScore) maxScore = score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    // Update saved records clear
    public void clearRecords() {
    	totalScore = 0;
        incorrectAnswers.clear();
        saveRecords();
    }
    
    public boolean hasData() {
        return !incorrectAnswers.isEmpty();
    }

    // Save records to file
    void saveRecords() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("TotalScore: " + totalScore + "\n");
            for (String question : incorrectAnswers) {
                writer.write(question + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
        }
    }

    // Load records file
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

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }
}
