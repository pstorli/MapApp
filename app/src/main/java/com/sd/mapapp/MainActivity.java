package com.sd.mapapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PolygonBuilder;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleRenderer;

public class MainActivity extends AppCompatActivity
{
    private MapView mapView;

    @Override
    protected void onPause()
    {
        mapView.pause();
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mapView.resume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inflate MapView from layout
        mapView = (MapView) findViewById(R.id.mapView);

        createHome ();

        // add graphics overlay to MapView.
        GraphicsOverlay graphicsOverlay = addGraphicsOverlay();

        //add an icon for my home.
        addHomeIcon (graphicsOverlay);

    }

    private void createHome ()
    {
        // create a map with the imagery basemap
        ArcGISMap map = new ArcGISMap(Basemap.createImagery());

        // create an initial viewpoint with a point and scale
        Point point = new Point(-226773, 6550477, SpatialReferences.getWebMercator());  //, 45.003911, -122.68582600000002, 15); //
        Viewpoint vp = new Viewpoint(point, 7500);

        // set initial map extent
        map.setInitialViewpoint(vp);

        // set the map to be displayed in the mapview
        mapView.setMap(map);

    }

    private GraphicsOverlay addGraphicsOverlay()
    {
        // create a new graphics overlay and add it to the mapview
        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        return graphicsOverlay;
    }

    private void addHomeIcon (GraphicsOverlay graphicOverlay)
    {
        //[DocRef: Name=Point graphic with symbol, Category=Fundamentals, Topic=Symbols and Renderers]
        //create a simple marker symbol
        SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, Color.GREEN, 12); //size 12, style of circle

        //add a new graphic with a new point geometry
        Point graphicPoint = new Point(-226773, 6550477, SpatialReferences.getWebMercator());
        Graphic graphic = new Graphic(graphicPoint, symbol);
        graphicOverlay.getGraphics().add(graphic);
        //[DocRef: END]
    }
}
