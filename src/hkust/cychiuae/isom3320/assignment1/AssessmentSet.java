package hkust.cychiuae.isom3320.assignment1;

public class AssessmentSet {
	private String assessmentName;
	private int assessmentPercentage;
	private float classTotal;
	
	public AssessmentSet(String name, int percentage) {
		this.assessmentName = name;
		this.assessmentPercentage = percentage;
		this.classTotal = 0;
	}

	public String getAssessmentName() {
		return assessmentName;
	}

	public int getAssessmentPercentage() {
		return assessmentPercentage;
	}
	
	public void addClassTotalScore(float score) {
		this.classTotal += score;
	}
	
	public float getClassTotal() {
		return classTotal;
	}
}
