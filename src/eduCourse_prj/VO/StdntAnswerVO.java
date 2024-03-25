package eduCourse_prj.VO;

public class StdntAnswerVO {
	private int question_number;
	private String std_answer;
	private String answer;
	public int getQuestion_number() {
		return question_number;
	}
	public String getStd_answer() {
		return std_answer;
	}
	public String getAnswer() {
		return answer;
	}
	public StdntAnswerVO(int question_number, String std_answer, String answer) {
		super();
		this.question_number = question_number;
		this.std_answer = std_answer;
		this.answer = answer;
	}
	
	public StdntAnswerVO() {
	}
	
}
