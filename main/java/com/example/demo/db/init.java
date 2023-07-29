package com.example.demo.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import com.mysql.cj.jdbc.Driver;
import javafx.scene.image.Image;

import static com.sun.webkit.graphics.WCImage.getImage;


public class init {
	public void connectDB() {
		//String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/";
		String USER = "root";
		String PASS = "2702";
   
		ResultSet rs;
		try{
			Driver myDriver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver( myDriver );
      
			System.out.println("Connecting to database...");
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
  
			System.out.println("Creating statement...");
			Statement stmt = conn.createStatement();

			rs = conn.getMetaData().getCatalogs();

			while (rs.next()) {
				if("student_db".equals(rs.getString(1))) {
					stmt.execute("DROP DATABASE student_db;");
					break;
				}
			}

			String[] sql = {
					"CREATE DATABASE student_db;",
					"USE student_db;",
					"CREATE TABLE student(usn varchar(10) primary key, name varchar(20) not null,branch varchar(4), sem int(2), sec char(1), dob date);",
					"CREATE TABLE subjects(branch varchar(4), sem int(2),sub varchar(6), subName varchar(40), subCode varchar(10),classDone int(3), totalClass int(3));",
					"CREATE TABLE mse1(usn varchar(10) references student(usn), EM4 int(3), DAA int(3), DBMS int(3), OS int(3), JAVA int(3), DBMSL int(3), DAAL int(3), PE int(3));",
					"CREATE TABLE mse2(usn varchar(10) references student(usn), EM4 int(3), DAA int(3), DBMS int(3), OS int(3), JAVA int(3), DBMSL int(3), DAAL int(3), PE int(3));",
					"CREATE TABLE mse3(usn varchar(10) references student(usn), EM4 int(3), DAA int(3), DBMS int(3), OS int(3), JAVA int(3), DBMSL int(3), DAAL int(3), PE int(3));",
					"CREATE TABLE la1(usn varchar(10) references student(usn), EM4 int(3), DAA int(3), DBMS int(3), OS int(3), JAVA int(3), DBMSL int(3), DAAL int(3), PE int(3));",
					"CREATE TABLE la2(usn varchar(10) references student(usn), EM4 int(3), DAA int(3), DBMS int(3), OS int(3), JAVA int(3), DBMSL int(3), DAAL int(3), PE int(3));",
					"CREATE TABLE attendance(usn varchar(10) references student(usn), EM4 int(3), DAA int(3), DBMS int(3), OS int(3), JAVA int(3), DBMSL int(3), DAAL int(3), PE int(3));",
					};
			for(int i = 0; i < sql.length; i++)
				stmt.execute(sql[i]);

			String[] names = {"Abhishek","Akarsh","Akif", "Keerthan", "Nayaz","Nitin", "Rohan", "Sarji","Varun"};
			String[] dob = {"2002-01-01","2002-02-02","2002-03-03", "2002-04-04", "2002-05-05", "2002-06-06", "2002-07-07", "2002-08-08", "2002-09-09" };
			String[] tests = { "mse1", "mse2", "mse3", "la1", "la2" };

			int val;
			for(int i = 0; i < 9; i++) {
				PreparedStatement student = conn.prepareStatement("INSERT INTO student VALUES(?,?,'CSE',4, 'B',?);");
				student.setString(1,"1NT20CS00"+(i+1));
				student.setString(2,names[i]);
				student.setString(3, dob[i]);
				student.execute();

				PreparedStatement attend = conn.prepareStatement("INSERT INTO attendance VALUES(?,?,?,?,?,?,?,?,?);");
				attend.setString(1,"1NT20CS00"+(i+1));
				for(int k = 0; k < 8; k++) {
					val = (int)(Math.random() * (25 - 13 + 1)) + 13;
					attend.setInt((k + 2),val);
				}
				attend.execute();
			}

			PreparedStatement subjects = conn.prepareStatement("INSERT INTO subjects VALUES('CSE',4,?,?,?,?,?);");
			String[] sub = {"EM4","DAA","DBMS","OS","JAVA","DBMSL","DAAL","PE"};
			String[] subname = {"Engineering Mathematics-4","Design and Analysis of Algorithm","Database Management Systems","Operating System","Application Development using Java","Database Management Systems Lab","Design and Analysis of Algorithm Lab","Program Elective"};
			String[] subcode ={"18MAT41A","18CS42","18CS43","18CS44","18CS45","18CSL47","18CSL48","18CSE462"};

			for(int i=0;i<8;i++)
			{
				subjects.setString(1,sub[i]);
				subjects.setString(2,subname[i]);
				subjects.setString(3,subcode[i]);
				subjects.setInt(4,25);
				subjects.setInt(5,40);
				subjects.execute();
			}

			for(int i = 0; i < 9; i++) {
				PreparedStatement test;
				for(int j = 0; j < 3; j++) {
					test = conn.prepareStatement("INSERT INTO " + tests[j] + " VALUES(?,?,?,?,?,?,?,?,?);");
					test.setString(1, "1NT20CS00"+(i+1));
					for(int k = 0;k < 8;k++) {
						val = (int)(Math.random() * (30 - 12 + 1)) + 12;
						test.setInt((k+2), val);
					}
					test.execute();
				}
				for(int j = 0; j < 2; j++) {
					test = conn.prepareStatement("INSERT INTO " + tests[j+3] + " VALUES(?,?,?,?,?,?,?,?,?);");
					test.setString(1, "1NT20CS00"+(i+1));
					for(int k = 0;k < 8;k++) {
						val = (int)(Math.random() * (10 - 5 + 1)) + 5;
						test.setInt((k+2), val);
					}
					test.execute();
				}
			}

			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	   	}
}
