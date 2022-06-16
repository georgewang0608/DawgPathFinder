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

import React, { Component } from "react";
import EdgeList from "./EdgeList";
import Map from "./Map";
import { UW_LATITUDE_CENTER, UW_LONGITUDE_CENTER } from "./Constants";


// Allows us to write CSS styles inside App.css, any styles will apply to all components inside <App />
import "./App.css";
import {ColoredEdge} from "./ColoredEdge";
import {LatLngExpression} from "leaflet";

interface AppState {
    edgeList : ColoredEdge[]; // List of edges
    position: LatLngExpression; // the cordinate
}

class App extends Component<{}, AppState> { // <- {} means no props.

  constructor(props: any) {
    super(props);
    this.state = {edgeList: [], position: [1, 1]};
  }

  render() {
    return (
      <div>
        <h1 id="app-title">Line Mapper!</h1>
        <div>
            {/* TODO: define props in the Map component and pass them in here */}
            {/* pass the current state for map to render */}
          <Map onChange={(value) => {
              this.setState({edgeList: [], position: value});
              console.log("Map onChange", value);
          }} edgeList={this.state.edgeList} position={this.state.position}  />
        </div>
        <EdgeList
          onChange={(value) => {
            // TODO: Modify this onChange callback to store the edges in the state
              // update the App state according to update of edgelist by user input
              this.setState({edgeList: value, position: this.state.position});
            console.log("EdgeList onChange", value);
          }}
        />
      </div>
    );
  }
}

export default App;
