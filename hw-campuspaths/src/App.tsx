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

import React, {Component} from 'react';

// Allows us to write CSS styles inside App.css, any styles will apply to all components inside <App />
import "./App.css";
import {ColoredEdge} from "./ColoredEdge";
import EdgeList from "./EdgeList";
import Map from "./Map";

interface AppState {
    edgeList : ColoredEdge[]; // List of edges
}

class App extends Component<{}, AppState> {

    constructor(props: any) {
        super(props);
        this.state = {edgeList: []};
    }

    render() {
        return (
            <div>
                <h1 id="app-title">Dawg Path Finder!</h1>
                <div>
                    {/* pass the current state for map to render */}
                    <Map edgeList={this.state.edgeList}/>
                </div>
                <EdgeList
                    onChange={(value) => {
                        // update the App state according to update of edgelist by user input
                        this.setState({edgeList: value});
                    }}
                />
            </div>
        );
    }

}

export default App;
