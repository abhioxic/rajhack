$(document).ready(function(){
	$('#msgsend').click(function(){
		var m = $('#msg').val();
		var datatosend = { 'type': 'sendnotification','msg': m};
		console.log(datatosend);
		$.ajax({
            url: "http://localhost:7824",
            dataType: "json",
            data: JSON.stringify(datatosend, null, '\t'),
            contentType: 'application/json;charset=UTF-8',
            type:'POST',
            success: function (data) {
            	console.log(data);
            },
	        error: function(response) {
	            console.log(response);
	        },
        });
	})
});