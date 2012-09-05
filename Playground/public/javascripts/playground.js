var	showJavaRequest = function () {
	$("#request_html").hide();
	$("#request_java").show();

	$("#request_java_li").addClass("active");
	$("#request_html_li").removeClass("active");
}

var showHtmlRequest = function () {
	$("#request_java").hide();
	$("#request_html").show();
	
	$("#request_html_li").addClass("active");
	$("#request_java_li").removeClass("active");
}
	
	
$("#request_java_link").live('click', showJavaRequest);

$("#request_html_link").live('click', showHtmlRequest);

$("#btn-get").live('click', function(){
	$("#form").submit();
});

function showElements(ArrayOfElementsId){
	for(i=0;i<ArrayOfElementsId.length;i++){
		$("#"+ArrayOfElementsId[i]).show();
	}	
}

var showOnlyButtons = function (fieldArray) {
	$(".btn").hide();
	showElements(fieldArray);
}

var showOnlyFields = function (fieldArray) {
	$("[id$='field']").hide();
	showElements(fieldArray);
}

var configureEndPoint = function (endpointUri) {

	$(".endpoint-li").removeClass("active");
	$("#endpoint-field").val(endpointUri);
	$(".input-field").val("");
	
	switch(endpointUri) {
		
		case "MEDIAS":
			$("#li-endpoint-medias").addClass("active");
			showOnlyFields([ "search-field", "first-field", "limit-field", "filter-field", "orderby-field", "sort-field", "recursivechannel-field"]);
			showOnlyButtons([ "btn-get"]);
			break;

		case "MEDIAS_COUNT":
			$("#li-endpoint-medias-count").addClass("active"); 
			showOnlyFields([]);
			showOnlyButtons([ "btn-get"]);
			break;

		case "MEDIAS_RATINGS":
			$("#li-endpoint-medias-ratings").addClass("active"); 
			showOnlyFields([ "first-field", "limit-field", "lastmodified-field", "filter-field"]);
			showOnlyButtons([ "btn-get"]);
			
			break;			
			
		case "MEDIAS_VIEWS":
			$("#li-endpoint-medias-views").addClass("active"); 
			showOnlyFields([ "first-field", "limit-field", "lastmodified-field"]);
			showOnlyButtons([ "btn-get"]);
			break;
			
		case "MEDIAS_MEDIAID":
			$("#li-endpoint-medias-mediaid").addClass("active"); 
			showOnlyFields([ "filter-field", "mediaid-field"]);
			showOnlyButtons([ "btn-get", "btn-put", "btn-delete" ]);
			break;

		case "MEDIAS_URLS_MEDIAFILEID":
			$("#li-endpoint-medias-urls-mediafileid").addClass("active");
			showOnlyFields([ "mediafileid-field"]);
			showOnlyButtons([ "btn-get"]);
			break;

		case "MEDIAS_VIEWS_MEDIAFILEID":
			$("#li-endpoint-medias-views-mediafileid").addClass("active");
			showOnlyFields([ "mediafileid-field", "sessionid-field", "quarter-field" ]);
			showOnlyButtons([ "btn-post"]);
			break;
			
		case "MEDIAS_MEDIAID_RATING":
			$("#li-endpoint-medias-mediaid-rating").addClass("active");
			showOnlyFields([ "mediaid-field", "filter-field"]);
			showOnlyButtons([ "btn-get", "btn-post" ]);
			break;

		case "MEDIAS_MEDIAID_RELATED":
			$("#li-endpoint-medias-mediaid-related").addClass("active");
			showOnlyFields([ "mediaid-field", "first-field", "limit-field", "filter-field"]);
			showOnlyButtons([ "btn-get"]);
			break;
			
		case "MEDIAS_MEDIAID_THUMBS":
			$("#li-endpoint-medias-mediaid-thumbs").addClass("active");
			showOnlyFields([ "mediaid-field", "filter-field" ]);
			showOnlyButtons([ "btn-get"]);
			break;

		case "MEDIAS_MEDIAID_VIEWS":
			$("#li-endpoint-medias-mediaid-views").addClass("active");
			showOnlyFields([ "mediaid-field"]);
			showOnlyButtons([ "btn-get"]);
			break;

		case "MEDIAS_URLS_MEDIAID_OUTPUTNAME":
			$("#li-endpoint-medias-urls-mediaid-outputname").addClass("active");
			showOnlyFields([ "mediaid-field", "outputname-field" ]);
			showOnlyButtons([ "btn-get"]);
			break;

		case "MEDIAS_MEDIAID_VIEWS_OUTPUTNAME":
			$("#li-endpoint-medias-mediaid-views-outputname").addClass("active");
			showOnlyFields([ "mediaid-field", "outputname-field", "sessionid-field", "quarter-field" ]);
			showOnlyButtons([ "btn-get"]);
			break;	
			
		case "CHANNELS":
			$("#li-endpoint-channels").addClass("active");
			showOnlyFields([ "filter-field", "limit-field", "first-field"]);
			showOnlyButtons([ "btn-get"]);
			break;
			
		case "CHANNELS_COUNT":
			$("#li-endpoint-channels-count").addClass("active");
			showOnlyFields([ ]);
			showOnlyButtons([ "btn-get"]);
			break;			
			
		case "CHANNELS_CHANNELID":
			$("#li-endpoint-channels-channelid").addClass("active");
			showOnlyFields([ "filter-field", "channelid-field"]);
			showOnlyButtons([ "btn-get", "btn-put" ]);
			break;

		case "OUTPUTS":
			$("#li-endpoint-outputs").addClass("active");
			showOnlyFields([ ]);
			showOnlyButtons([ "btn-get" ]);
			break;

		case "OUTPUTS_COUNT":
			$("#li-endpoint-outputs-count").addClass("active");
			showOnlyFields([ ]);
			showOnlyButtons([ "btn-get" ]);
			break;

		case "REPORT_DETAIL_TRAFFIC":
			$("#li-endpoint-report-detail-traffic").addClass("active");
			showOnlyFields([ "begin-field", "end-field" ]);
			showOnlyButtons([ "btn-get" ]);
			break;

		case "REPORT_DETAIL_MEDIAS":
			$("#li-endpoint-report-detail-medias").addClass("active");
			showOnlyFields([ "date-field"]);
			showOnlyButtons([ "btn-get" ]);
			break;

		case "REPORT_DETAIL_VIEWS":
			$("#li-endpoint-report-detail-views").addClass("active");
			showOnlyFields([ "begin-field", "end-field" ]);
			showOnlyButtons([ "btn-get" ]);
			break;

		case "REPORT_DETAIL_STORAGE":
			$("#li-endpoint-report-detail-storage").addClass("active");
			showOnlyFields([ "date-field" ]);
			showOnlyButtons([ "btn-get" ]);
			break;			
			
		default:
			configureEndPoint("MEDIAS");

	}

}

var clickAdvancedOptions = function() {
	if( $('#advanced-options:visible').length ) {
		$('#advanced-options').hide();
		$('#advanced-options-label').removeClass('icon-upload').addClass('icon-download');
		
	} else {
		$('#advanced-options').show();
		$('#advanced-options-label').removeClass('icon-download').addClass('icon-upload');
	}
}

$(document).ready(function() {

	// Advanced options cursor
	$('#advanced-options-label').css('cursor', 'pointer');
	$('#main-content').undelegate('#advanced-options-label', 'click').delegate('#advanced-options-label', 'click', clickAdvancedOptions);
	
	// Abas Xml/Java
	showHtmlRequest();
	// Sintaxe da linguegm (Xml/java) 
	SyntaxHighlighter.all();
	
	// Endpoint input
	configureEndPoint("@playgroundRequest.endpoint");
	
	/* Events */
	$("#endpoint-medias").live('click', function(){
		configureEndPoint("MEDIAS");
    });
	
	$("#endpoint-medias-count").live('click', function(){
		configureEndPoint("MEDIAS_COUNT");
     });
	
	$("#endpoint-medias-ratings").live('click', function(){
		configureEndPoint("MEDIAS_RATINGS");
     });
	
	$("#endpoint-medias-views").live('click', function(){
		configureEndPoint("MEDIAS_VIEWS");
     });
	
	$("#endpoint-medias-mediaid").live('click', function(){
		configureEndPoint("MEDIAS_MEDIAID");
     });
	
	$("#endpoint-medias-urls-mediafileid").live('click', function(){
		configureEndPoint("MEDIAS_URLS_MEDIAFILEID");
     });

	$("#endpoint-medias-views-mediafileid").live('click', function(){
		configureEndPoint("MEDIAS_VIEWS_MEDIAFILEID");
     });
	
	$("#endpoint-medias-mediaid-rating").live('click', function(){
		configureEndPoint("MEDIAS_MEDIAID_RATING");
     });

	$("#endpoint-medias-mediaid-related").live('click', function(){
		configureEndPoint("MEDIAS_MEDIAID_RELATED");
     });
	
	$("#endpoint-medias-mediaid-thumbs").live('click', function(){
		configureEndPoint("MEDIAS_MEDIAID_THUMBS");
     });	

	$("#endpoint-medias-mediaid-views").live('click', function(){
		configureEndPoint("MEDIAS_MEDIAID_VIEWS");
     });	

	$("#endpoint-medias-urls-mediaid-outputname").live('click', function(){
		configureEndPoint("MEDIAS_URLS_MEDIAID_OUTPUTNAME");
     });
	
	$("#endpoint-medias-mediaid-views-outputname").live('click', function(){
		configureEndPoint("MEDIAS_MEDIAID_VIEWS_OUTPUTNAME");
     });		

	
	$("#endpoint-channels").live('click', function(){
		configureEndPoint("CHANNELS");
     });
	
	$("#endpoint-channels-count").live('click', function(){
		configureEndPoint("CHANNELS_COUNT");
     });
	
	$("#endpoint-channels-channelid").live('click', function(){
		configureEndPoint("CHANNELS_CHANNELID");
     });
	
	$("#endpoint-outputs").live('click', function(){
		configureEndPoint("OUTPUTS");
     });	
	
	$("#endpoint-outputs-count").live('click', function(){
		configureEndPoint("OUTPUTS_COUNT");
     });	

	$("#endpoint-report-detail-traffic").live('click', function(){
		configureEndPoint("REPORT_DETAIL_TRAFFIC");
     });
	
	$("#endpoint-report-detail-medias").live('click', function(){
		configureEndPoint("REPORT_DETAIL_MEDIAS");
     });
	
	$("#endpoint-report-detail-views").live('click', function(){
		configureEndPoint("REPORT_DETAIL_VIEWS");
     });
	
	$("#endpoint-report-detail-storage").live('click', function(){
		configureEndPoint("REPORT_DETAIL_STORAGE");
     });
	
	
});



