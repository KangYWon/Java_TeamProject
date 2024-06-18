package project_record;

public class Subtraction extends Addition {
    @Override
	public boolean checkAnswer(int a, int b, int userAnswer) {
		return a-b == userAnswer;
	}
	
	@Override
    public String getOperator() {
        return "-";
    }
}
