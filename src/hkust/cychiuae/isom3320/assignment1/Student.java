package hkust.cychiuae.isom3320.assignment1;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentId;
	private String name;
	private HashMap<String, Assessment> assessmentsMap;
	private int rank;
	
	//Creating a student with their student id and name
	public Student(String studentId, String name) {
		this.studentId = studentId;
		this.name = name;
		this.assessmentsMap = new HashMap<String, Assessment>();
		this.rank = -1;
	}
	
	//Add a assessment
	public void addAssessment(Assessment a) {
		this.assessmentsMap.put(a.getAssessmentName(), a);
	}
	
	//Return an array of assessments of the student
	public Assessment[] getAssessment() {
		ArrayList<Assessment> result = new ArrayList<Assessment>();
		for(Assessment a : this.assessmentsMap.values()) {
			result.add(a);
		}
		return result.toArray(new Assessment[this.assessmentsMap.size()]);
	}
	
	//Get the assessment by the assessment name
	public Assessment getAssessmentByName(String assessmentName) {
		return this.assessmentsMap.get(assessmentName);
	}

	//Some getters and setters of a student object
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}
}
