/*
 * Copyright 2016 Esri.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esri.samples.na.service_areas;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.tasks.networkanalysis.ClosestFacilityParameters;

public class ServiceAreasController {

  @FXML private MapView mapView;

  @FXML private ToggleButton btnAddFacility;
  @FXML private ToggleButton btnAddBarrier;
  @FXML private Button btnShowServiceArea;
  @FXML private Button btnReset;

  //*** Needs to be Implemented***
  private ServiceAreaTask task;

  public void initialize() {

    // 
    ArcGISMap map = new ArcGISMap(Basemap.createStreets());
    mapView.setMap(map);

    SpatialReference spatialReference = mapView.getSpatialReference();

    //
    String facilityUrl = "http://static.arcgis.com/images/Symbols/SafetyHealth/Hospital.png";
    PictureMarkerSymbol facilitySymbol = new PictureMarkerSymbol(facilityUrl);
    facilitySymbol.setHeight(30);
    facilitySymbol.setWidth(30);

    //
    SimpleLineSymbol outline = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF000000, 1.0f);
    SimpleFillSymbol firstFill = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, 0xFF00FF00, outline);
    SimpleFillSymbol secondFill = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, 0xFFFFFF00, outline);
    SimpleFillSymbol thirdFill = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, 0xFFFF0000, outline);

    //
    GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
    mapView.getGraphicsOverlays().add(graphicsOverlay);

    // set viewpoint to San Diego
    mapView.setViewpoint(new Viewpoint(37.7749, -122.4194, 40000));

    // 

    mapView.setOnMouseClicked(e -> {
      // check that the primary mouse button was clicked
      if (e.getButton() == MouseButton.PRIMARY && e.isStillSincePress()) {
        if (btnAddFacility.isSelected()) {
          // create a point from where the user clicked
          Point2D point = new Point2D(e.getX(), e.getY());
          // create a map point from a point
          Point mapPoint = mapView.screenToLocation(point);
          //
          Graphic graphic = new Graphic(new Point(mapPoint.getX(), mapPoint.getY(), spatialReference), facilitySymbol);
          //          graphic.getAttributes().put("type", "Facility");
          graphicsOverlay.getGraphics().add(graphic);
        } else if (btnAddBarrier.isSelected()) {
          // create barrier
          //          Graphic graphic = new Graphic(new Point(e.getX(), e.getY(), spatialReference), facilitySymbol);
          //          graphic.getAttributes().put("type", "Barrier");
          //          mapView.getGraphicsOverlays().get(0).getGraphics().add(graphic);
        }
      }
    });
  }

  @FXML
  private void buttonAction() {

  }

  @FXML
  private void showServiceAreas() {

    //*** This needs to change ***

    // call task to find nearest facility
    final ListenableFuture<ClosestFacilityParameters> parameters = task.createDefaultParametersAsync();
    parameters.addDoneListener(() -> {
      try {
        ClosestFacilityParameters facilityParameters = parameters.get();
        //add facilities
        // waiting on methods for facilityParameters to be completed
        //            facilityParameters.

        //add incident (cross)

        // catch all exceptions 
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
  }

  /**
   * Stops and releases all resources used in application.
   */
  void terminate() {

    if (mapView != null) {
      mapView.dispose();
    }
  }
}
