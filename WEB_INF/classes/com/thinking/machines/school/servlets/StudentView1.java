package com.thinking.machines.school.servlets;
import com.thinking.machines.school.dao.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;

public class StudentView1 extends HttpServlet
{
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{
try
{
int vRoll_Number;
String vName;
String vAddress;
String vGender;
Date vDate_Of_Birth;
boolean vIndian;
int vCity_Code;
String vCity_Name;
Connection c=DAOConnection.getConnection();
Statement s=c.createStatement();
ResultSet resultSet;
resultSet=s.executeQuery("select * from student_view order by name");


PrintWriter pw=rs.getWriter();
rs.setContentType("text/html");
pw.println("<!doctype html>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charesultSetet='utf-8'>");
pw.println("<title>Web Application Programming - Style 1</title>");
pw.println("<script>");
pw.println("var selectedRow=null;");
pw.println("function selectRow(r)");
pw.println("{");
pw.println("if(r==selectedRow)return;");
pw.println("if(selectedRow!=null)");
pw.println("{");
pw.println("selectedRow.style.background='white';");
pw.println("selectedRow.style.color='black';");
pw.println("}");
pw.println("selectedRow=r;");
pw.println("r.style.background='gray';");
pw.println("r.style.color='black';");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<div style='width:98%;min-height:880px;border:1px solid black;padding:5px'>");
pw.println("<!--Header Division Starts -->");
pw.println("<div style='width:100%;min-height:90px;border-bottom:1px solid gray'>");
pw.println("<img src='/styleone/images/logo3.png'>&nbsp;&nbsp;&nbsp;");
pw.println("<span style='font-size:30pt;font-family:verdana;'>Urban Corporation </span>");
pw.println("</div>");
pw.println("<!--Header division ends -->");
pw.println("<div style='width:100;min-height:680px;'>");
pw.println("<!--links division starts -->");
pw.println("<div style='float:left;width:20%;min-height:670px;padding:5px'>");
pw.println("<a style='font-size:16pt' href='/styleone/students' >Students</a>");
pw.println("</div>");
pw.println("<!-- Link dividion ends -->");
pw.println("<!-- Content division starts -->");
pw.println("<div style='float:right;width:78%;min-height:670px;border-left:1px solid green;padding:5px'>");

pw.println("<table border='1'>");
pw.println("<thead>");
pw.println("<tr>");
pw.println("<th>S.No.</th>");
pw.println("<th>Roll number</th>");
pw.println("<th>Name</th>");
pw.println("<th>Gender</th>");
pw.println("<th>D.O.B.</th>");
pw.println("<th>City</th>");
pw.println("<th>Edit</th>");
pw.println("<th>Delete</th>");
pw.println("</tr>");
pw.println("</thead>");
int sno=0;
String stringDateOfBirth;
while(resultSet.next())
{
sno++;
vRoll_Number=resultSet.getInt("Roll_Number");
vName=resultSet.getString("Name").trim();
vAddress=resultSet.getString("Address").trim();
vGender=resultSet.getString("Gender");
vDate_Of_Birth=resultSet.getDate("Date_Of_Birth");
stringDateOfBirth=vDate_Of_Birth.getDate()+"/"+(vDate_Of_Birth.getMonth()+1)+"/"+(vDate_Of_Birth.getYear()+1900);
vIndian=resultSet.getBoolean("Indian");
vCity_Code=resultSet.getInt("City_Code");
vCity_Name=resultSet.getString("City_Name").trim();

pw.println("<tr style='cursor:pointer;' onclick='selectRow(this)'>");
pw.println("<td>"+sno+"</td>");
pw.println("<td>"+vRoll_Number+"</td>");
pw.println("<td>"+vName+"</td>");
if(vGender.equalsIgnoreCase("M"))
{
pw.println("<td><img src='/styleone/images/male_icon.png'></td>");
}
else
{
pw.println("<td><img src='/styleone/images/female_icon.png'></td>");
}
pw.println("<td>"+stringDateOfBirth+"</td>");
pw.println("<td>"+vCity_Name+"</td>");
pw.println("<td><a href='/styleone/editStudent?Roll_Number="+URLEncoder.encode(String.valueOf(vRoll_Number))+"'><img src='/styleone/images/edit_icon.png'></a></td>");
pw.println("<td><a href='/styleone/deleteStudent?Roll_Number="+URLEncoder.encode(String.valueOf(vRoll_Number))+"'><img src='/styleone/images/delete_icon.png'></a></td>");
pw.println("</tr>");
}
pw.println("</table>");

pw.println("</div>");
pw.println("<!-- Content division ends-->");
pw.println("</div><!--Temp dev -->");
pw.println("<!--footer division starts -->");
pw.println("<div style='text-align:center;widh:100%;min-height:50px;border-top:1px solid black;font-weight:bold;padding:2px'>");
pw.println("    &copy; Urbon Corporation 2020-2022");
pw.println(" </div>");
pw.println("<!--footer division ends -->");
pw.println("</div>");
pw.println("</body>");
pw.println("</html>");
}catch(Exception e)
{
System.out.println(e);//now remove after testing
//throw new DAOException(e.getMessage());
}
}
}

