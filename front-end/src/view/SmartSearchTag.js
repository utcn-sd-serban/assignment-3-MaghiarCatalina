import React, { Component } from "react";
import question from "../model/question";
import SearchTag from "./SearchTag";
import questionPresenter from "../presenter/QuestionsPresenter";

const mapModelStateToComponentState = modelState => ({
    searchedQuestionsByTag : modelState.searchedQuestionsByTag
});

export default class SmartSearchTag extends Component {
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
            <SearchTag 
                onBack={questionPresenter.onBack}
                foundQuestionsByTag={this.state.searchedQuestionsByTag} />               
        );
    }
}
