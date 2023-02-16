package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class init {
	public void connectDB() {
		//String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/";
		String USER = "cheeseburger";
		String PASS = "Iambatman@69";
   
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
					"CREATE TABLE subjects(branch varchar(4), sem int(2), subName varchar(30), subCode varchar(10),classDone int(3), totalClass int(3));",
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
			
			
			for(int i = 0; i < 9; i++) {
				PreparedStatement student = conn.prepareStatement("INSERT INTO student VALUES(?,?,'CSE',4, 'B',?);");
				student.setString(1,names[i]);
				student.setString(2, "1NT20CS00"+(i+1));
				student.setString(3, dob[i]);
				student.execute();
			}
			
			int val;
			for(int i = 0; i < 9; i++) {
				PreparedStatement test;
				for(int j = 0; j < 3; j++) {
					test = conn.prepareStatement("INSERT INTO " + tests[j] + " VALUES(?,?,?,?,?,?,?,?,?);");
					test.setString(1, "1NT20CS00"+(i+1));
					for(int k = 0;k < 8;k++) {
						val = (int)(Math.random() * 13) + 1;
						test.setInt((k+2), val);
					}
					test.execute();
				}
				for(int j = 0; j < 2; j++) {
					test = conn.prepareStatement("INSERT INTO " + tests[j+3] + " VALUES(?,?,?,?,?,?,?,?,?);");
					test.setString(1, "1NT20CS00"+(i+1));
					for(int k = 0;k < 8;k++) {
						val = (int)(Math.random() * 6) + 1;
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
