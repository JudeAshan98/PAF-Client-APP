$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateDoctorForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	$("#formApp").submit();
});

// UPDATE===========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#AppointmentIDsave")
					.val(
							$(this).closest("tr").find(
									'#hidAppointmentIDUpdate').val());
			$("#date").val($(this).closest("tr").find('td:eq(0)').text());
			$("#time").val($(this).closest("tr").find('td:eq(1)').text());
			$("#patientID").val($(this).closest("tr").find('td:eq(2)').text());
			$("#doc").val($(this).closest("tr").find('td:eq(3)').text());
			$("#appointmentStatus").val(
					$(this).closest("tr").find('td:eq(4)').text());
			$("#paymentID").val($(this).closest("tr").find('td:eq(5)').text());
		});

// CLIENTMODEL=========================================================================
function validateDoctorForm() {
	// Doctor name
	if ($("#date").val().trim() == "") {
		return "Insert  Date.";
	}
	// Doctor type
	if ($("#time").val().trim() == "") {
		return "Insert Time.";
	}
	// Contact number-------------------------------
	if ($("#patientID").val().trim() == "") {
		return "Insert Patient ID.";
	}
	// Address
	if ($("#doc").val().trim() == "") {
		return "Insert Doctor ID.";
	}
	// Email
	if ($("#appointmentStatus").val().trim() == "") {
		return "Insert appointment status.";
	}
	// Hospital_ID
	if ($("#paymentID").val().trim() == "") {
		return "Insert payment ID.";
	}

	return true;
}