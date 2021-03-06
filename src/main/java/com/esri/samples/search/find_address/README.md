<h1>Find Address</h1>

<p>Demonstrates how to perform geocoding and reverse geocoding with offline data.</p>

<p><img src="FindAddress.png"/></p>

<h2>How to use the sample</h2>

<p>For simplicity, the sample comes loaded with a set of addresses. You can select any address to perform geocoding and show it's location on map. To perform reverse geocoding in real-time, click on any location on the ArcGISMap to provide it's address.</p>

<h2>How it works</h2>

<p>To perform geocoding with offline resources:</p>

<ol>
    <li>Create an <code>ArcGISMap</code>'s using a <code>Basemap</code>.
        <ul><li>basemap is created using a <code>TileCache</code>, which represent our offline resource</li></ul></li>
    <li>Add the map to the <code>MapView</code>, <code>MapView.setMap()</code>. </li>
    <li>Create a <code>LocatorTask</code> using a URI to the offline locator file and define the <code>ReverseGeocodeParameters</code>/<code>GeocodeParameters</code> for  the LocatorTask.</li>
    <li>To geocode an address, set the geocode parameters and use <code>LocatorTask.geocodeAsync(geocodeParameters)</code>.</li>
    <li>To reverse geocode a location, get the <code>Point</code> location on the map view and use <code>LocatorTask.reverseGeocodeAsync(Point)</code>.</li>
    <li>Show the retrieved results by creating a <code>PictureMarkerSymbol</code> with attributes from the result and add that symbol to a <code>Graphic</code>  in the <code>GraphicsOverlay</code>.</li>
</ol>

<h2>Features</h2>

<ul>
    <li>ArcGISMap</li>
    <li>ArcGISTiledLayer</li>
    <li>Callout</li>
    <li>MapView</li>
    <li>LocatorTask </li>
    <li>GeocodeParameters</li>
    <li>GeocodeResult</li>
    <li>Graphic</li>
    <li>GraphicsOverlay</li>
    <li>Point</li>
    <li>PictureMarkerSymbol</li>
    <li>ReverseGeocodeParameters</li>
    <li>TileCache</li>
</ul>


