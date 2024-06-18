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

		// create singleton objects
        records = Records.createRecords(); 
        againSolve = AgainSolve.createAS(operations, records);
    }
	
	public void start() {
		GameRound gameRound = new GameRound(records);
		operations = new HashMap<>();
		
		while(true) {
			System.out.println("-----------Game Start!-----------");
			System.out.println("[1] Add\n[2] Subtract\n[3] Multiplication\n[4] Divide\n[5] Random Time Attack\n[6] View Records\n[7] Again solve\n[0] Exit");
			System.out.println("Choose an operation: ");
			int choice = s.nextInt();
			System.out.println();
			
			if (choice == 6) {
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
			
			if (choice == 7) {
				againSolve.solveIncorrectProblems();
				continue;
			}
			
			if (choice == 0) {
				System.out.println("Exiting the Game.");
				System.out.println("Your total score: "+ totalScore);
				System.out.println("GOOD BYE~");
				break;
			}
			
			gameRound.play(choice);
		}
	}
}
