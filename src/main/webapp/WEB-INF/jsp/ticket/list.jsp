<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta charset="u8">
<title>Ticket Dashboard</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.css"/>
</head>

<body>
<body>
	<section class="container">
		<div class="list">
		<div class="ticket-header">
		<h1>Tickets Dashboard
				<div class="float-div">
					<input type="button" id="login" value="ADD TICKET" onclick="javascript:addTicket();" />
				</div>
		</h1>

		</div>
		
			<div class="admin-table-data clr">
				<div class="data-table-block">
					<div class="admin-search">
						<table width="100%" cellpadding="0" cellspacing="0"
							id="ticketList">
							<thead>
								<tr>
									<th>Ticket No.</th>
									<th>Reported By</th>
									<th>Created By</th>
									<th>Assigned To</th>
									<th>Status</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</section>

</body>
<footer>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.maskedinput.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.load.js"></script>
<script type="text/javascript">
	var htmlTableId = 'ticketList';
	var ajaxSourceUrl = "${pageContext.request.contextPath}/ticket/dashboard/load";
	var itemsPerPage = 10;
	var searchBoxPlaceHolderText = "Reported By, Created By, Assigned To";
	var resetPageUrl = "${pageContext.request.contextPath}/ticket/dashboard";
	var loadingText = "Loading Tickets...";
	var sortingDefinition = [ {
		"aTargets" : [ 0 ],
		"bSortable" : true
	}, {
		"aTargets" : [ 1 ],
		"bSortable" : true
	}, {
		"aTargets" : [ 2 ],
		"bSortable" : true
	}, {
		"aTargets" : [ 3 ],
		"bSortable" : true
	}, {
		"aTargets" : [ 4 ],
		"bSortable" : true
	}, {
		"aTargets" : [ 5 ],
		"bSortable" : false
	} ];
	var defaultSorting = [ [ 0, 'asc' ] ];
	$(document).ready(function() {

		loadDataTableUsingAjax(htmlTableId, ajaxSourceUrl, itemsPerPage, searchBoxPlaceHolderText, 
				resetPageUrl, loadingText, sortingDefinition, defaultSorting);
			});
	
	function addTicket(){
		window.location.href = "${pageContext.request.contextPath}/ticket/add";
	}
</script>

</footer>
</html>