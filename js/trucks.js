var FoodTrucks = Backbone.Collection.extend({
        
  //Specify REST URL
  url: 'http://ec2-54-200-60-161.us-west-2.compute.amazonaws.com:8080/foodtrucks/getinfo',
  
  //Parse the response
    parse: function (response) {
      
      var trucksinfo = response.foodTrucksResponse;
      var trucksdata = jQuery.parseJSON( trucksinfo );

      var parsedTruckData = [];
      $.each(trucksdata, function() {
        var truckObject = {};

        truckObject.latitude = this.latitude;
        truckObject.longitude= this.longitude;
        truckObject.applicant = this.applicant;
        truckObject.address = this.address;
        parsedTruckData.push(truckObject);
      });

      return parsedTruckData;
    },

  initialize: function () {
  } 

});