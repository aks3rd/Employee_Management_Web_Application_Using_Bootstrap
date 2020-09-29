package com.thinking.machines.school.servlets;
import com.thinking.machines.school.dao.*;
import com.thinking.machines.school.beans.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;

public class EditStudent extends HttpServlet
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

System.out.println("Roll Number : "+rollNumber);
System.out.println("Name : "+name);
System.out.println("Address : "+address);
System.out.println("Gender : "+gender);
System.out.println("DOB : "+dateOfBirth);
System.out.println("Indian : "+indian);
System.out.println("City Code : "+cityCode);

Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet;

PreparedStatement ps=connection.prepareStatement("update student set name=?,address=?,date_of_birth=?,gender=?,indian=?,city_code=? where roll_number=?;");

ps.setString(1,name);
ps.setString(2,address);
ps.setDate(3,dateOfBirth);
ps.setString(4,gender);
ps.setBoolean(5,indian);
ps.setInt(6,cityCode);
ps.setInt(7,rollNumber);
ps.executeUpdate();
ps.close();

ErrorBean errorBean;
errorBean=new ErrorBean("edit",true,"Student Updated",sb);
request.setAttribute("errorBean",errorBean);
request.setAttribute("studentBean",sb);
RequestDispatcher rd;
rd=request.getRequestDispatcher("/Students.jsp");
rd.forward(request,response);

}catch(Exception e)
{
ErrorBean errorBean;
StudentBean sb=(StudentBean)request.getAttribute("studentBean");
errorBean=new ErrorBean("edit",false,e.getMessage(),sb);
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

