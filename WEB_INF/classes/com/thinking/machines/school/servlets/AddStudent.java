package com.thinking.machines.school.servlets;
import com.thinking.machines.school.dao.*;
import com.thinking.machines.school.beans.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;

public class AddStudent extends HttpServlet
{

public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
int rollNumber;
String name="";
String address="";
String gender="";
boolean indian=false;
boolean exists=false;
int cityCode=-1;
String cityName="";
String dateOfBirthString="1900/01/01";
java.sql.Date dateOfBirth=null;


StudentBean sb=(StudentBean)request.getAttribute("studentBean");

rollNumber=sb.getRollNumber();
name=sb.getName();
gender=sb.getGender();
if(gender==null)gender="";
dateOfBirthString=sb.getDateOfBirth();
System.out.println(dateOfBirthString);
if(dateOfBirthString==null)
{
System.out.println("Null aya");
dateOfBirthString="";
}
dateOfBirth=java.sql.Date.valueOf(dateOfBirthString);
address=sb.getAddress();
if(sb.getIndian()==false)
{
indian=false;
}
else if(sb.getIndian()==true)
{
indian=true;
}
else
{
indian=false;
}
cityCode=sb.getCityCode();

System.out.println("Gender :"+gender+"Indian : "+indian+"DateOfBith : "+dateOfBirth);
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet;

PreparedStatement ps=connection.prepareStatement("select * from student where roll_number=?");
ps.setInt(1,rollNumber);
resultSet=ps.executeQuery();
exists=resultSet.next();
resultSet.close();
ps.close();
if(exists)
{
ErrorBean errorBean;
errorBean=new ErrorBean("add",false,"Student Exists",sb);
request.setAttribute("errorBean",errorBean);
request.setAttribute("studentBean",sb);
RequestDispatcher rd;
rd=request.getRequestDispatcher("/Students.jsp");
rd.forward(request,response);
}
else
{
ps=connection.prepareStatement("insert into student values(?,?,?,?,?,?,?);");
ps.setInt(1,rollNumber);
ps.setString(2,name);
ps.setString(3,address);
ps.setDate(4,dateOfBirth);
ps.setString(5,gender);
ps.setBoolean(6,indian);
ps.setInt(7,cityCode);
ps.executeUpdate();
ps.close();
ErrorBean errorBean;
errorBean=new ErrorBean("add",true,"Student Added",sb);
request.setAttribute("errorBean",errorBean);
request.setAttribute("studentBean",sb);
RequestDispatcher rd;
rd=request.getRequestDispatcher("/Students.jsp");
rd.forward(request,response);

}

}catch(Exception e)
{
ErrorBean errorBean;
StudentBean sb=(StudentBean)request.getAttribute("studentBean");
errorBean=new ErrorBean("add",false,e.getMessage(),sb);
request.setAttribute("errorBean",errorBean);
request.setAttribute("studentBean",sb);
try{
RequestDispatcher rd;
rd=request.getRequestDispatcher("/Students.jsp");
rd.forward(request,response);
}catch(Exception e2){}
e.printStackTrace();
System.out.println(e);//now remove after testing
//throw new DAOException(e.getMessage());
}
}
}

