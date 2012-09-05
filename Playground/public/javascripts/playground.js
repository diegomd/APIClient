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

$("#send").live('click', function(){
	$("#form").submit();
});



var showOnlyFields = function (fieldArray) {
	$("[id$='field']").hide();
	
	for(i=0;i<fieldArray.length;i++){
		$("#"+fieldArray[i]).show();
	}
}

var configureEndPoint = function (endpointUri) {

	$(".endpoint-li").removeClass("active");
	$("#endpoint-field").val(endpointUri);
	$(".input-field").val("");
	
	switch(endpointUri) {
		
		case "MEDIAS":
			$("#li-endpoint-medias").addClass("active");
			showOnlyFields([ "search-field", "first-field", "limit-field", "filter-field", "orderby-field", "sort-field", "recursivechannel-field" ]);
			break;

		case "MEDIAS_COUNT":
			$("#li-endpoint-medias-count").addClass("active"); 
			showOnlyFields([ "first-field" ]);
			break;

		case "CHANNELS":
			$("#li-endpoint-channels").addClass("active");
			showOnlyFields([ "filter-field", "limit-field", "first-field"]);
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

	$("#endpoint-channels").live('click', function(){
		configureEndPoint("CHANNELS");
     });
});