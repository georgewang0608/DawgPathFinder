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

interface EdgeListProps {
    onChange(edges: ColoredEdge[]): void;  // called when a new edge list is ready
                                    // TODO: once you decide how you want to communicate the edges to the App, you should
                                    // change the type of edges so it isn't `any`
}

interface EdgeListState {
    inputValue: string; // The string input by user
}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 */
class EdgeList extends Component<EdgeListProps, EdgeListState> {
    constructor(props: any) {
        super(props);
        this.state = {inputValue: ''};
    }

    render() {
        return (
            <div id="edge-list">
                Edges <br/>
                <textarea
                    rows={5}
                    cols={30}
                    onChange={(event) => {console.log('textarea onChange was called');
                    this.setState({inputValue: event.target.value})}}
                    value={this.state.inputValue}
                /> <br/>
                <button onClick={this.onDraw}>Draw</button>
                <button onClick={this.onClear}>Clear</button>
            </div>
        );
    }

    /** update the string input to the edges*/
    onDraw = () => {
        let lines = this.state.inputValue.trim().split('\n');
        let edgeList : ColoredEdge[] = [];
        for (let i = 0; i < lines.length; i++) {
            let arg: string[] = lines[i].trim().split(/\s+/);
            if (arg.length !== 5) {
                alert("Require 5 arguments including four coordinates and one color");
                this.setState({inputValue: ''});
                this.props.onChange([]);
                return;
            }
            // check if the first 4 coordinates are number or out of boundary
            for (let j = 0; j < arg.length - 1; j++) {
                if (isNaN(Number(arg[j]))) {
                    alert("The input coordinate should only contains numbers");
                    this.setState({inputValue: ''});
                    this.props.onChange([]);
                    return;
                } else if (Number(arg[j]) < 0 || Number(arg[j]) > 4000) {
                    alert("Input coordinate is out of boundary");
                    this.setState({inputValue: ''});
                    this.props.onChange([]);
                    return;
                }
            }
            if (!isNaN(Number(arg[4]))) {
                alert("The fifth input is suppose to be a color");
                this.setState({inputValue: ''});
                this.props.onChange([]);
                return;
            }
            edgeList.push({x1: Number(arg[0]), y1: Number(arg[1]), x2: Number(arg[2]), y2: Number(arg[3]), color: arg[4]});
        }
        this.props.onChange(edgeList);
    }

    /** clear the input and alert other component*/
    onClear = () => {
        this.setState({inputValue: ''});
        this.props.onChange([]);
    }
}

export default EdgeList;
