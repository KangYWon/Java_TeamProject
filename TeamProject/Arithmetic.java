package TeamProject;
import java.util.*;

public class Arithmetic implements ArithmeticOperation{
	Random random = new Random();
	
	public String getOperator() {
		return null;
	}

	@Override
	public int generateQuestion(int digits) {
		int max = (int) Math.pow(10,  digits)-1;
		int min = (int) Math.pow(10, digits-1);
		return random.nextInt(max-min+1)+min;
	}

	@Override
	public boolean checkAnswer(int a, int b, int userAnswer) {
		// TODO Auto-generated method stub
		return false;
	}

}
