/*
 * Common file to load DataTables
 * 
 * This file has the capability to load dataTables using 2 methods:
 * 1) Load dataTables on page load (Recommended for tables with LESS/LIMITED number of rows)
 * 2) Load dataTables after page load Using Ajax (Recommended for tables with LARGE/DYNAMIC number of rows)
 *
*/	

//Utility function to serialize a form's contents as a JSON object
$.fn.serializeObject = function() {
    var o = Object.create(null),
        elementMapper = function(element) {
            element.name = $.camelCase(element.name);
            return element;
        },
        appendToResult = function(i, element) {
            var node = o[element.name];

            if ('undefined' != typeof node && node !== null) {
                o[element.name] = node.push ? node.push(element.value) : [node, element.value];
            } else {
                o[element.name] = element.value;
            }
        };

    $.each($.map(this.serializeArray(), elementMapper), appendToResult);
    
    return o;
};

//Custom function to pass Additional input parameters to generate the data table output.
function passAdditionalFormParams(aoData)
{
    var advancedFormQueryStringJsonObject = $('#additionalFieldsForm').serializeObject();
    for(var elem  in advancedFormQueryStringJsonObject) {
        aoData.push( { "name": elem, "value": advancedFormQueryStringJsonObject[elem] } );
    }
    
    return aoData;
}

//Custom function to load a data table using Ajax
function loadDataTableUsingAjax(
	htmlTableId, 
	ajaxSourceUrl, 
	itemsPerPage, 
    searchBoxPlaceHolderText,
    resetPageUrl,
    loadingText,
    sortingDefinition,
    defaultSorting
) {    
	dataTableObject = $('#' + htmlTableId).dataTable({
    	"sPaginationType": "full_numbers",
    	"sDom": '<"top"lfip>rt<"bottom"ip<"clear">',
    	"aLengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
    	"aoColumns": sortingDefinition,
    	"aaSorting": defaultSorting,
    	"oLanguage": {
            "sProcessing": loadingText
        },
        "bProcessing": true,
        "bServerSide": true,
        "iDisplayLength": itemsPerPage,
        "sServerMethod": "POST",
        "sAjaxSource": ajaxSourceUrl,
        "fnServerData" : function (sSource, aoData, fnCallback) {
        	//Pass additional input parameters to the server
        	aoData = passAdditionalFormParams(aoData);
            $.ajax({
            	"dataType": 'json',
                "type": "POST",
                "url": sSource, 
                "data": aoData, 
                "success": fnCallback
            });
        },
        "fnDrawCallback": function( oSettings ) {
        	if ( typeof dataTableOnCompleteCallBack == 'function' ) { 
        		dataTableOnCompleteCallBack(); 
        	}
       }
    });    
    
    $('#' + htmlTableId + "_filter input").attr('placeholder', searchBoxPlaceHolderText);    
    $('#' + htmlTableId + "_filter").append('<a href="'+ resetPageUrl +'" class="reset-link">Reset Filter</a>');
    $('#' + htmlTableId + "_filter input").addClass('searchBox');
}    

