const BASE_URL = "http://localhost:8080";

export default class RestClient {
    constructor(username, password) {
        this.authorization = "Basic " + btoa(username + ":" + password);
    }

    loadAllQuestions(){
        return fetch(BASE_URL+"/view-all-questions", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    loadQuestionsByTitle(searchedTitle){
        return fetch(BASE_URL+"/title-search/"+searchedTitle, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    loadQuestionsByTag(searchedTag){
        return fetch(BASE_URL+"/tag-search/"+searchedTag, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    login(){
        return fetch(BASE_URL+"/",{
            method: "POST",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    addQuestion(author,title,text,tags){
        return fetch(BASE_URL+"/home",{
            method: "POST",
            body: JSON.stringify({
                title: title,
                author: author,
                text: text,
                tags: tags
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        }).then(response => response.json());
    }
}