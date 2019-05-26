package ro.utcn.sd.cata.stackoverflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Question {
    private Integer id;
    private Integer author;
    private String title;
    private String text;
    private String creationDate;
    private List<Tag> tag;

    private List<Answer> answer;

    public Question(){
        this.tag = new ArrayList<>();
    }
    public Question(String title, String text, String creationDate,Integer author){
        this.author = author;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.tag = new ArrayList<>();
        this.answer = new ArrayList<>();
    }

    public Question(Integer id, String title, String text, String creationDate){
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.tag = new ArrayList<>();
        this.answer = new ArrayList<>();
    }

    public Question(Integer id,Integer author, String title, String text, String creationDate){
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.author = author;
        this.tag = new ArrayList<>();
        this.answer = new ArrayList<>();
    }

}
