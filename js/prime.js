$(document).ready(function() {
    $('#theForm').submit(function(event) {
        event.preventDefault(); 
        var ajaxURL = "http://localhost:8080/prime/check";

        $.ajax( {
            type: "GET",
            url: ajaxURL,
            data: { 
                number: $("#number").val()
            },
            success: function(data){
                alert("Result: " + data);
            },
            error: function(){
                alert("Error detected");
            }
        } );

        return false;
    });

});