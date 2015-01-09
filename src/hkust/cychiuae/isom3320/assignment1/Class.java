package hkust.cychiuae.isom3320.assignment1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Class {
	private ArrayList<Student> students;
	private ArrayList<String> assignmentName;
	private int asg1Percentage;
	private int asg2Percentage;
	private int midtermPercentage;
	private int finalPercentage;
	
	public Class() {
		this.students = new ArrayList<Student>();
		this.assignmentName = new ArrayList<String>();
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public void addAssignment(String name) {
		this.assignmentName.add(name);
	}
	
	public float calculateStudentOverallScore(Student s) {
		return (float)((s.getAsg1Score() * this.asg1Percentage + s.getAsg2Score() * this.asg2Percentage + s.getMidtermScore() * this.midtermPercentage + s.getFinalScore() * this.finalPercentage) / 100.0);
	}
	
	public void rankTheStudents() {
		int rank = 1;
		
		for(Student s : this.students) {
			int indexOfStudent = this.students.indexOf(s);
			
			if(indexOfStudent == 0) {
				s.setRank(rank);
			}
			else if(this.calculateStudentOverallScore(s) == this.calculateStudentOverallScore(this.students.get(indexOfStudent - 1))){
				s.setRank(rank);
			}
			else {
				rank = indexOfStudent + 1;
				s.setRank(rank);
			}
		}
	}
	
	public void readStudentData(String fileLocation) {
		BufferedReader in;
		
		try {
			in = new BufferedReader(new FileReader(fileLocation));
			
			String firstLine = in.readLine();
			String[] firstLineData = firstLine.split(", ");
			
			assignmentName.add(firstLineData[2]);
			asg1Percentage = Integer.parseInt(firstLineData[3]);
			
			assignmentName.add(firstLineData[4]);
			asg2Percentage = Integer.parseInt(firstLineData[5]);
			
			assignmentName.add(firstLineData[6]);
			midtermPercentage = Integer.parseInt(firstLineData[7]);
			
			assignmentName.add(firstLineData[8]);
			finalPercentage = Integer.parseInt(firstLineData[9]);
			
			String line = null;
			while((line = in.readLine()) != null) {
				String[] lineData = line.split(", ");
				Student student = new Student(lineData[0], lineData[1]);
				student.setAsg1Score(Float.parseFloat(lineData[2]));
				student.setAsg2Score(Float.parseFloat(lineData[3]));
				student.setMidtermScore(Float.parseFloat(lineData[4]));
				student.setFinalScore(Float.parseFloat(lineData[5]));
				
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
	
	public void printStudentData() {
		float averageAsg1Score = 0, averageAsg2Score = 0, averageMidtermScore = 0, averageFinalScore = 0, averageOverallScore = 0;
		float totalAsg1Score = 0, totalAsg2Score = 0, totalMidtermScore = 0, totalFinalScore = 0, totalOverallScore = 0;
		
		System.out.printf("ID\t\tName\t\t\t%s\t%s\t%s\t%s\tOverall\tRank\n", assignmentName.get(0), assignmentName.get(1), assignmentName.get(2), assignmentName.get(3));
		
		for(Student s : this.students) {
			float overallScore = this.calculateStudentOverallScore(s);
			totalAsg1Score += s.getAsg1Score();
			totalAsg2Score += s.getAsg2Score();
			totalMidtermScore += s.getMidtermScore();
			totalFinalScore += s.getFinalScore();
			totalOverallScore += overallScore;
			
			System.out.printf("%s\t%s\t\t%.1f\t%.1f\t%.1f\t%.1f\t%.1f\t%d\n", s.getStudentId(), s.getName(), s.getAsg1Score(), s.getAsg2Score(), s.getMidtermScore(), s.getFinalScore(), overallScore, s.getRank());
		}
		
		averageAsg1Score = totalAsg1Score / this.students.size();
		averageAsg2Score = totalAsg2Score / this.students.size();
		averageMidtermScore = totalMidtermScore / this.students.size();
		averageFinalScore = totalFinalScore / this.students.size();
		averageOverallScore = totalOverallScore / this.students.size();
		
		System.out.printf("\t\t\t\tAverage:%.1f\t%.1f\t%.1f\t%.1f\t%.1f\n", averageAsg1Score, averageAsg2Score, averageMidtermScore, averageFinalScore, averageOverallScore);
	}
	
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