<%@page import="com.mysql.cj.ParseInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ page import="com.AppointmentService" %>
	<%@ page import="model.Appointment" %>
	
	<% 
		//Save---------------------------------
		if (request.getParameter("date") != null) {
			Appointment App = new Appointment();
			String stsMsg = "";
			//Insert------------------------------
			if (request.getParameter("AppointmentIDsave") == "") {
				stsMsg = App.insertAppoinment(request.getParameter("date"),
						request.getParameter("time"), 
						request.getParameter("hospitalID"), 
						request.getParameter("patientID"), 
						request.getParameter("doctorID"), 
						request.getParameter("paymentID")); 
						
				
			} else//Update----------------------
			{
				stsMsg = App.updateAppoinment(request.getParameter("AppointmentIDsave"),
								request.getParameter("date"),
								request.getParameter("time"), 
								request.getParameter("hospitalID"),
								request.getParameter("patientID"),
								request.getParameter(("refundID")),
								request.getParameter("doctorID"), 
								request.getParameter("appointmentStatus"),
								request.getParameter("paymentID")); 
								
			}
			session.setAttribute("statusMsg", stsMsg);
		}
		//Delete-------------------------------------------
		if (request.getParameter("hidAppointmentIDDelete") != null) {
			Appointment App = new Appointment();
			String
			stsMsg = App.DeleteAppoinment(request.getParameter("hidAppointmentIDDelete"));
			session.setAttribute("statusMsg", stsMsg);
		}
	%>
<title>Appointment</title>

<link rel="stylesheet" href="Components/bootstrap.min.css">
<script src="Components/bootstrap.min.js"></script>
<script src="Components/jquery-3.2.1.min.js"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>

<script src="Components/app.js">
</script>
  <style>
  /* Style the input field */
  #myInput {
    padding: 20px;
    margin-top: -6px;
    border: 0;
    border-radius: 0;
    background: #f1f1f1;
  }
  </style>
</head>
<body>
<body>
	<div class="container">
		<div class="row">
			<div class="col-8">
			<br/>
				<h2 class="m-3">Online Appointment</h2>
				<br/>
				<form id="formApp" name="formApp" method="post" action="index.jsp">
					<label for="date">Date:</label> 	
					<input id="date" name="date" type="date" class="form-control form-control-sm"><br> 
					<label for="itemName">Time: </label> 
					<input id="time" name="time" type="Time" class="form-control form-control-sm"> <br> 
					<label for="itemPrice">Hospital: </label>
					<select class="form-control select2" >
			           <option>Select</option> 
			           <option>Car</option> 
			           <option>1</option> 
			           <option>2</option> 
			           <option>3</option> 
			           <option>4</option> 
			        </select>
			        <br/> <br/>
					<label for="itemDesc">Doctor: </label>
					<select class="form-control select2" name="doc" id="doc">
			           <option>Select</option> 
			          <option value="1">test1</option>
			           <option value="${user.id}">Bike</option> 
			           <option>1</option> 
			           <option>2</option> 
			           <option>3</option> 
			        </select> <br>  <br> 
			        	<input type="hidden"id="hidItemIDSave" name="hidItemIDSave" value="">
					<input	id="btnSave" name="btnSave" type="button" value="Save"	class="btn btn-primary"> 
				
				</form>
				<br/>
				<div id="alertSuccess" class="alert alert-success">
		<%
		out.print(session.getAttribute("statusMsg"));
		%>
		</div>
	
	
	

	<% 
	     Appointment App=new Appointment();
	     out.print(App.GetAllAppoinments());
	     %>
	     
	     
	     <br><br>
				<br>
			</div>
		</div>
		<br>
	</div>
	<script>
    $('.select2').select2();
</script>
</body>
</html>