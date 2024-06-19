package TeamProject;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

// AgainSolve class: solve incorrect problem function
public class AgainSolve {
    private static AgainSolve againSolve;
    private Map<String, Arithmetic> operations;
    private Records records;

    public AgainSolve(Map<String, Arithmetic> operations, Records records) { 
        this.operations = operations;
        this.records = records;
    }

    // Create AgainSolve object with Singleton pattern
    public static AgainSolve createAS(Map<String, Arithmetic> operations, Records records) {
        if (againSolve == null) againSolve = new AgainSolve(operations, records);
        return againSolve;
    }

    // Solve again incorrect problem
    public void solveIncorrectProblems() {
        Scanner scanner = new Scanner(System.in);
        for (Iterator<String> iterator = records.getIncorrectAnswers().iterator(); iterator.hasNext();) {
            String problem = iterator.next();
            String[] parts = problem.split(" ");
            int num1 = Integer.parseInt(parts[0]);
            String operator = parts[1];
            int num2 = Integer.parseInt(parts[2]);

            Arithmetic operation = operations.get(operator);
            if (operation == null) {
                System.out.println("Unsupported operators: " + operator);
                continue;
            }

            boolean validInput = false;
            int userAnswer = 0;
            
            while (!validInput) {
                try {
                    System.out.print(num1 + " " + operator + " " + num2 + " = ");
                    userAnswer = scanner.nextInt();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("Invalid input.");
                    scanner.next(); 
                }
            }
            
            boolean isCorrect = operation.checkAnswer(num1, num2, userAnswer);
            
            if (isCorrect) {
                System.out.println("Succes!");
                records.addScore(1);
                iterator.remove(); 
            } else {
                System.out.println("Fail.");
            }
        }
        records.saveRecords();
    }
}
