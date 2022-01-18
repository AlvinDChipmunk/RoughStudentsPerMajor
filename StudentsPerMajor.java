package com.coderscampus.week08.assign4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/*

Assignment #4
Requirements

Situation

You work for a College as their Data Scientist and your job is to help the Professors 
manage the data that they receive from the College. For this assignment you need to help 
the Professors of 3 different classes with their student enrollment list. 

The Professors were given a single CSV file that contains a "master list" of all 
students across all courses. But each Professor only wants to see a list of their own 
students (not the students who are in other classes).

Job

You will need to parse the Master List File and separate the data into 3 separate CSV 
files. Each CSV file should contain a list of students specific to that particular 
course (i.e. we're grouping the students by course). 

For each of the 3 CSV files, you'll need to sort the students by grade in descending 
order.

The CSV output file names should be called: course1.csv, course2.csv, and course3.csv.


Hints

In order to sort an array, you'll need to figure out how to handle null entries in 
your array. If you're trying to use Arrays.sort(yourArray), and yourArray contains 
null values, the sort will crash. 

When writing to a file, you can use "\n" to write a new line to the file. For 
example: 

fileWriter.write("This is one line \n");

If you want to turn String input into an Integer, you can parse it like so: 

Integer myIntVal = Integer.parseInt(myStringVal);

Example Output

course1.csv (COMPSCI)

Student ID,Student Name,Course,Grade
28,Justin Conrad,COMPSCI 310,99
37,Simone Scott,COMPSCI 312,91
91,Donald Schultz,COMPSCI 321,87
19,Blair Heaton,COMPSCI 309,87
79,Kerys John,COMPSCI 319,87
64,Jameson Best,COMPSCI 316,86
... rest of lines omitted ...

course2.csv (APMTH)

Student ID,Student Name,Course,Grade
89,Alison Murray,APMTH 134,93
59,Amber-Rose Austin,APMTH 129,93
68,Aran Rice,APMTH 131,89
44,Brandan Mcbride,APMTH 127,89
32,Veronica Dodd,APMTH 125,88
... rest of lines omitted ...

course3.csv (STAT)

Student ID,Student Name,Course,Grade
15,Padraig Barry,STAT 236,93
39,Zachariah Hutchinson,STAT 240,92
33,Stewart Reed,STAT 239,90
54,Franciszek Hartman,STAT 242,90
6,Bodhi Stokes,STAT 234,86
... rest of lines omitted ...


Raw Line Data:
28,Justin Conrad,COMPSCI 310,99

Processed Data:
Student ID   : 28
Student Name : Justin Conrad
Course       : COMPSCI 310
Grade        : 99

*/

public class StudentsPerMajor {

	public static void main(String[] args) throws IOException {
		
		// begin preliminary testing section for converting single line string into useful info
		
		// this test block works
		/*
		String   testData        = "33,Stewart Reed,STAT 239,90";
		
		String[] testDestination = MassageLineData (testData);
		
		String   testMajor       = "NOTSET";
		
		System.out.println("Before Processing: \n" + testData + "\nMajor: " + testMajor); 
		
		System.out.println("\nAfter Processing");
		testMajor = WhichMajor(testData);
		
		System.out.println("studentID     : " + testDestination[0]);
		System.out.println("studentName   : " + testDestination[1]);
		System.out.println("currentCourse : " + testDestination[2]);
		System.out.println("studentGrade  : " + testDestination[3]);

		System.out.println("Major         : " + testMajor);
		*/
		
		// end preliminary testing section for converting single line string into useful info
		
		// begin preliminary testing section for reading one file and writing to three different files
		
		// this test block works
		/*
		String lineFromFile      = "This is pre population junk before the actual reading of a file."; 
		String studentWhichMajor = "NOTSET";
		
		try (
			BufferedReader fileReaderMaster  = new BufferedReader (new FileReader("student-master-list.csv"));
			FileWriter     fileWriterCourse1 = new FileWriter     ("test1.csv");
			FileWriter     fileWriterCourse2 = new FileWriter     ("test2.csv");
			FileWriter     fileWriterCourse3 = new FileWriter     ("test3.csv"); ) {
			
			while ((lineFromFile = fileReaderMaster.readLine()) != null) {
				//System.out.println(lineFromFile); 
				
				studentWhichMajor = WhichMajor(lineFromFile);
				
				if        (studentWhichMajor.equals("COMPSCI")) {
					
					System.out.println("Writing to test1.csv.");
					fileWriterCourse1.write(lineFromFile + "\n");
					
				} else if (studentWhichMajor.equals("APMTH")) {
					
					System.out.println("Writing to test2.csv.");
					fileWriterCourse2.write(lineFromFile + "\n");
					
				} else if (studentWhichMajor.equals("STAT")) {
					
					System.out.println("Writing to test3.csv.");
					fileWriterCourse3.write(lineFromFile + "\n");
					
				} else {
					System.out.println("Invalid line.");
				}
				
			}
			
			fileReaderMaster.close();
			fileWriterCourse1.close();
			fileWriterCourse2.close();
			fileWriterCourse3.close();
		}
		*/
		
		// end preliminary testing section for reading one file and writing to three different files
		
		// begin preliminary testing section for reading one file and populating three different object arrays

		String lineFromFile      = "This is pre population junk before the actual reading of a file.";
		String studentWhichMajor = "NOTSET";	
		
		Student[] stCOMPSCI_Arr  = new Student[105]; // for sorting the COMPSCI students
		int stCOMPSCI_Index  = 0;
		
		Student[] stAPMTH_Arr    = new Student[105]; // for sorting the APMTH students
		int stAPMTH_Index    = 0;
		
		Student[] stSTAT_Arr     = new Student[105]; // for sorting the STAT students
		int stSTAT_Index     = 0;
		
		Student tempStudent      = new Student();    // blank student to allow data insertion
		String[] tempStudentInfo = new String[4];
		
		// 105 is slightly larger than the line count of student-master-list.csv
		
		for (Student stCOMPSCI : stCOMPSCI_Arr) { stCOMPSCI = new Student(); }
		for (Student stAPMTH   : stAPMTH_Arr)   { stAPMTH   = new Student(); }
		for (Student stSTAT    : stSTAT_Arr)    { stSTAT    = new Student(); }
		
		try (
			BufferedReader fileReaderMaster  = new BufferedReader (new FileReader("student-master-list.csv"));
			FileWriter     fileWriterCourse1 = new FileWriter     ("test1.csv");
			FileWriter     fileWriterCourse2 = new FileWriter     ("test2.csv");
			FileWriter     fileWriterCourse3 = new FileWriter     ("test3.csv");) {

			// first populate the three arrays 
			
			while ((lineFromFile = fileReaderMaster.readLine()) != null) {
				
				System.out.println( "Starting line: " + lineFromFile ); 
				
				studentWhichMajor = WhichMajor(lineFromFile);
				System.out.println( "Starting major: " + studentWhichMajor ); 
				
				tempStudentInfo   = MassageLineData(lineFromFile); 
				System.out.println( "Starting Student Data from line: ");
				System.out.println( "tempStudentInfo[0]: " + tempStudentInfo[0] );
				System.out.println( "tempStudentInfo[1]: " + tempStudentInfo[1] );
				System.out.println( "tempStudentInfo[2]: " + tempStudentInfo[2] );
				System.out.println( "tempStudentInfo[3]: " + tempStudentInfo[3] );
				
				if      (studentWhichMajor.equals("COMPSCI")) { stCOMPSCI_Index = insertStudent (stCOMPSCI_Arr, tempStudentInfo, stCOMPSCI_Index); }
				else if (studentWhichMajor.equals("APMTH"))   { stAPMTH_Index   = insertStudent (stAPMTH_Arr,   tempStudentInfo, stAPMTH_Index);   }
				else if (studentWhichMajor.equals("STAT"))    { stSTAT_Index    = insertStudent (stSTAT_Arr,    tempStudentInfo, stSTAT_Index);    }
				else                                          { System.out.println("Invalid line."); }
				
			}
			
			// test each array for nulls
			for (Student compSciStudent : stCOMPSCI_Arr) {
				String myData = 
					compSciStudent.getStudentID() + "," +
					compSciStudent.getStudentName() + "," +
					compSciStudent.getCurrentCourse() + "," +
					compSciStudent.getStudentGrade();
				
				System.out.println(myData);
			}

			for (Student APMTHStudent : stAPMTH_Arr) {
				String myData = 
					APMTHStudent.getStudentID() + "," +
					APMTHStudent.getStudentName() + "," +
					APMTHStudent.getCurrentCourse() + "," +
					APMTHStudent.getStudentGrade();
				
				System.out.println(myData);
			}

			for (Student statStudent : stSTAT_Arr) {
				String myData = 
					statStudent.getStudentID() + "," +
					statStudent.getStudentName() + "," +
					statStudent.getCurrentCourse() + "," +
					statStudent.getStudentGrade();
				
				System.out.println(myData);
			}

			
			// now sort each array
			Arrays.sort(stCOMPSCI_Arr);
			Arrays.sort(stAPMTH_Arr);
			Arrays.sort(stSTAT_Arr);
			
			// now write from each array to the corresponding file
			String myString;
			
			Integer maxStCOMPSCI_Index = stCOMPSCI_Index; 
			Integer maxStAPMTH_Index   = stAPMTH_Index;
			Integer maxStSTAT_Index    = stSTAT_Index;
			
			// now reset indexes so we can travel up each array appropriately
			stCOMPSCI_Index  = 0;
			stAPMTH_Index    = 0;
			stSTAT_Index     = 0;	
			
			for (stCOMPSCI_Index = 0; stCOMPSCI_Index <= maxStCOMPSCI_Index; stCOMPSCI_Index ++) {
				myString = 
					stCOMPSCI_Arr[stCOMPSCI_Index].getStudentID()     + "," + 
					stCOMPSCI_Arr[stCOMPSCI_Index].getStudentName()   + "," + 
					stCOMPSCI_Arr[stCOMPSCI_Index].getCurrentCourse() + "," + 
					stCOMPSCI_Arr[stCOMPSCI_Index].getStudentGrade()  + "\n";
				
				fileWriterCourse1.write(myString);
			}
			
			for (stAPMTH_Index = 0; stAPMTH_Index <= maxStAPMTH_Index; stAPMTH_Index ++) {
				myString = 
					stAPMTH_Arr[stAPMTH_Index].getStudentID()     + "," + 
					stAPMTH_Arr[stAPMTH_Index].getStudentName()   + "," + 
					stAPMTH_Arr[stAPMTH_Index].getCurrentCourse() + "," + 
					stAPMTH_Arr[stAPMTH_Index].getStudentGrade()  + "\n";
				
				fileWriterCourse2.write(myString);
			}

			for (stSTAT_Index = 0; stSTAT_Index <= maxStSTAT_Index; stSTAT_Index ++) {
				myString = 
					stSTAT_Arr[stSTAT_Index].getStudentID()     + "," + 
					stSTAT_Arr[stSTAT_Index].getStudentName()   + "," + 
					stSTAT_Arr[stSTAT_Index].getCurrentCourse() + "," + 
					stSTAT_Arr[stSTAT_Index].getStudentGrade()  + "\n";
				
				fileWriterCourse3.write(myString);
			}
			
			fileReaderMaster.close();
			fileWriterCourse1.close();
			fileWriterCourse2.close();
			fileWriterCourse3.close();
		}
		
		
		// end preliminary testing section for reading one file and populating three different object arrays
	}

	public static String[] MassageLineData (String lineData) {
		
		// helper method to get data ready to put into a student object
		
		String[] myStringArray = lineData.split(",");
		
		return myStringArray; 
	}
	
	public static String WhichMajor (String lineData) {
		
		// helper method to get back which Major is being processed
		
		String thisMajor = "UNDECLARED";
			
		boolean foundCOMPSCI = lineData.matches(".*COMPSCI.*"); // one match for COMPSCI for course1.csv
		boolean foundAPMTH   = lineData.matches(".*APMTH.*");   // one match for APMTH for course2.csv
		boolean foundSTAT    = lineData.matches(".*STAT.*");    // one match for STAT for course3.csv
		
		if (foundCOMPSCI)    { thisMajor = "COMPSCI";    }
		else if (foundAPMTH) { thisMajor = "APMTH";      }
		else if (foundSTAT)  { thisMajor = "STAT";       }
		else                 { thisMajor = "UNDECLARED"; }
		
		return thisMajor;
	}
	
	public static Integer insertStudent (Student[] studentList, String[] studentInfo, int listIndex) {
				
		studentList [ listIndex ] = new Student(studentInfo);	
		listIndex ++;
		
		return listIndex;
	}
	

	
}
