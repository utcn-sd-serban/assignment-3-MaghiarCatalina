import React, { Component } from "react";
import question from "../model/question";
import SearchTitle from "./SearchTitle";
import questionPresenter from "../presenter/QuestionsPresenter";

const mapModelStateToComponentState = modelState => ({
    searchedQuestions : modelState.searchedQuestions
});

export default class SmartSearchTitle extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(question.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        question.addListener("change", this.listener);
    }

    componentWillUnmount() {
        question.removeListener("change", this.listener);
    }

    render() {
        return (
            <SearchTitle 
                onBack={questionPresenter.onBack}
                foundQuestions={this.state.searchedQuestions} />               
        );
    }
}
