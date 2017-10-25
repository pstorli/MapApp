package com.sd.mapapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esri.arcgisruntime.geometry.PolygonBuilder;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
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

        createHome ();

        // add graphics overlay to MapView.
        GraphicsOverlay graphicsOverlay = addGraphicsOverlay(mapView);

        //add an icon for my home.
        addHomeIcon (graphicsOverlay);

    }

    private GraphicsOverlay addGraphicsOverlay(MapView mapView) {
        //create the graphics overlay
       GraphicsOverlay graphicsOverlay = new GraphicsOverlay();

        //add the overlay to the map view
        mapView.getGraphicsOverlays().add(graphicsOverlay);
        return graphicsOverlay;
    }

    private void createHome ()
    {
        mapView = (MapView) findViewById(R.id.mapView);

        // 18529 CHEMAWA LANE, SILVERTON, OR 97381 LAT/LONG
        ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 15.169193, 16.333479, 2); //, 45.003911, -122.68582600000002, 15);
        mapView.setMap(map);
    }

    private void addHomeIcon (GraphicsOverlay graphicOverlay) {
        //define the home locations long/lat
        //Point homeLoc = new Point(40e5, 40e5, SpatialReferences.getWebMercator()); //-122.685828, 45.003912, SpatialReferences.getWebMercator());

        //SimpleMarkerSymbol homeMarker = new SimpleMarkerSymbol (SimpleMarkerSymbol.Style.SQUARE, Color.GREEN, 100);

        //create graphics
        //Graphic homeGraphic = new Graphic(homeLoc, homeMarker);

        //add the graphics to the graphics overlay
        //graphicOverlay.getGraphics().add(homeGraphic);

        //

        //polygon graphic
        PolygonBuilder polygonGeometry = new PolygonBuilder(SpatialReferences.getWebMercator());
        polygonGeometry.addPoint(-20e5, 20e5);
        polygonGeometry.addPoint(20e5, 20e5);
        polygonGeometry.addPoint(20e5, -20e5);
        polygonGeometry.addPoint(-20e5, -20e5);

        // solid yellow polygon symbol
        SimpleFillSymbol polygonSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.YELLOW, null);

        // create graphic for polygon
        Graphic polygonGraphic = new Graphic(polygonGeometry.toGeometry());

        // create graphic overlay for polygon
        GraphicsOverlay polygonGraphicOverlay = new GraphicsOverlay();

        // create simple renderer
        SimpleRenderer polygonRenderer = new SimpleRenderer(polygonSymbol);

        // add graphic to overlay
        polygonGraphicOverlay.setRenderer(polygonRenderer);

        // add graphic to overlay
        polygonGraphicOverlay.getGraphics().add(polygonGraphic);

        // add graphics overlay to MapView
        mapView.getGraphicsOverlays().add(polygonGraphicOverlay);

    }

}
