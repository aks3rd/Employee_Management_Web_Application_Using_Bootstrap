package com.thinking.machines.school.beans;
public class MessageBean implements java.io.Serializable
{
private boolean success=false;
private String message="";
public MessageBean()
{
this.message="";
}
public MessageBean(String message,boolean success)
{
this.message=message;
this.success=success;
}
public void setMessage(String message)
{
this.message=message;
}
public String getMessage()
{
return this.message;
}
public void setSuccess(boolean success)
{
this.success=success;
}
public boolean getSuccess()
{
return this.success;
}
}
