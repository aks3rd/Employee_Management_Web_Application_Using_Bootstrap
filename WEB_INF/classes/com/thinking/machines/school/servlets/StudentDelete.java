package com.thinking.machines.school.servlets;
import com.thinking.machines.school.dao.*;
import com.thinking.machines.school.beans.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;

public class StudentDelete extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
int vRollNumber;
String vName;
String vAddress;
String vGender;
java.sql.Date vDateOfBirth;
boolean vIndian;
int vCityCode;
String vCityName;
java.sql.Date dateOfBirth;
String dateOfBirthString;

Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet;


int rollNumber;
boolean exists;
StudentBean sb=(StudentBean)request.getAttribute("studentBean");
rollNumber=sb.getRollNumber();
PreparedStatement ps=connection.prepareStatement("delete from student where roll_Number=?");
ps.setInt(1,rollNumber);
ps.executeUpdate();
//exists=resultSet.next();
ps.close();

//PrintWriter pw=response.getWriter();
//response.setContentType("text/html");
MessageBean messageBean;
messageBean=new MessageBean("Student Deleted",true);
request.setAttribute("messageBean",messageBean);
RequestDispatcher rd;
rd=request.getRequestDispatcher("/Students.jsp");
rd.forward(request,response);

}catch(Exception e)
{
try{
MessageBean messageBean;
messageBean=new MessageBean(e.getMessage(),true);
request.setAttribute("messageBean",messageBean);
RequestDispatcher rd;
rd=request.getRequestDispatcher("/Students.jsp");
rd.forward(request,response);
}catch(Exception e2){}
System.out.println(e);//now remove after testing
//throw new DAOException(e.getMessage());
}
}
}

