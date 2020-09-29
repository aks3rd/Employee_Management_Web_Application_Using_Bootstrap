<jsp:useBean id='studentBean' scope='request' class='com.thinking.machines.school.beans.StudentBean'/>
<jsp:setProperty name='studentBean' property='*'/>
<jsp:forward page='/addStudent'/>
