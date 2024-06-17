package TeamProject;
import java.util.*;

public class GameMenu implements Game {
	private Scanner s = new Scanner(System.in);
	private int totalScore =0;
	private Map<String, Arithmetic> operations;
	private AgainSolve againSolve;
	private Records records;
	
	public GameMenu() {
        operations = new HashMap<>();
        operations.put("+", new Addition());
        operations.put("-", new Subtraction());
        operations.put("*", new Multiplication());
        operations.put("/", new Division());

        records = Records.createRecords();
        againSolve = new AgainSolve(operations, records);
    }
	
	@Override
	public void start() {
		operations = new HashMap<>();
		
		while(true) {
			System.out.println("-----------Game Start!-----------");
			System.out.println("[1] Add\n[2] Subtract\n[3] Multiplication\n[4] Divide\n[5] View Records\n[6] Again solve\n[0] Exit");
			System.out.println("Choose an operation: ");
			int choice = s.nextInt();
			
			if (choice == 5) {
				records.displayRecords();
				if (records.hasData()) {
					System.out.println("Are you sure you want to clear all records? (yes/no)");
	                String confirmation = s.nextLine();
	                while (!confirmation.equalsIgnoreCase("yes") && !confirmation.equalsIgnoreCase("no")) {
	                	System.out.println("Please enter 'yes' or 'no'.");
	                    confirmation = s.nextLine();
	                }
	                if (confirmation.equalsIgnoreCase("yes")) {
	                    records.clearRecords();
	                    System.out.println("All records have been cleared.");
	                } else if (confirmation.equalsIgnoreCase("no")) {
	                    System.out.println("Clear records operation cancelled.");
	                }
				}
				continue;
			}
			
			if(choice == 6) {
				 againSolve.solveIncorrectProblems();
			}
			
			if(choice ==0) {
				System.out.println("Exiting the Game.");
				System.out.println("Your total score: "+ totalScore);
				System.out.println("GOOD BYE~");
				break;
			}
			Arithmetic arithmetic;
			switch(choice) {
			case 1:
				arithmetic = new Addition();
				break;
			case 2: 
				arithmetic = new Subtraction();
				break;
			case 3:
				arithmetic = new Multiplication();
				break;
			case 4:
				arithmetic = new Division();
				break;
				
			default:
				System.out.println("Invalid choide!!");
				continue;
	
			}
			
			GameRound gameRound = new GameRound(records, choice);
			int score = gameRound.play();
			totalScore += score;
			records.addScore(score);
			System.out.println("Current total score: "+ totalScore);
		}
	}
	@Override
	public void saveRecords() {
		// records implementation 저장
	}
}
