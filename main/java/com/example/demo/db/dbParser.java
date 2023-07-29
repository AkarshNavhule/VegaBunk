package com.example.demo.db;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class dbParser {

    Statement stmt;
    Connection conn;
    public dbParser() {
        //String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/student_db";
        String USER = "root";
        String PASS = "2702";

        try{
            Driver myDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver( myDriver );

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getDob(String usn) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT dob FROM STUDENT WHERE usn='" + usn + "';");
        rs.next();
        Date dob = rs.getDate(1);
        return dob.toString();
    }

    public ResultSet getDetails(String usn) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT name,usn,branch,sem FROM student WHERE usn='" + usn + "';");
        rs.next();
        return rs;
    }

    public int[] getMarks(String usn) throws SQLException {
        String[] sub = {"EM4","DAA","DBMS","OS","JAVA","DBMSL","DAAL","PE"};
        String[] tests = { "mse1", "mse2", "mse3", "la1", "la2" };

        PreparedStatement pstmt;
        int[] marks = {0,0,0,0,0,0,0,0};

        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                pstmt = conn.prepareStatement("SELECT " + sub[i] + " FROM " + tests[j] +
                        " WHERE usn='" + usn + "';");
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                if (j < 3)
                    if(j == 2)
                        marks[i] += rs.getInt(1) * 0.2;
                    else marks[i] += rs.getInt(1) * 0.4;
                else marks[i] += rs.getInt(1);
            }
        }
        return marks;
    }
    public int[][] getTotalDoneClass() throws SQLException {
        String[] sub = {"EM4","DAA","DBMS","OS","JAVA","DBMSL","DAAL","PE"};
        int[][] attendance = new int[2][8];
        PreparedStatement pstmt = conn.prepareStatement("SELECT classDone, totalClass from subjects;");
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        for(int j = 0; j < 8;j++) {
            attendance[0][j] = rs.getInt(1);
            attendance[1][j] = rs.getInt(2);
        }
        return attendance;
    }
    public int[] getAttendance(String usn) throws SQLException {
        String[] sub = {"EM4","DAA","DBMS","OS","JAVA","DBMSL","DAAL","PE"};

        PreparedStatement pstmt;
        int[] attendance = new int[8];

        for(int i = 0; i < 8; i++) {
            pstmt = conn.prepareStatement("SELECT " + sub[i] + " FROM attendance WHERE usn='" + usn + "';");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            attendance[i] = rs.getInt(1);
        }
        return attendance;
    }
    public ResultSet getResMarks(String usn, String sub) throws SQLException {
        String[] tests = {"mse1", "mse2", "mse3", "la1", "la2"};

        PreparedStatement pstmt = null;
        ResultSet rs;
            String select = "";
            String from = "";
            String where = "";
            for(int j = 0; j < 5;j++) {
                if (j != 4) {
                    select += (tests[j] + "." + sub + ",");
                    from += (tests[j] + ",");
                    where += (tests[j] + ".usn='" + usn + "' AND ");

                } else {
                    select += (tests[j] + "." + sub);
                    from += (tests[j]);
                    where += (tests[j] + ".usn='" + usn + "';");
                }
            }
            String st = "select " + select +
                    " from " + from +
                    " where " + where;
            pstmt = conn.prepareStatement(st);

        rs = pstmt.executeQuery();
        rs.next();
        return rs;
    }

    public int[] getAttendedSub(String sub, String usn) throws SQLException {
        int[] attended = new int[3];
        PreparedStatement pstmt = conn.prepareStatement("SELECT " + sub + " FROM attendance WHERE usn='" + usn + "';");
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        attended[0] = rs.getInt(1);
        pstmt = conn.prepareStatement("select classDone, totalClass from subjects where sub='" + sub + "';");
        rs = pstmt.executeQuery();
        rs.next();
        attended[1] = rs.getInt(1);
        attended[2] = rs.getInt(2);
        return attended;
    }
    public void close() throws SQLException {
        stmt.close();
        conn.close();
    }

}
