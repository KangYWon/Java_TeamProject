package TeamProject;
import java.util.*;

public class GameRound {
	private Arithmetic arithmetic;
	private Records records;
	private int score, totalScore = 0;
	// private int operationNum;
	private Scanner s = new Scanner(System.in);
	Random random = new Random();
	int problemCnt, correctAnswers;
	
	public void setArithmetic(int operationNum) {
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
				System.out.println("Invalid choice!!");
				arithmetic = null;
		}
	}

	public GameRound(Records records) {
		this.records = records;
		totalScore = records.getTotalScore();
	}
	
	public void play(int operationNum) {
		List<Integer> wrongAnswers = new ArrayList<>();
		boolean isRandom = false;

		if (operationNum == 5) {
			operationNum = random.nextInt(4)+1;
			isRandom = true;
		}
		setArithmetic(operationNum);

		System.out.print("Enter the number of digits: ");
		int digits = -1;

		while (digits < 1) {
			try {
				digits = s.nextInt();
				if(digits < 1){
					System.out.println("Please enter a number greater than 0.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please enter a valid number.");
				s.next();
			}
		}

		if (isRandom) {
			wrongAnswers = randomly(digits, wrongAnswers);
			correctAnswers = problemCnt - wrongAnswers.size();
			System.out.printf("YOU GOT %d OUT OF %d CORRECT!.\n", correctAnswers, problemCnt);
			records.updateScore(correctAnswers * 10);
		}
		else {
			wrongAnswers = orderly(digits, wrongAnswers);
			correctAnswers = 10 - wrongAnswers.size();
			System.out.printf("YOU GOT %d OUT OF 10 CORRECT!.\n", correctAnswers);
		}
		
        if (!wrongAnswers.isEmpty()) {
            System.out.println("Wrong question numbers: " + wrongAnswers);
		}
		score = correctAnswers * 10;
		totalScore += score;
		records.addScore(score);
		System.out.println("Current total score: " + totalScore);
	}

	public List<Integer> orderly (int digits, List<Integer> wrongAnswers) {
		for(int i=1; i<=10; i++) {
			int a = arithmetic.generateQuestion(digits);
			int b = arithmetic.generateQuestion(digits);
			String operator = arithmetic.getOperator();
			System.out.printf("[%d]: %d %s %d = ", i, a, operator,b);
			int userAnswer;
			try {
				userAnswer = s.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input!");
				s.next(); 
				wrongAnswers.add(i);
				String question = String.format("%d %s %d", a, operator, b);
				records.addIncorrectAnswer(question);
				continue; 
			}
			
			if (!arithmetic.checkAnswer(a, b, userAnswer)) {
                wrongAnswers.add(i);
                String question = String.format("%d %s %d", a, operator, b);
				records.addIncorrectAnswer(question);
            }
		}

		return wrongAnswers;
	}

	public List<Integer> randomly (int digits, List<Integer> wrongAnswers) {
		problemCnt = 1;
		long start = System.currentTimeMillis();
		long end = start + 30 * 1000;

		while(System.currentTimeMillis() < end) {
			int a = arithmetic.generateQuestion(digits);
			int b = arithmetic.generateQuestion(digits);
			String operator = arithmetic.getOperator();
			System.out.printf("[%d]: %d %s %d = ", problemCnt, a, operator,b);
			int userAnswer;
			try {
				userAnswer = s.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input!");
				s.next(); 
				wrongAnswers.add(problemCnt);
				String question = String.format("%d %s %d", a, operator, b);
				records.addIncorrectAnswer(question);
				continue; 
			}
			
			if (!arithmetic.checkAnswer(a, b, userAnswer)) {
                wrongAnswers.add(problemCnt);
                String question = String.format("%d %s %d", a, operator, b);
				records.addIncorrectAnswer(question);
            }
			problemCnt++;
		}

		return wrongAnswers;
	}
}
