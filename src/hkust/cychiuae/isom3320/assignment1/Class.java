package hkust.cychiuae.isom3320.assignment1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Class {
	private ArrayList<Student> students;
	private AssessmentCollection assessmentCollection;
	
	//Creating a class object
	public Class() {
		this.students = new ArrayList<Student>();
		this.assessmentCollection = new AssessmentCollection();
	}
	
	//Add a student to the class
	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	//A helper function that calculate the overall score of a student
	public float calculateStudentOverallScore(Student s) {
		float overallScore = 0;
		
		//Add up the assessment score
		for(AssessmentSet aS : this.assessmentCollection.getAssessmentSet()) {
			overallScore += s.getAssessmentByName(aS.getAssessmentName()).getScore() * aS.getAssessmentPercentage();
		}
		
		//return the total divided by 100
		return overallScore / 100;
	}
	
	//Get the rank the students in the class.
	//Should be sorted before use this method
	public void rankTheStudents() {
		int rank = 1;
		
		for(Student s : this.students) {
			int indexOfStudent = this.students.indexOf(s);
			
			if(indexOfStudent == 0) {
				s.setRank(rank);
			}
			else if(this.calculateStudentOverallScore(s) == this.calculateStudentOverallScore(this.students.get(indexOfStudent - 1))){
				s.setRank(rank);	//If the scores of students are same, set the rank to same
			}
			else {
				rank = indexOfStudent + 1;
				s.setRank(rank);
			}
		}
	}
	
	//Read the input data from a file
	public void readStudentData(String fileLocation) {
		BufferedReader in;
		
		try {
			in = new BufferedReader(new FileReader(fileLocation));
			
			//Read the first line
			String firstLine = in.readLine();
			String[] firstLineData = firstLine.split(",");
			
			
			//Process the first line and set up the assessments of the class
			for(int i = 2; i < firstLineData.length; i += 2) {
				String assessmentName = firstLineData[i].trim();
				int assessmentPercentage = Integer.parseInt(firstLineData[i + 1].trim());
				AssessmentSet assessmentSet = new AssessmentSet(assessmentName, assessmentPercentage);
				this.assessmentCollection.addAddessment(assessmentSet);
			}

			//Read the rest of the file
			String line = null;
			while((line = in.readLine()) != null) {
				//Process a line and set up the students data
				String[] lineData = line.split(",");
				Student student = new Student(lineData[0].trim(), lineData[1].trim());
				
				for(int i = 2; i < lineData.length; i++) {
					Assessment assessment = new Assessment(this.assessmentCollection.getAssessmentSet().get(i - 2).getAssessmentName(), Float.parseFloat(lineData[i].trim()));
					student.addAssessment(assessment);
				}
				
				this.students.add(student);
			}
			
			in.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
	
	//Print some awesome data on the console
	public void printStudentData() {		
		float totalOverall = 0;
		
		//Print the header
		System.out.printf("%-9s%-21s", "ID", "Name");
		
		for(AssessmentSet assessmentSet : this.assessmentCollection.getAssessmentSet()) {
			int assessmentNameLength = assessmentSet.getAssessmentName().length() + 1;
			String format = "%-" + assessmentNameLength +"s";
			System.out.printf(format, assessmentSet.getAssessmentName());
		}
		
		System.out.printf("%-8s%s", "Overall", "Rank");
		
		System.out.println();
		
		//Print the student records
		for(Student s : this.students) {
			float studentOverallScore = calculateStudentOverallScore(s);
			totalOverall += studentOverallScore;
			
			System.out.printf("%-9s%-21s", s.getStudentId(), s.getName());
			
			for(AssessmentSet assessmentSet : this.assessmentCollection.getAssessmentSet()) {
				float assessmentScore = s.getAssessmentByName(assessmentSet.getAssessmentName()).getScore();
				assessmentSet.addClassTotalScore(assessmentScore);
				
				int assessmentNameLength = assessmentSet.getAssessmentName().length() + 1;
				String format = "%-" + assessmentNameLength +".1f";
				
				System.out.printf(format, assessmentScore);
			}
			
			System.out.printf("%-8.1f", studentOverallScore);
			
			System.out.printf("%d", s.getRank());
			
			System.out.println();
		}
		
		System.out.printf("%30s", "Average:");
		
		for(AssessmentSet assessmentSet : this.assessmentCollection.getAssessmentSet()) {
			int assessmentNameLength = assessmentSet.getAssessmentName().length() + 1;
			String format = "%-" + assessmentNameLength +".1f";
			
			System.out.printf(format, assessmentSet.getClassTotal() / this.students.size());
		}
		
		System.out.printf("%-8.1f\n", totalOverall / this.students.size());
	}
	
	//Sort the students in the class according o their overall score
	public void sortTheStudent() {
		this.students.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				float student1OverallScore = Class.this.calculateStudentOverallScore(o1);
				float student2OverallScore = Class.this.calculateStudentOverallScore(o2);
			
				if(student1OverallScore > student2OverallScore) {
					return -1;
				}
				else if(student1OverallScore == student2OverallScore) {
					return 0;
				}
				else {
					return 1;
				}
			}
		});
	}
}