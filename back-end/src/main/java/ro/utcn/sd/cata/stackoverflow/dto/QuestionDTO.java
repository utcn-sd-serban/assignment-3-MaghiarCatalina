package ro.utcn.sd.cata.stackoverflow.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private String author;
    private String title;
    private String text;
    private String date;
    private String tags;
}
