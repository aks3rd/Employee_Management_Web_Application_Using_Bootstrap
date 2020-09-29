<jsp:include page='/MasterPageTopSection.jsp' />
<jsp:useBean id='messageBean' scope='request' class='com.thinking.machines.school.beans.MessageBean' />
<jsp:useBean id='errorBean' scope='request' class='com.thinking.machines.school.beans.ErrorBean' />
<jsp:useBean id='studentBean' scope='request' class='com.thinking.machines.school.beans.StudentBean' />
<jsp:include page='/StudentJS.jsp' />

<script>
function errorMessageShow()
{
//alert("Chala");
	var x=<jsp:getProperty name='messageBean' property='success'/>;
	var y;
	if(x==true)
	{
//alert("true");
		var errorSection=document.getElementById('deleteMessageSection');
		errorSection.style.display='block';
		var messageBean=document.getElementById("messageBeanSection");
		messageBean.innerHTML="<jsp:getProperty name='messageBean' property='message'/>";
	}
	if(x==false)
	{
//alert("false");
		var errorSection=document.getElementById('deleteMessageSection');
		errorSection.style.display='none';
		var messageBean=document.getElementById("messageBeanSection");
		messageBean.innerHTML="<jsp:getProperty name='messageBean' property='message'/>";
	}
	<jsp:setProperty name='messageBean' property='message' value=" " />
	<jsp:setProperty name='messageBean' property='success' value='false' />
}

function errorBeanModalShow()
{
	var x=<jsp:getProperty name='errorBean' property='success'/>;
	var y;
	if(x==true)
	{
//alert("true");
		var errorSection=document.getElementById('errorMessageSection');
		errorSection.style.display='block';
		var errorBean=document.getElementById("errorBeanSection");
		errorBean.innerHTML="<jsp:getProperty name='errorBean' property='message'/>";

		var rollNumber=<jsp:getProperty name='studentBean' property='rollNumber'/>;
		//alert(rollNumber);
		var iii=0;
		while(iii<viewModel.students.length)
		{
			if(viewModel.students[iii].rollNumber==rollNumber)
			{
				var tr=document.getElementById("ixix"+iii);
				selectRow(iii,tr);
				tr.scrollIntoView(true);
			}
			iii++;
		}
	}
	if(x==false)
	{
//alert("False");
		var type="<jsp:getProperty name='errorBean' property='type'/>";
		if(type.toUpperCase()=="add".toUpperCase())
		{
			//alert("add wala");
			var s=new Student();
			s.rollNumber=<jsp:getProperty name='studentBean' property='rollNumber'/>;
			s.name="<jsp:getProperty name='studentBean' property='name'/>";
			s.gender="<jsp:getProperty name='studentBean' property='gender'/>";
			s.dateOfBirth="<jsp:getProperty name='studentBean' property='dateOfBirth'/>";
			s.address="<jsp:getProperty name='studentBean' property='address'/>";
			s.indian=<jsp:getProperty name='studentBean' property='indian'/>;
			s.city=<jsp:getProperty name='studentBean' property='cityCode'/>;
			//alert(s.rollNumber+","+s.name+","+s.gender+","+s.address+","+s.indian+","+s.city);
			displayAddErrorForm(s);
			document.getElementById("addFormErrorSection").innerHTML="<jsp:getProperty name='errorBean' property='message'/>";
		}
		if(type.toUpperCase()=="edit".toUpperCase())
		{
			alert("edit wala");
		}
		var errorSection=document.getElementById('errorMessageSection');
		errorSection.style.display='none';
		var errorBean=document.getElementById("errorBeanSection");
		<jsp:setProperty name='errorBean' property='message' value=" " />
		<jsp:setProperty name='errorBean' property='success' value='false' />
		errorBean.innerHTML="<jsp:getProperty name='errorBean' property='message'/>";
	}
}
window.addEventListener('load',errorMessageShow);
window.addEventListener('load',errorBeanModalShow);
</script>

	<div class="container-fluid" id="tableGrid" style='padding:0px;display:block'>
		<nav class="navbar navbar-light" style="background-color: #e3f2fd;width:100%;">
			<!-- Navbar content -->
			<a class="navbar-brand">Students</a>
			<form class="form-inline">
				<input class="form-control mr-sm-2" id="searchBox" type="search"  oninput="searchStudent(this)" placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" onclick="searchStudentByButtonClickedAction()" type="button">Search</button>&nbsp;&nbsp;
				<button class="btn btn-outline-success my-2 my-sm-0" onclick="displayAddSection()" type="button">Add</button>
			</form>
		</nav>
		
		<div class="alert alert-info alert-dismissible fade show" role="alert" id="deleteMessageSection" style="display:none;">
			<strong id="messageBeanSection"> </strong>
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-info alert-dismissible fade show" role="alert" id="errorMessageSection" style="display:none;">
			<strong id="errorBeanSection"> </strong>
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class='gridTableSection' id="tableContent">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">S.No.</th>
						<th scope="col">Roll Number</th>
						<th scope="col">Name</th>
						<th scope="col">Gender</th>
						<th scope="col">DOB</th>
						<th scope="col">City</th>
						<th scope="col">Edit</th>
						<th scope="col">Delete</th>
						<th scope="col">Details</th>
					</tr>
				</thead>
				<tbody id="studentViewGrid">

				</tbody>
			</table>
		</div>
	</div>


<jsp:include page='/StudentDetailsSection.jsp'/>
<jsp:include page='/StudentAddSection.jsp'/>
<jsp:include page='/StudentEditSection.jsp'/>
<jsp:include page='/StudentDeleteSection.jsp'/>

<jsp:include page='/MasterPageBottomSection.jsp'/>
