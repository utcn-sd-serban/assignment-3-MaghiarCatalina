import { EventEmitter } from "events";
import RestClient from "../rest/RestClient";

const client = new RestClient("Cata","1234");

class Question extends EventEmitter{
    constructor(){
        super();
        this.state = {
            questions: [],
            newQuestion: {
            author : "",
            title: "",
            text: "",
            date: "",
            tags: ""
            },
            searchedQuestions:[],
            searchedQuestionsByTag:[],
            titleSearch: "",
            tagSearch: ""
        };
    }

    loadQuestions(){
        return client.loadAllQuestions().then(questions => {
            this.state = {
                ...this.state,
                questions: questions
            };
            this.emit("change", this.state);
        })
    }

    createQuestion(author, title, text, tags){
        return client.addQuestion(author,title,text,tags)
        .then(question => {
            this.state={
                ...this.state,
                questions: this.state.questions.concat([question])
            };
            this.emit("change",this.state);
        });
    }

    appendQuestion(question) {
        this.state = { 
            ...this.state, 
            questions: this.state.questions.concat([question]) 
        };
        this.emit("change", this.state);
    }

    changeNewQuestionProperty(property, value){
        this.state = {
            ...this.state,
            newQuestion: {
                ...this.state.newQuestion,
                [property]: value
            }
        };
        this.emit("change",this.state);
    }

    changeSearchedQuestionsProperty(quest){
        this.state ={
            ...this.state,
            searchedQuestions: this.state.searchedQuestions.concat(quest)
        };
        this.emit("change",this.state);
    }

    changeSearchedQuestionsByTagProperty(quest){
        this.state ={
            ...this.state,
            searchedQuestionsByTag: this.state.searchedQuestionsByTag.concat(quest)
        };
        this.emit("change",this.state);
    }

    changeProperty(property,value){
        this.state={
            ...this.state,
            [property]: value
        };
        this.emit("change", this.state);
    }

    // searchByTitle(titleSearch){
    //     this.state.searchedQuestions = this.state.questions.filter(q => q.title.includes(titleSearch));
    //     this.emit("change", this.state);
    // }

    // searchByTag(tagSearch){
    //     this.state.searchedQuestionsByTag = this.state.questions.filter(q => q.tags.includes(tagSearch));
    //     this.emit("change", this.state);
    // }

    searchByTitle(searchedTitle){
        return client.loadQuestionsByTitle(searchedTitle).then(response=>{
            this.state = {
                ...this.state,
                searchedQuestions: response
    
            };
            this.emit("change", this.state);
        });
    }

    searchByTag(searchedTag){
        return client.loadQuestionsByTag(searchedTag).then(response=>{
            this.state = {
                ...this.state,
                searchedQuestionsByTag: response
    
            };
            this.emit("change", this.state);
        });
    }
}

const question = new Question();

export default question;