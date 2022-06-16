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
import {ColoredEdge} from "./ColoredEdge";
import {buildQueries} from "@testing-library/react";
import {Browser} from "leaflet";

interface EdgeListProps {
    onChange(edges: ColoredEdge[]): void;  // called when a new edge list is ready
}

interface EdgeListState {
    start: string; // short name for the selected start building
    end: string; // short name for the selected end building
    buildings: any; // the on campus building list
    edges: ColoredEdge[]; // the edges of short past drawn
}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 */
class EdgeList extends Component<EdgeListProps, EdgeListState> {
    constructor(props: any) {
        super(props);
        this.state = {
            start: 'PAR',
            end: 'PAR',
            buildings: {},
            edges: []
        };
        this.initialize().then((res) => {this.setState({buildings: res})});
        // this.initializeBuildings()
        console.log(this.state);
    }

    render() {
        return (
            <div id="edge-list">
                <select onChange={this.startBuildingSelected} value={this.state.start}>
                    {Object.keys(this.state.buildings).map((key) => {
                    return <option key={key} value={key}>{this.state.buildings[key]}</option>})}
                </select>
                <select onChange={this.endBuildingSelected} value={this.state.end}>
                    {Object.keys(this.state.buildings).map((key) => {
                        return <option key={key} value={key}>{this.state.buildings[key]}</option>})}
                </select>
                <button onClick={this.onDraw}>Draw</button>
                <button onClick={this.onClear}>Clear</button>
            </div>
        );
    }

    /** fetch the building data from the server*/
    initialize = async () => {
        return (await fetch("http://localhost:4567/building")).json();
    }

    /** update the string input to the edges*/
    onDraw = () => {
        let edgeList: ColoredEdge[] = [];
        let shortest : any;
        this.path().then((resp) => {
                    shortest = resp;
            for (let i = 0; i < shortest.length; i++) {
                edgeList.push({x1: Number(shortest[i].StartX), y1: Number(shortest[i].StartY),
                    x2: Number(shortest[i].EndX), y2: Number(shortest[i].EndY), color: "red"});
            }
            this.setState({
                edges: edgeList
            })
            this.props.onChange(this.state.edges);
        })
    }

    /** Get the shortest path from the server*/
    path = async () => {
        return (await fetch("http://localhost:4567/path?start=" + encodeURIComponent(this.state.start) + "&end=" +
            encodeURIComponent(this.state.end))).json()
    }

    /** clear the input and alert other component*/
    onClear = () => {
        this.setState({start: 'PAR', end: 'PAR'});
        this.props.onChange([]);
        this.setState({edges: []})
    }

    /** update the start building*/
    startBuildingSelected = (e: any) => {
        // set state of b1 to the selected option
        this.setState({start: e.target.value})
        console.log(e.target.value);
    }

    /** update the end building*/
    endBuildingSelected = (e: any) => {
        this.setState({end: e.target.value})
    }
}

export default EdgeList;
