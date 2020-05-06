$(document).ready(function() {
	//if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	//}
	$("#alertError").hide();
	// $("#paymentID").hide();

	$("#bottom_hide").hide();
	$("#app_id").hide();
	
	if ($("#role").text().trim()=="admin"){
		$('#formApp input').attr('readonly', 'readonly');
		$('#formApp select').attr("disabled", true); 
		$('#btnSave').hide();
		$('#new_btn').hide();
		$("#patientID").val()=="";

	}
	else if ($("#role").text().trim()=="doctor"){
		$('#formApp input').attr('readonly', 'readonly');
		$('#formApp select').attr("disabled", true); 
		$('#btnSave').hide();
		$('#new_btn').hide();
		$('.btnRemove').hide();
	}
	
});

function hideElemts(){
	$("#AppointmentID").val("");
	$("#formApp")[0].reset();
	$("#bottom_hide").hide();
	$("#app_id").hide();
};

//New Button=======================================
$(document).on("click","#new_btn",function(event){
	
	hideElemts();
});


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateAppForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#AppointmentID").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "AppointmentAPI",
		type : type,
		data : $("#formApp").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

	// If valid------------------------
	// $("#formApp").submit();
});


function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#AppGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#AppointmentID").val("");
	$("#formApp")[0].reset();
	$("#bottom_hide").hide();
	$("#app_id").hide();
}
//
// UPDATE===========================================
$(document).on("click", ".btnUpdate", function(event) {
	// Change the view
	// $("#paymentID").show();
	// $("#appointmentStatus").show();s
	// $("#refundID").show();
	$("#app_id").show();
	$("#bottom_hide").show();
	$("#AppointmentID").prop('readonly', true);
	if ($("#role").text().trim()=="admin"){
		$('#formApp input').attr('readonly', false);
		$('#btnSave').show();
		$("#AppointmentID").prop('readonly', true);
		$('#formApp select').attr("disabled", false); 
	}
	
	// $("#refundID").val(1);
	$("#AppointmentID").val($(this).closest("tr").find('#hidAppointmentID').val());
	// $("#AppointmentID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#date").val($(this).closest("tr").find('td:eq(1)').text());
	$("#time").val($(this).closest("tr").find('td:eq(2)').text());
	$("#hospitalID").val($(this).closest("tr").find('td:eq(3)').text());
	$("#patientID").val($(this).closest("tr").find('td:eq(4)').text());
	$("#doctorID").val($(this).closest("tr").find('td:eq(5)').text());
	$("#appointmentStatus").val($(this).closest("tr").find('td:eq(6)').text());
	$("#paymentID").val($(this).closest("tr").find('td:eq(7)').text());
});

// CLIENTMODEL=========================================================================
function validateAppForm() {
	// Doctor name
	if ($("#date").val().trim() == "") {
		return "Insert  Date.";
	}
	// Doctor type
	if ($("#time").val().trim() == "") {
		return "Insert Time.";
	}
	// Address
	if ($("#doctorID").val().trim() == "") {
		return "Insert Doctor ID.";
	}
	// Hospital
	if ($("#hospitalID").val().trim() == "") {
		return "Insert hospital ID .";
	}
	// Contact number-------------------------------
	if ($("#patientID").val().trim() == "") {
		return "Insert Patient ID.";
	}
	// Hospital_ID
	// if ($("#paymentID").val().trim() == "") {
	// return "Insert payment ID.";
	// }

	return true;
}

// REMOVE==========================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "AppointmentAPI",
		type : "DELETE",
		data : "AppointmentID=" + $(this).data("appointment"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#AppGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}