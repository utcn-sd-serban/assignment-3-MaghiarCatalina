package ro.utcn.sd.cata.stackoverflow.dto;

import lombok.Data;

@Data
public class CreateQuestionDTO {
    private String title;
    private String author;
    private String text;
    private String tags;
}
