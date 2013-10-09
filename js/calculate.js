$(document).ready(  function() {
  $( "#display_table" ).click(function(event) {
    
    var value = $('#display_table').attr('value')

    if(value=="Show") {
      $('#display_table').attr('value','Hide');
      show_table();

    } else if (value=="Hide") {
       $('#display_table').attr('value','Show');
       hide_table();
    }
  })
  function show_table() {
    $( "#theTableDiv").append("<table id=\"theTable\">" );
    
    $( "#theTable").append("<tr>" );
       $('tr').last().append("<th></th>");
       for(var j=0; j<=10; j++) {
       $('tr').last().append("<th>");
       $('th').last().append(j);
       $('tr').last().append("</th>");
     }
    $( "#theTable").append("</tr>" );

    for (var i=1;i<=10;i++) { 
      $( "#theTable").append("<tr>" );
      $('tr').last().append("<th>");
      $('th').last().append(i);
      $('tr').last().append("</th>");
      for(var j=0; j<=10; j++) {
       $('tr').last().append("<td>");
       $('td').last().append(i*j);
       $('tr').last().append("</td>");
     }
     $( "#theTable").append("</tr>" );
    }
    $( "#theTableDiv").append("</table>" );
  }

  function hide_table() {
    $( "#theTableDiv").empty();
  }
});