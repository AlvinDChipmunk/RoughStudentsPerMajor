package com.coderscampus.week08.assign4;

// this POJO++ is to represent the core data structure, The Student
// such a student object has an ID, a name, a college course, and a grade from that course

/*

Raw Line Data:
28,Justin Conrad,COMPSCI 310,99

Processed Data:
Student ID   : 28            - Integer
Student Name : Justin Conrad - String
Course       : COMPSCI 310   - String
Grade        : 99            - Integer

*/

public class Student implements Comparable <Student> {
	private Integer studentID;
	private String  studentName;
	private String  currentCourse;
	private Integer studentGrade;
	
	public Integer getStudentID ()                         { return studentID; }
	public void    setStudentID (Integer studentID)        { this.studentID = studentID; }
	
	public String  getStudentName ()                       { return studentName; }
	public void    setStudentName (String studentName)     { this.studentName = studentName; }
	
	public String  getCurrentCourse ()                     { return currentCourse; }
	public void    setCurrentCourse (String currentCourse) { this.currentCourse = currentCourse; }
	
	public Integer getStudentGrade ()                      { return studentGrade; }
	public void    setStudentGrade (Integer  studentGrade) { this.studentGrade = studentGrade; }
	
	public Student (
			Integer studentID, 
			String  studentName, 
			String  currentCourse, 
			Integer studentGrade) {

		super();
		
		this.setStudentID     (studentID);
		this.setStudentName   (studentName);
		this.setCurrentCourse (currentCourse);
		this.setStudentGrade  (studentGrade);
	}
	
	public Student () {

		super();
		
		Integer myPlaceholder = -1;
		
		this.setStudentID     (myPlaceholder);
		this.setStudentName   ("Unknown Unknown");
		this.setCurrentCourse ("ZZZZ 9999");
		this.setStudentGrade  (myPlaceholder);

	}
	
	public Student (String[] textLine) {

		super();
		
		Integer myID    = Integer.parseInt (textLine[0]);
		Integer myGrade = Integer.parseInt (textLine[3]);
				
		this.setStudentID     (myID);
		this.setStudentName   (textLine[1]);
		this.setCurrentCourse (textLine[2]);
		this.setStudentGrade  (myGrade);
	}
	
	@Override
	public int compareTo(Student otherStudent) {

		// my comparison function
		
		if (otherStudent.getStudentGrade().equals(this.getStudentGrade())) {
			
			if (otherStudent.getStudentName().equals(this.getStudentName())) {
				return this.getCurrentCourse().compareTo(otherStudent.getCurrentCourse());
			} else {
				return otherStudent.getStudentName().compareTo(this.getStudentName());
			}
		
		} else {
			return otherStudent.getStudentGrade().compareTo(this.getStudentGrade());
		}

	}
	
}
