package hkust.cychiuae.isom3320.assignment1;

public class AssessmentSet {
	private String assessmentName;
	private int assessmentPercentage;
	private float classTotal;
	
	//Creating an assessment set with its name and percentage
	public AssessmentSet(String name, int percentage) {
		this.assessmentName = name;
		this.assessmentPercentage = percentage;
		this.classTotal = 0;
	}

	//Get the assessment name
	public String getAssessmentName() {
		return assessmentName;
	}

	//Get the assessment percentage
	public int getAssessmentPercentage() {
		return assessmentPercentage;
	}
	
	//add a student's score to this assessment
	public void addClassTotalScore(float score) {
		this.classTotal += score;
	}
	
	//Return the class total of this assessment
	public float getClassTotal() {
		return classTotal;
	}
}
