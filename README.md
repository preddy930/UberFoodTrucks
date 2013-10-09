UberFoodTrucks
==============

A simple little webapp that displays all the roaming food vehicles in San Francisco with Bing Maps.

URL: http://ec2-54-200-60-161.us-west-2.compute.amazonaws.com:8080/index.html

Technical specifications:
<ul>
<li>Java + Spring - Server
<li>EhCache 
<li>Backbone.js - Frontend
</ul>

Design Decisions
============

Server:<BR>
The server backend consists of a Java+Spring framework, and uses EhCache as a persistence mechanism rather than a full-fledged database.
I made this decisions, because I felt a DB would be over-kill, unless the goal was to track what users were actually 
looking, or more in-depth information about each of the food trucks.

Currently the cache is set to refresh itself every 24 hours with fresh data via the SF Data url. <BR>
http://ec2-54-200-60-161.us-west-2.compute.amazonaws.com:8080/foodtrucks/populate, is the URL that can manually trigger
the server to refresh the in-memory cache, which stores the raw JSON response from the SF Data api.

Client:<BR>
The client is composed of a JQuery + Backbone.js architecture. In order to retrieve the food truck info from the server,<BR>the following URL
accessed.<BR>
URL: http://ec2-54-200-60-161.us-west-2.compute.amazonaws.com:8080/foodtrucks/getinfo

Bing Maps provides a responsive and versatile presentation layer, and in order to geocode an address, I use Google Geocoding client.
There is a search bar, located in the top middle of the map that allows users to enter an address which is then geocoded, and the mop
will then focus on that location.

Alternate Approaches
============
Since we were dealing with a small dataset of information, I thought it be sufficient to pinpoint all the food trucks ahead of time,
and then allow the user to then focus on a particular area of San Francisco. This model is also used on the TESLA website, when showing 
the locations of all the superchargers in the United States.

However, if we were to expand this for a larger dataset, that say covered the entire state of CA, it would be more prudent to have the server
parse the JSON response, and store the serialized objects in the cache.

This is also my first foray into Bacbone.js, so I'm sure there are some convetions that I might've missed.

Known Defects
============
There is a small issue in which the search bar does not render in the top middle of the page when using Firefox, but works normally in Chrome.
