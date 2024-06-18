package project_record;

public class Addition extends Arithmetic {
	@Override
	public boolean checkAnswer(int a, int b, int userAnswer) {
		return a+b == userAnswer;
	}
	
	@Override
    public String getOperator() {
        return "+";
    }
}
