package com.thinking.machines.school.servlets;
import com.thinking.machines.school.dao.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;

public class StudentView extends HttpServlet
{
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
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


PrintWriter pw=rs.getWriter();
rs.setContentType("text/html");

pw.println("<!doctype html>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title>Web Application Programming - Style 1</title>");
pw.println("<script>");
pw.println("function City()");
pw.println("   {");
pw.println("     this.code=0;");
pw.println("     this.name='';");
pw.println("   }");
pw.println("   function Student()");
pw.println("   {");
pw.println("     this.rollNumber=0;");
pw.println("     this.name='';");
pw.println("     this.gender='';");
pw.println("     this.dateOfBirth='';");
pw.println("     this.address='';");
pw.println("     this.indian=false;");
pw.println("     this.city=null;");
pw.println("   }");
pw.println("   function ViewModel()");
pw.println("   {");
pw.println("    this.students=[];");
pw.println("    this.selectedRow=null;");
pw.println("    this.selectedStudentIndex=-1;");
pw.println("    this.cities=[];");
pw.println("   }");
pw.println("   var viewModel=new ViewModel();");
pw.println("   function createGoToViewButtonClickedHandler()");
pw.println("   {");
pw.println("    return function()");
pw.println("    {");
pw.println("     goToViewSection();");
pw.println("    };");
pw.println("   }");

pw.println("   function createEditIconClickedHandler(index,row)");
pw.println("   {");
pw.println("    return function()");
pw.println("    {");
pw.println("     displayEditSection(index,row);");
pw.println("    };");
pw.println("   }");
pw.println("   function createDeleteIconClickedHandler(index,row)");
pw.println("   {");
pw.println("    return function()");
pw.println("    {");
pw.println("     displayDeleteConfirmationSection(index,row);");
pw.println("    };");
pw.println("   }");
pw.println("   function createDetailsIconClickedHandler(index,row)");
pw.println("   {");
pw.println("    return function()");
pw.println("    {");
pw.println("     displayDetailsSection(index,row);");
pw.println("    };");
pw.println("   }");
pw.println("   function createRowClickedHandler(index,row)");
pw.println("   {");
pw.println("    return function(){");
pw.println("    selectRow(index,row);");
pw.println("   };");
pw.println("   }");

pw.println("   function selectRow(index,row)");
pw.println("   {");
pw.println("    if(index==viewModel.selectedStudentIndex) return;");
pw.println("    if(viewModel.selectedRow!=null)");
pw.println("    {");
pw.println("      viewModel.selectedRow.style.background='white';");
pw.println("      viewModel.selectedRow.style.color='black';");
pw.println("    }");
pw.println("    viewModel.selectedStudentIndex=index;");
pw.println("    viewModel.selectedRow=row;");
pw.println("    row.style.background='black';");
pw.println("    row.style.color='white';");
pw.println("   }");

pw.println("   function searchStudent(studentName)");
pw.println("   {");
pw.println("   studentName.style.color='black'");
pw.println("   var sn=studentName.value.trim().toUpperCase();");
pw.println("   if(sn.length==0)return;");
pw.println("   var i=0;");
pw.println("   while(i<viewModel.students.length)");
pw.println("   {");
pw.println("    if(viewModel.students[i].name.toUpperCase().startsWith(sn))break;");
pw.println("    i++;");
pw.println("   }");
pw.println("   if(i==viewModel.students.length)");
pw.println("   {");
pw.println("    studentName.style.color='red';");
pw.println("    return;");
pw.println("   }");
pw.println("   else");
pw.println("   {");
pw.println("    var tr=document.getElementById('ixix'+i)");
pw.println("    selectRow(i,tr);");
pw.println("    tr.scrollIntoView(true);");
pw.println("   }");
pw.println("   }");

pw.println("   function searchStudentByButtonClickedAction()");
pw.println("   {");
pw.println("   studentName=document.getElementById('searchBox');");
pw.println("   searchStudent(studentName);");
pw.println("   }");
pw.println("   function goToViewSection()");
pw.println("   {");
pw.println("    var grid;");
pw.println("    var studentDetailsSection;");
pw.println("    grid=document.getElementById('gridSection');");
pw.println("    grid.style.display='block';");
pw.println("    studentDetailsSection=document.getElementById('studentDetailsSection');");
pw.println("    studentDetailsSection.style.display='none';     ");
pw.println("    studentEditSection=document.getElementById('studentEditSection');");
pw.println("    studentEditSection.style.display='none';");
pw.println("    studentDeleteSection=document.getElementById('studentDeleteSection');");
pw.println("    studentDeleteSection.style.display='none';");
pw.println("    studentAddSection=document.getElementById('studentAddSection');");
pw.println("    studentAddSection.style.display='none';");
pw.println("    document.getElementById('searchBox').disabled=false;;");

pw.println("   }");
pw.println("   function displayAddSection()");
pw.println("   {");
pw.println("    var grid;");
pw.println("    var studentDetailsSection,studentEditSection,studentDeleteSection,studentAddSection;");
pw.println("    grid=document.getElementById('gridSection');");
pw.println("    grid.style.display='none';");
pw.println("    studentAddSection=document.getElementById('studentAddSection');");
pw.println("    studentAddSection.style.display='block';");
pw.println("    studentDeleteSection=document.getElementById('studentEditSection');");
pw.println("    studentDeleteSection.style.display='none';");
pw.println("    studentEditSection=document.getElementById('studentEditSection');");
pw.println("    studentEditSection.style.display='none';");
pw.println("    studentDetailsSection=document.getElementById('studentDetailsSection');");
pw.println("    studentDetailsSection.style.display='none';");
pw.println("    document.getElementById('searchBox').disabled=true;;");
pw.println("    var button=document.getElementById('addSectionGoToViewButton');");
pw.println("    button.onclick=createGoToViewButtonClickedHandler();");
    
pw.println("    return;");
pw.println("   }");
pw.println("   function displayDeleteConfirmationSection(index,row)");
pw.println("   {");
pw.println("    var grid;");
pw.println("    var studentDetailsSection,studentEditSection,studentAddSection,studentDeleteSection;");
pw.println("    grid=document.getElementById('gridSection');");
pw.println("    grid.style.display='none';");
pw.println("    studentDetailsSection=document.getElementById('studentDetailsSection');");
pw.println("    studentDetailsSection.style.display='none';");
pw.println("    studentEditSection=document.getElementById('studentEditSection');");
pw.println("    studentEditSection.style.display='none';");
pw.println("    studentAddSection=document.getElementById('studentAddSection');");
pw.println("    studentAddSection.style.display='none';");
pw.println("    studentDeleteSection=document.getElementById('studentDeleteSection');");
pw.println("    studentDeleteSection.style.display='block';");
pw.println("    document.getElementById('searchBox').disabled=true;;");
pw.println("    var student=viewModel.students[index];");
pw.println("    var button;");

pw.println("    var rollNumberL=document.getElementById('deleteSectionRollNumberL');");
pw.println("    rollNumberL.innerHTML=student.rollNumber;");
pw.println("    var rollNumber=document.getElementById('deleteSectionRollNumber');");
pw.println("    rollNumber.value=student.rollNumber;");
pw.println("    var name=document.getElementById('deleteSectionName');");
pw.println("    name.innerHTML=student.name;");
pw.println("    var gender=document.getElementById('deleteSectionGender');");
pw.println("    gender.innerHTML=student.gender;");
pw.println("    var dateOfBirth=document.getElementById('deleteSectionDateOfBirth');");
pw.println("    dateOfBirth.innerHTML=student.dateOfBirth;");
pw.println("    var address=document.getElementById('deleteSectionAddress');");
pw.println("    address.innerHTML=student.address;");
pw.println("    var indian=document.getElementById('deleteSectionIndian');");
pw.println("    indian.innerHTML=student.indian;");
pw.println("    var cityCode=document.getElementById('deleteSectionCityCode')");;
pw.println("    cityCode.innerHTML=student.city.code;");
pw.println("    var cityName=document.getElementById('deleteSectionCityName');");
pw.println("    cityName.innerHTML=student.city.name;");
pw.println("    button=document.getElementById('deleteSectionGoToViewButton');");
pw.println("    button.onclick=createGoToViewButtonClickedHandler();");

pw.println("   }");
pw.println("   function displayEditSection(index,row)");
pw.println("   {");
pw.println("    var grid;");
pw.println("    var studentDetailsSection,studentEditSection;");

pw.println("    grid=document.getElementById('gridSection');");
pw.println("    grid.style.display='none';");
pw.println("    studentDetailsSection=document.getElementById('studentDetailsSection');");
pw.println("    studentDetailsSection.style.display='none';");
pw.println("    studentAddSection=document.getElementById('studentAddSection');");
pw.println("    studentAddSection.style.display='none';");
pw.println("    studentDeleteSection=document.getElementById('studentDeleteSection');");
pw.println("    studentDeleteSection.style.display='none';");
pw.println("    studentEditSection=document.getElementById('studentEditSection');");
pw.println("    studentEditSection.style.display='block';");
pw.println("    document.getElementById('searchBox').disabled=true;;");
pw.println("    var student=viewModel.students[index];");
pw.println("    var button;");

pw.println("    var rollNumberL=document.getElementById('editSectionRollNumberL');");
pw.println("    rollNumberL.innerHTML=student.rollNumber;");
pw.println("    var rollNumber=document.getElementById('editSectionRollNumber');");
pw.println("    rollNumber.value=student.rollNumber;");
pw.println("    var name=document.getElementById('editSectionName');");
pw.println("    name.value=student.name;");
pw.println("    if(student.gender=='M')");
pw.println("    {");
pw.println("    var gender=document.getElementById('editSectionMale');");
pw.println("    gender.checked=true;");
pw.println("    }");
pw.println("    else");
pw.println("    {");
pw.println("    var gender=document.getElementById('editSectionFemale');");
pw.println("    gender.checked=true;");
pw.println("    }");
pw.println("    var dateOfBirth=document.getElementById('editSectionDateOfBirth');");
pw.println("    dateOfBirth.value=Date.parse(student.dateOfBirth);");
//pw.println("    dateOfBirth.valueAsDate=new Date(student.dateOfBirth);");

pw.println("    var address=document.getElementById('editSectionAddress');");
pw.println("    address.value=student.address;");
pw.println("    if(student.indian==true)");
pw.println("    {");
pw.println("    var indian=document.getElementById('editSectionIndian');");
pw.println("    indian.checked=true;");
pw.println("    }");
pw.println("    else");
pw.println("    {");
pw.println("    var indian=document.getElementById('editSectionIndian');");
pw.println("    indian.checked=false;");
pw.println("    }");
pw.println("    var cityCode=document.getElementById('editSectionCity');");
pw.println("    cityCode.value=student.city.code;");
//pw.println("    var cityName=document.getElementById('editSectionCityName');");
//pw.println("    cityName.value=student.city.name;");
pw.println("    button=document.getElementById('editSectionGoToViewButton');");
pw.println("    button.onclick=createGoToViewButtonClickedHandler();");

pw.println("   }");
pw.println("   function displayDetailsSection(index,row)");
pw.println("   {");
pw.println("    var grid;");
pw.println("    var studentDetailsSection,studentEditSection;");
pw.println("    grid=document.getElementById('gridSection');");
pw.println("    grid.style.display='none';");
pw.println("    studentDetailsSection=document.getElementById('studentDetailsSection');");
pw.println("    studentDetailsSection.style.display='block';");
pw.println("    studentEditSection=document.getElementById('studentEditSection');");
pw.println("    studentEditSection.style.display='none';");
pw.println("    studentAddSection=document.getElementById('studentAddSection');");
pw.println("    studentAddSection.style.display='none';");
pw.println("    studentDeleteSection=document.getElementById('studentDeleteSection');");
pw.println("    studentDeleteSection.style.display='none';");
pw.println("    document.getElementById('searchBox').disabled=true;;");

pw.println("    var student=viewModel.students[index];");
pw.println("    var button;");

pw.println("    var rollNumber=document.getElementById('detailsSectionRollNumber');");
pw.println("    rollNumber.innerHTML=student.rollNumber;");
pw.println("    var name=document.getElementById('detailsSectionName');");
pw.println("    name.innerHTML=student.name;");
pw.println("    var gender=document.getElementById('detailsSectionGender');");
pw.println("    gender.innerHTML=student.gender;");
pw.println("    var dateOfBirth=document.getElementById('detailsSectionDateOfBirth');");
pw.println("    dateOfBirth.innerHTML=student.dateOfBirth;");
pw.println("    var address=document.getElementById('detailsSectionAddress');");
pw.println("    address.innerHTML=student.address;");
pw.println("    var indian=document.getElementById('detailsSectionIndian');");
pw.println("    indian.innerHTML=student.indian;");
pw.println("    var cityCode=document.getElementById('detailsSectionCityCode');");
pw.println("    cityCode.innerHTML=student.city.code;");
pw.println("    var cityName=document.getElementById('detailsSectionCityName');");
pw.println("    cityName.innerHTML=student.city.name;");
pw.println("    button=document.getElementById('detailsSectionGoToViewButton');");
pw.println("    button.onclick=createGoToViewButtonClickedHandler();");
pw.println("   }");



pw.println("   function initView()");
pw.println("   {");

resultSet=statement.executeQuery("select * from student_view order by name");
int i=0;
while(resultSet.next())
{
System.out.println("Stdeunt Chala");
pw.println("s=new Student();");
pw.println("s.rollNumber="+resultSet.getInt("Roll_Number")+";");
pw.println("s.name=\""+resultSet.getString("Name").trim()+"\";");
pw.println("s.address=\""+resultSet.getString("Address").trim()+"\";");
pw.println("s.gender=\""+resultSet.getString("Gender")+"\";");
dateOfBirth=resultSet.getDate("Date_Of_Birth");
dateOfBirthString=dateOfBirth.getDate()+"-"+(dateOfBirth.getMonth()+1)+"-"+(dateOfBirth.getYear()+1900);
//dateOfBirthString=(dateOfBirth.getYear()+1900)+"-"+(dateOfBirth.getMonth()+1)+"-"+dateOfBirth.getDate();
pw.println("s.dateOfBirth=\""+dateOfBirthString+"\";");
pw.println("s.indian="+resultSet.getBoolean("Indian")+";");
pw.println("s.city=new City();");
pw.println("s.city.code="+resultSet.getInt("City_Code")+";");
pw.println("s.city.name=\""+resultSet.getString("City_Name").trim()+"\";");
pw.println("viewModel.students["+i+"]=s;");
i++;
}
resultSet.close();
resultSet=statement.executeQuery("select * from city order by name");

i=0;
while(resultSet.next())
{
System.out.println("Chala");
pw.println("city=new City();");
pw.println("city.code="+resultSet.getInt("code")+";");
pw.println("city.name=\""+resultSet.getString("name").trim()+"\";");
pw.println("viewModel.cities["+i+"]=city;");
i++;
}

statement.close();
connection.close();
    

pw.println("    var addFormCityCode=document.getElementById(\"addSectionCity\");");
pw.println("    var editFormCityCode=document.getElementById(\"editSectionCity\");");
pw.println("    var ci=0;");
pw.println("    var option;");
pw.println("    while(ci<viewModel.cities.length)");
pw.println("    {");
pw.println("     option=document.createElement(\"option\");");
pw.println("     option.value=viewModel.cities[ci].code;");
pw.println("     option.text=viewModel.cities[ci].name;");
pw.println("     addFormCityCode.appendChild(option);");
pw.println("     option=document.createElement(\"option\");");
pw.println("     option.value=viewModel.cities[ci].code;");
pw.println("     option.text=viewModel.cities[ci].name;");
pw.println("     editFormCityCode.appendChild(option);");
pw.println("     ci++;");
pw.println("    }");
pw.println("    var i,tr,td,textNode,img;");
pw.println("    var grid=document.getElementById('studentViewGrid');");
pw.println("    i=0;");
pw.println("    while(i<viewModel.students.length)");
pw.println("    {");
pw.println("     tr=document.createElement('tr');");
pw.println("     td=document.createElement('td');");
pw.println("     textNode=document.createTextNode(i+1+'.');");
pw.println("     td.appendChild(textNode);");
pw.println("     tr.appendChild(td);");
     
pw.println("     td=document.createElement('td');");
pw.println("     textNode=document.createTextNode(viewModel.students[i].rollNumber);");
pw.println("     td.appendChild(textNode);");
pw.println("     tr.appendChild(td);");
     
pw.println("     td=document.createElement('td');");
pw.println("     textNode=document.createTextNode(viewModel.students[i].name);");
pw.println("     td.appendChild(textNode);");
pw.println("     tr.appendChild(td);");
     
pw.println("     td=document.createElement('td');");
pw.println("     img=document.createElement('img');");
pw.println("     if(viewModel.students[i].gender=='M')img.src='/styleone/images/male_icon.png';");             
pw.println("     else img.src='/styleone/images/female_icon.png';");
pw.println("     td.appendChild(img);");
pw.println("     tr.appendChild(td);");

pw.println("     td=document.createElement('td');");
pw.println("     textNode=document.createTextNode(viewModel.students[i].dateOfBirth);");
pw.println("     td.appendChild(textNode);");
pw.println("     tr.appendChild(td);");
    
pw.println("     td=document.createElement('td');");
pw.println("     textNode=document.createTextNode(viewModel.students[i].city.name);");
pw.println("     td.appendChild(textNode);");
pw.println("     tr.appendChild(td);");
     
pw.println("     td=document.createElement('td');");
pw.println("     img=document.createElement('img');");
pw.println("     img.src='/styleone/images/edit_icon.png';");
pw.println("     img.onclick=createEditIconClickedHandler(i,tr);");
pw.println("     td.appendChild(img);");
pw.println("     tr.appendChild(td);");
     
pw.println("     td=document.createElement('td');");
pw.println("     img=document.createElement('img');");
pw.println("     img.src='/styleone/images/delete_icon2.png';");
pw.println("     img.onclick=createDeleteIconClickedHandler(i,tr);");
pw.println("     td.appendChild(img);");
pw.println("     tr.appendChild(td);");
     
     
pw.println("     td=document.createElement('td');");
pw.println("     img=document.createElement('img');");
pw.println("     img.src='/styleone/images/details_icon2.png';");        
pw.println("     img.onclick=createDetailsIconClickedHandler(i,tr);");
pw.println("     td.appendChild(img);");
pw.println("     tr.appendChild(td);");
     
pw.println("     tr.onclick=createRowClickedHandler(i,tr);");
pw.println("     tr.id='ixix'+i;");
pw.println("     tr.style.cursor='pointer';");
pw.println("     grid.appendChild(tr);");
pw.println("     i++;");
pw.println("    }");
//setTimeOut Function something for span
pw.println("    setTimeout(function(){  }, 3000);");
pw.println("   }");
pw.println("   window.addEventListener('load',initView);");
pw.println("  </script>");
pw.println(" </head>");
pw.println(" <body>");
pw.println("  <div style='width:98%;min-height:880px;border:1px solid black;padding:5px'>");
pw.println("   <!--Header Division Starts -->");
pw.println("   <div style='width:100%;min-height:90px;border-bottom:1px solid gray'>");
pw.println("    <img src='/styleone/images/logo3.png'>&nbsp;&nbsp;&nbsp;");
pw.println("    <span style='font-size:30pt;font-family:verdana;'>Urban Corporation </span>");
pw.println("   </div>");
pw.println("   <!--Header division ends -->");
pw.println("   <div style='width:100;min-height:680px;'>");
pw.println("   <!--links division starts -->");
pw.println("   <div style='float:left;width:20%;min-height:670px;padding:5px'>");
pw.println("    <a style='font-size:16pt' href='/styleone/students' >Students</a>");
pw.println("   </div>");
pw.println("   <!-- Link dividion ends -->");
pw.println("   <!-- Content division starts -->");
pw.println("   <div style='float:right;width:78%;min-height:670px;border-left:1px solid green;padding:5px'>");
pw.println("   <div id='toolSection' name='toolSection'>");
pw.println("    <h1 style='display:inline-block'>Student</h1>");
pw.println("   <input type='text' id='searchBox' name='searchBox' placeholder='Search Student' style='display:inline' oninput='searchStudent(this)'><img src='/styleone/images/search_icon.png' onclick='searchStudentByButtonClickedAction()'><br>");
pw.println("   <img src='/styleone/images/add_icon.png' onclick='displayAddSection()'>");
pw.println("   </div>");
pw.println("   <div id='gridSection' name='gridSection' style='width:60%;height:270px;border:1px solid black; overflow:scroll;'>");
pw.println("   <span id='gridErrorSection' name='gridErrorSection'></span><br>");
pw.println("   <table border='1px' id='studentViewGrid'>");
pw.println("    <thead>");
pw.println("     <tr>");
pw.println("      <th>S.No.</th>");
pw.println("      <th>Roll_Number</th>");
pw.println("      <th>Name</th>");
pw.println("      <th>Gender</th>");
pw.println("      <th>D.O.B.</th>");
pw.println("      <th>City</th>");
pw.println("      <th>Edit</th>");
pw.println("      <th>Delete</th>");
pw.println("      <th>Details</th>");
pw.println("     </tr>");
pw.println("    </thead>");
pw.println("    <tbody>");
pw.println("    </tbody>");
pw.println("   </table>");
pw.println("   </div>");

pw.println("   <div id='studentDetailsSection' name='studentDetailsSection' style='width:78%;min-height:500px;border:1px solid black; display:none;'>");
pw.println("   <!--This is studentDetailsSection -->");
pw.println("   Roll Number : <label id='detailsSectionRollNumber'></label><br>");
pw.println("   Name :<label id='detailsSectionName'></label><br>");
pw.println("   Gender :<label id='detailsSectionGender'></label><br>");
pw.println("   Date Of Birth :<label id='detailsSectionDateOfBirth'></label><br>");
pw.println("   Address :<label id='detailsSectionAddress'></label><br>");
pw.println("   Indian :<label id='detailsSectionIndian'></label><br>");
pw.println("   City Code :<label id='detailsSectionCityCode'></label><br>");
pw.println("   City Name :<label id='detailsSectionCityName'></label><br>");
pw.println("   <button id='detailsSectionGoToViewButton'>Go To View</button>");
pw.println("   </div>");

pw.println("   <div id='studentAddSection' name='studentAddSection' style='width:78%;min-height:500px;border:1px solid black; display:none;'>");
pw.println("   <!--This is studentAddSection -->");
pw.println("   <span id='addErrorSection' name='addErrorSection'></span><br>");
pw.println("   <form action='/styleone/addStudent'>");
pw.println("   Roll Number : <input type='number' id='addSectionRollNumber' name='rollNumber'><br>");
pw.println("   Name :<input type='text' id='addSectionName' name='name'><br>");
pw.println("   Gender :<label for='addSectionMale'>Male</label><input type='radio' id='addSectionMale' name='gender' value='M'><label for='addSectionFemale'>Female</label><input type='radio' id='addSectionFemale' name='gender' value='F'><br>");
pw.println("   Date Of Birth :<input type='date' id='addSectionDateOfBirth' name='dateOfBirth' required><br>");
pw.println("   Address :<textarea id='addSectionAddress' name='address'></textarea><br>");
pw.println("   Indian :<input type='checkbox' id='addSectionIndian' name='indian'><br>");
pw.println("   City :<select id='addSectionCity' name='cityCode'>");
pw.println("         <option value='-1'>&lt;Select City&gt;</option>");
pw.println("         </select><br><br>");
pw.println("   <button type='submit' id='addSectionSubmitButton'>Save</button>");
pw.println("   <button type='button' id='addSectionGoToViewButton'>Go to View</button>");
pw.println("   </form>");
pw.println("   </div>");

pw.println("   <div id='studentEditSection' name='studentEditSection' style='width:78%;min-height:500px;border:1px solid black; display:none;'>");
pw.println("   <!--This is studentEditSection -->");
pw.println("   <span id='editErrorSection' name='editErrorSection'></span><br>");
pw.println("   <form action='/styleone/editStudent'>");
pw.println("   Roll Number : <label id='editSectionRollNumberL'></label><br>");
pw.println("   <input type='hidden' id='editSectionRollNumber' name='rollNumber'>");
pw.println("   Name :<input type='text' id='editSectionName' name='name'><br>");
pw.println("   Gender :<label for='editSectionMale'>Male</label><input type='radio' id='editSectionMale' name='gender' value='M'><label for='editSectionFemale'>Female</label><input type='radio' id='editSectionFemale' name='gender' value='F'><br>");
pw.println("   Date Of Birth :<input type='date' id='editSectionDateOfBirth' name='dateOfBirth' required><br>");
pw.println("   Address :<textarea id='editSectionAddress' name='address'></textarea><br>");
pw.println("   Indian :<input type='checkbox' id='editSectionIndian' name='indian'><br>");
pw.println("   City :<select id='editSectionCity' name='cityCode'>");
pw.println("         <option value='-1'>&lt;Select City&gt;</option>");
pw.println("         </select><br><br>");
pw.println("   <button type='submit' id='editSectionSubmitButton'>Update</button>");
pw.println("   <button type='button' id='editSectionGoToViewButton'>Go to View</button>");
pw.println("   </form>");
pw.println("   </div>");

pw.println("   <div id='studentDeleteSection' name='studentDeleteSection' style='width:78%;min-height:500px;border:1px solid black; display:none;'>");
pw.println("   <!--This is studentDeleteSection -->");
pw.println("   <form action='/styleone/deleteStudent'>");
pw.println("   <span id='deleteErrorSection' style='display:none;' ></span><br>");
pw.println("   Roll Number : <label id='deleteSectionRollNumberL'></label><br>");
pw.println("   <input type='hidden' id='deleteSectionRollNumber' name='rollNumber'>");
pw.println("   Name :<label id='deleteSectionName'></label><br>");
pw.println("   Gender :<label id='deleteSectionGender'></label><br>");
pw.println("   Date Of Birth :<label id='deleteSectionDateOfBirth'></label><br>");
pw.println("   Address :<label id='deleteSectionAddress'></label><br>");
pw.println("   Indian :<label id='deleteSectionIndian'></label><br>");
pw.println("   City Code :<label id='deleteSectionCityCode'></label><br>");
pw.println("   City Name :<label id='deleteSectionCityName'></label><br>");
pw.println("   <button type='submit' id='deleteSectionSubmitButton'>Delete</button>");
pw.println("   <button type='button' id='deleteSectionGoToViewButton'>Go to View</button>");
pw.println("   </form>");

pw.println("   </div>");


pw.println("   </div>");
pw.println("   <!-- Content division ends-->");
pw.println("   </div><!--Temp dev -->");
pw.println("   <!--footer division starts -->");
pw.println("   <div style='text-align:center;widh:100%;min-height:50px;border-top:1px solid black;font-weight:bold;padding:2px'>");
pw.println("    &copy; Urbon Corporation 2020-2022");
pw.println("   </div>");
pw.println("   <!--footer division ends -->");
pw.println("  </div>");
pw.println(" </body>");
pw.println("</html>");
}catch(Exception e)
{
System.out.println(e);//now remove after testing
//throw new DAOException(e.getMessage());
}
}
}

