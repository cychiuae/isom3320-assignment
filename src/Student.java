
public class Student {
	private String studentId;
	private String name;
	private float asg1Score;
	private float asg2Score;
	private float midtermScore;
	private float finalScore;
	
	public Student(String studentId, String name) {
		this.studentId = studentId;
		this.name = name;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}

	public float getAsg1Score() {
		return asg1Score;
	}

	public void setAsg1Score(float asg1Score) {
		this.asg1Score = asg1Score;
	}

	public float getAsg2Score() {
		return asg2Score;
	}

	public void setAsg2Score(float asg2Score) {
		this.asg2Score = asg2Score;
	}

	public float getMidtermScore() {
		return midtermScore;
	}

	public void setMidtermScore(float midtermScore) {
		this.midtermScore = midtermScore;
	}

	public float getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(float finalScore) {
		this.finalScore = finalScore;
	}
}
