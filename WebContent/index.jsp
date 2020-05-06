<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.cj.protocol.Resultset"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.mysql.cj.ParseInfo"%>
<%@page import="com.DBconnection" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ page import="com.Appointment"%>
<% Integer userid = (Integer) session.getAttribute("usrID"); %>
				<% String role = (String) session.getAttribute("role"); %>
<%
	Class.forName("com.mysql.jdbc.Driver");
// java.sql.Connection connection = DriverManager.getConnection(
// 		"jdbc:mysql://localhost:3306/pafdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
// 		"root", "");
	DBconnection con = new DBconnection();

	Connection connection = con.connect();	
	
String query = "SELECT hospitalID , hospitalname from hospital ";
String query1 = "SELECT * from doctor";
Statement stmt = connection.createStatement();

ResultSet rs = stmt.executeQuery(query);

Statement stmt1 = connection.createStatement();
ResultSet rs1 = stmt1.executeQuery(query1);

%>
<title>Appointment</title>

<link rel="stylesheet" href="Components/bootstrap.min.css">
<script src="Components/bootstrap.min.js"></script>
<script src="Components/jquery-3.2.1.min.js"></script>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>

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
<body><div style="max-height:20px; background-color:black; with:100%;margin-top:10px"> &nbsp </div>
	<div class="container">
	
		<div class="row">
			<div class="col-8">
				<br />
				<h2 class="m-3">Online Appointment</h2>
				<br />
				
				<p id="role" hidden><%=role %></p>
<%-- 				<input type="hidden" id="userid" name="userid"><%=userid%> --%>
<%-- 				<input type="hidden" id="role" name="role"><%=role%> --%>
				<form id="formApp" name="formApp">
					<div id="app_id">
						<label for="date">Appointment ID:</label> <input type="text"
							id="AppointmentID" name="AppointmentID" value=""
							class="form-control form-control-sm"><br />
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="date">Date:</label> <input id="date" name="date"
								type="date" class="form-control form-control-sm"><br>
						</div>
						<div class="form-group col-md-6">
							<label for="itemName">Time: </label> <input id="time" name="time"
								type="Time" class="form-control form-control-sm"> <br>

						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="itemPrice">Hospital: </label> <select
								class="form-control select2" id="hospitalID" name="hospitalID">
								<%
									while (rs.next()) {
								%>
								<option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
								<%
									}
								%>
							</select> <br /> <br />
						</div>
						<div class="form-group col-md-6">
							<label for="itemDesc">Doctor: </label> <select
								class="form-control select2" name="doctorID" id="doctorID">
								<%
									while (rs1.next()) {
								%>
								<option value="<%=rs1.getString(1)%>"><%=rs1.getString(4)%>  <%=rs1.getString(5)%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<br />
					<div hidden="">
						<label for="patientID">patient ID:</label> <input id="patientID"
							name="patientID" type="text" class="form-control form-control-sm"
							value="<%=userid%>"><br>
					</div>

					<input type="hidden" id="hidAppIDSave" name="hidAppIDSave"
						value="89">
						<div class="form-row" id="bottom_hide">
						<div class="form-group col-md-12">
							<label for="appointmentStatus"></label>Payment ID: <input
								type="text" id="appointmentStatus" name="appointmentStatus"
								value="" class="form-control form-control-sm"><br />
						</div>
						<div class="form-group col-md-12">
							<label for="paymentID"></label>Appointment Status: <input type="text"
								id="paymentID" name="paymentID" value=""
								class="form-control form-control-sm"><br />
						</div>
					</div>
					
					<br/><br/>
					
					<input id="btnSave" name="btnSave" type="button" value="Save*"
						class="btn btn-success"> 
						<input id="new_btn"	name="new_btn" type="button" value="New*" class="btn btn-info">
				</form>
				<br />

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br/>
				<div id="AppGrid">
					<%
						Appointment App = new Appointment();
					out.print(App.GetAllAppoinments());
					%>
				</div>

				<br>
			</div>
		</div>
	</div>
	<script>
		$('.select2').select2();
	</script>
</body>
</html>