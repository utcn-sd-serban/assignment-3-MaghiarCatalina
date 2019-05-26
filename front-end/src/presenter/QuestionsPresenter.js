import question from "../model/question";
//import user from "../model/user";

class QuestionsPresenter{

    onViewAll(){
        window.location.assign("#/view-all-questions");
    }

    onInit(){
        question.loadQuestions();
    }

    onAddQuestion(){
        var author = question.state.newQuestion.author;
        var title = question.state.newQuestion.title;
        var text = question.state.newQuestion.text;
       
        var tags = question.state.newQuestion.tags;
       
        question.createQuestion(author,title,text,tags).then(()=>{
        question.changeNewQuestionProperty("author","");
        question.changeNewQuestionProperty("title", "");
        question.changeNewQuestionProperty("text", "");
        question.changeNewQuestionProperty("tags", "");
        window.location.assign("#/view-all-questions");
        });
    }

  
    onSearchByTitle(){
        var title = question.state.titleSearch;
        question.searchByTitle(title);
        window.location.assign('#/title-search/'+title);
    }

    onSearchByTag(){
        var tag = question.state.tagSearch;
        question.searchByTag(tag);
        window.location.assign('#/tag-search/'+tag);
    }

    onChange(property, value){
       question.changeNewQuestionProperty(property,value);
    }

    onChangeSearch(property,value){
        question.changeProperty(property,value);
    }
    
    onBack(){
        window.location.assign('#/home');
    }
}

const questionsPresenter = new QuestionsPresenter();

export default questionsPresenter;