/*
 * Copyright (C) 2022 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

import { LatLngExpression } from "leaflet";
import React, { Component } from "react";
import { MapContainer, TileLayer } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import MapLine from "./MapLine";
import { UW_LATITUDE_CENTER, UW_LONGITUDE_CENTER } from "./Constants";
import {ColoredEdge} from "./ColoredEdge";

// This defines the location of the map. These are the coordinates of the UW Seattle campus
const position: LatLngExpression = [UW_LATITUDE_CENTER, UW_LONGITUDE_CENTER];

// NOTE: This component is a suggestion for you to use, if you would like to. If
// you don't want to use this component, you're free to delete it or replace it
// with your hw-lines Map

interface MapProps {
  // TODO: Define the props of this component.
    edgeList: ColoredEdge[]; // Array of ColoredEdge to be rendered
}

interface MapState {}

class Map extends Component<MapProps, MapState> {
  render() {
    return (
      <div id="map">
        <MapContainer
          center={position}
          zoom={15}
          scrollWheelZoom={false}
        >
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          {
              // loop through the props updated by App while mapping the key to each line and return the rendered
              // Mapline for display
              this.props.edgeList ? this.props.edgeList.map((edge, index) => {
                  return <MapLine key={index} color={edge.color.toLowerCase()} x1={edge.x1} y1={edge.y1}
                                  x2={edge.x2} y2={edge.y2}/>
              }) : null
          }
        </MapContainer>
      </div>
    );
  }
}

export default Map;
