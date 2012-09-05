
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
	
});