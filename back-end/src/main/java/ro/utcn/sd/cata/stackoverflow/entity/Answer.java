package ro.utcn.sd.cata.stackoverflow.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private Integer id;
    private Integer questionId;
    private Integer authorId;
    private String text;
    private String creationDate;

    public Answer(int questionId, int authorId, String text, String creationDate){
        this.questionId = questionId;
        this.authorId = authorId;
        this.text = text;
        this.creationDate = creationDate;
    }

}
