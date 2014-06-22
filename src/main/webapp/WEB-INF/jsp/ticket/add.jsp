<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta charset="u8">
<title>Ticket Dashboard</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.css"/>
</head>

<body style="background: #ffffff"> 
	<section class="container">
		<div class="login" style="width:700px;">
			<h1>Add a Ticket<input style="float:right;" type="button" id="dashboard" value="Ticket Dashboard" onclick="javascript:ticketDashboard();"></h1>
			<form:form id="ticketform" modelAttribute="ticket" action="${pageContext.request.contextPath}/ticket/save" method="POST">
					<form:hidden path="createdBy.id" value="${userProfile.id}"/>
					<div class="block">
						<span id="span-block">Title:</span>
						<form:input id="title" path="title" placeholder="Title"/>
					</div>
					<div class="block">
					<span id="span-block">Assigned To:</span>
						<form:hidden id="assignedToId" path="assignedTo.id"/>
						<form:input id="assignedTo" path="assignedTo.email" placeholder="Search User To Assign Ticket" autocomplete="off" onkeyup="javascript:autoComplete(this.value, 'USER', 'assignedTo')"/>
					</div>
					<div class="block">
					<span id="span-block">Customer Email:</span>
						<form:hidden id="reportedById" path="reportedBy.id"/>
						<form:input id="reportedBy" path="reportedBy.email" placeholder="Search Customer Email" autocomplete="off" onkeyup="javascript:autoComplete(this.value, 'CUSTOMER', 'reportedBy')"/>
					</div>
				
				<div id="customer-check" >
					If Customer not found, Please check to enter Customer details
					<input type="checkbox" id="customer" name="customer" />
				</div>
				<div id="customer-info" style="display:none;">
				
				<h3 style="float: left; width: 500px;">Customer Details</h3>
				<div class="block">
					<span id="span-block">FirstName:</span>
					<form:input id="firstName" path="reportedBy.firstName" placeholder="FirstName"/>
				</div>
				<div class="block">
					<span id="span-block">LastName:</span>
					<form:input id="lastName" path="reportedBy.lastName" placeholder="LastName"/>
				</div>
				<div class="block">
					<span id="span-block">Phone:</span>
					<form:input id="phone" path="reportedBy.phone" placeholder="Phone"/>
				</div>
				<div class="block">
					<span id="span-block">Address1:</span>
					<form:input id="address1" path="reportedBy.address1" placeholder="Address1"/>
				</div>
				<div class="block">
					<span id="span-block">Address2:</span>
					<form:input id="address2" path="reportedBy.address2" placeholder="Address2"/>
				</div>
				<div class="block">
					<span id="span-block">City:</span>
					<form:input id="city" path="reportedBy.city" placeholder="City"/>
				</div>
				<div class="block">
					<span id="span-block">State:</span>
					<form:input id="state" path="reportedBy.state" placeholder="State"/>
				</div>
				<div class="block">
					<span id="span-block">Zip:</span>
					<form:input id="zip" path="reportedBy.zip" placeholder="Zip"/>
				</div>
				</div>
				<div class="block"><span id="span-block">Comment:</span>
					<form:textarea style="height:120px; width:275px;" id="comment" path="comment"/>
				</div>
				
				<div class="block" style="width: 500px;padding: 10px;">
					<input type="submit" id="add" value="Save" onclick="javascript:validateForm();">
					<input type="button" id="cancel" value="Cancel" onclick="javascript:ticketDashboard();">
				</div>
			</form:form>
		</div>

	</section>

</body>
<footer>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.maskedinput.js"></script>
	<script>
		function validateForm() {
			$.validator.addMethod("lookup", function (value) {
		        return $('#assignedToId').val();
				}, 'Please select from lookup');
			$("#ticketform").validate({
				rules : {
					title : "required",

					"reportedBy.email" : "required",
					"reportedBy.firstName" : "required",
					"reportedBy.lastName" : "required",
					"reportedBy.phone" : "required",
					"reportedBy.address1" : "required",
					"reportedBy.city" : "required",
					"reportedBy.state" : "required",
					"reportedBy.zip" : "required"
				},
				messages : {
					title : "Title is required",
					
					"reportedBy.email" : "Email is required",
					"reportedBy.firstName" : "First Name is required",
					"reportedBy.lastName" : "Last Name is required",
					"reportedBy.phone" : "Phone is required",
					"reportedBy.address1" : "Address1 is required",
					"reportedBy.city" : "City is required",
					"reportedBy.state" : "State is required",
					"reportedBy.zip" : "Zip is required"
				}
			});

		}
		function ticketDashboard(){
			window.location.href = "${pageContext.request.contextPath}/ticket/dashboard";
		}
		
		$("#phone").mask("999-999-9999");
		$("#zip").mask("99999?-9999");
		
		$('input[type="checkbox"]').change(function() {
		     if(this.checked) {
		    	 $('#customer-info').css('display','block');
		     }
		 });
		
		function autoComplete(searchText, lookupType, divId)
		{
				
			$.getJSON("${pageContext.request.contextPath}/autoComplete", {
				searchText : searchText,
				lookupType : lookupType
			}, function(data) {
				$("#"+divId).autocomplete( {
					open: function() { 
			               $('.ui-menu').width(220);
			            } ,
					source : data,
					minLength : 1,
					select : function(event, ui) {
						setValues(event, ui, lookupType);

					}
				}).focus(function() {
					if ($(this).val().length == 1) {
						$(this).autocomplete("search");
					}
				});
			});
		}
		
		function setValues(event, ui, lookupType){
			if(lookupType == 'USER'){
				$("#assignedTo").val(ui.item.value);
				$("#assignedToId").val(ui.item.id);
			}else if(lookupType == 'CUSTOMER'){
				$('#customer-info').css('display','block');
				$("#reportedBy").val(ui.item.value);
				$("#reportedById").val(ui.item.id);
				$("#firstName").val(ui.item.firstName);
				$("#lastName").val(ui.item.lastName);
				$("#phone").val(ui.item.phone);
				$("#address1").val(ui.item.address1);
				$("#address2").val(ui.item.address2);
				$("#city").val(ui.item.city);
				$("#state").val(ui.item.state);
				$("#zip").val(ui.item.zip);
				$('#customer-check').css('display','none');
			}
		}
	</script>

</footer>
</html>