package TeamProject;
import java.util.*;

public class GameRound {
	private Arithmetic arithmetic;
	private Records records;
	private int operationNum, digits;
	private Scanner s = new Scanner(System.in);
	
	public void createArithmetic() {
		switch(operationNum) {
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
				// 에러 처리
		}
	}

	public GameRound(Records records, int operationNum) {
		this.operationNum = operationNum;
		createArithmetic();
		this.records = records;
	}
	
	public void play() {
		int correctAnswers =0;
		List<Integer> wrongAnswers = new ArrayList<>();

		System.out.print("Enter the number of digits: ");
		int digits = s.nextInt();
		
		for(int i=1; i<=10; i++) {
			int a = arithmetic.generateQuestion(digits);
			int b = arithmetic.generateQuestion(digits);
			String operator = arithmetic.getOperator();
			System.out.printf("[%d]: %d %s %d = ", i, a, operator,b);
			int userAnswer = s.nextInt();
			
			if (arithmetic.checkAnswer(a, b, userAnswer)) {
                correctAnswers++;
            } else {
                wrongAnswers.add(i);
            }
		}
		System.out.printf("YOU GOT %d OUT OF 10 CORRECT!.\n", correctAnswers);
        if (!wrongAnswers.isEmpty()) {
            System.out.println("Wrong question numbers: " + wrongAnswers);
        }

        // return correctAnswers * 10;
	}
}
