package com.thinking.machines.dao;
import com.thinking.machines.dao.*;
import java.sql.*;
public class Test
{
public Test()
{
try
{
System.out.println("Hello Test");
Connection c=DAOConnection.getConnection();
System.out.println(c);
}catch(Exception e)
{
System.out.println(e);
}
}
public static void main(String gg[])
{
Test t=new Test();
}
}
