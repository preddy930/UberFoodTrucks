App={}

App.Map = Backbone.Model.extend({

});
App.map = new App.Map(); 


App.MapView = Backbone.View.extend({

  createMap: function (x,y,zoomLevel) {
    this.map = new Microsoft.Maps.Map(document.getElementById("mapDiv"), {credentials:"AoddhUu-oajCBWiyCSmuC0Zu5m8BsMjScidETLnbQtuFELC-IzEhrzt7TqnKm6g8", zoom: zoomLevel, enableSearchLogo: false,  width: "100%", height: "100%", center: new Microsoft.Maps.Location (x,y)});
  },

  render: function() {

      var that = this;

      $( "#myform" ).submit(function( event ) {
        var address = $( "#address" ).val();
        event.preventDefault();

        var geocoder = new google.maps.Geocoder();

        if (geocoder) {
          geocoder.geocode({ 'address': address }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
              console.log(results[0].geometry.location);
              that.map.setView({ zoom: 17, center: new Microsoft.Maps.Location (results[0].geometry.location.k,results[0].geometry.location.A)});
            }
            else {
              alert("Invalid address");
              console.log("Geocoding failed: " + status);
            }
          });
        }      
    });

  this.createMap(37.7833,-122.4167,13);

  var foodTrucks = new FoodTrucks();
  var that2 = this;

  foodTrucks.fetch({

    success: function(response,xhr) {
      console.log(response);

      var models = foodTrucks.models;
      
      $.each( models, function( index, value ) {
        
        // Define the pushpin location
        var loc = new Microsoft.Maps.Location(value.attributes.latitude, value.attributes.longitude);
        var pin = new Microsoft.Maps.Pushpin(loc); 
            
        var pinInfobox = new Microsoft.Maps.Infobox(loc, 
        {  title: value.attributes.applicant + "<BR>" + value.attributes.address,
           visible: false,
           pushpin: pin,
           height: 50,
           showCloseButton: true
        });
        
        //handlers
        Microsoft.Maps.Events.addHandler(pin, 'click', show);
        Microsoft.Maps.Events.addHandler(that2.map, 'viewchange', hide);

        // Add the pushpin and infobox to the map
        that2.map.entities.push(pin);
        that2.map.entities.push(pinInfobox);

       function show(e)
         {
            pinInfobox.setOptions({ visible:true });
         }          

         function hide(e)
         {
            pinInfobox.setOptions({ visible: false });
         }
      });
    },
    error: function (errorResponse) {
      console.log(errorResponse)
    }
  });

    this.$el.html(this.map);
  }
});