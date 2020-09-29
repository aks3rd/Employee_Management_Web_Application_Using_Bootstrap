package com.thinking.machines.school.dao;
import java.sql.*;

public class DAOConnection
{
//private Connection c=null;
public static Connection getConnection() throws DAOException
{
try
{
Class.forName("com.mysql.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/j2eeuec6thsem2020db","akuser","AK_user2020");
return c;
}catch(Exception e)
{
System.out.println(e);//now remove after testing
throw new DAOException(e.getMessage());
}
}
}
