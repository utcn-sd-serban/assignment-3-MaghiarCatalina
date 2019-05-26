import React, { Component } from "react";
import question from "../model/question";
import ViewAllQuestions from "./ViewAllQuestions";
import questionPresenter from "../presenter/QuestionsPresenter";

const mapModelStateToComponentState = modelState => ({
    questions: modelState.questions
});

export default class SmartViewAllQuestions extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(question.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        question.addListener("change", this.listener);
        questionPresenter.onInit();
    }

    componentWillUnmount() {
        question.removeListener("change", this.listener);
    }

    render() {
        return (
            <ViewAllQuestions 
                onBack={questionPresenter.onBack}
                questions={this.state.questions} />               
        );
    }
}
