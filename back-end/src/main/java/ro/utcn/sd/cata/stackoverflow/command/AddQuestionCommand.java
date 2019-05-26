package ro.utcn.sd.cata.stackoverflow.command;

import lombok.Data;
import ro.utcn.sd.cata.stackoverflow.dto.CreateQuestionDTO;
import ro.utcn.sd.cata.stackoverflow.dto.QuestionDTO;
import ro.utcn.sd.cata.stackoverflow.service.QuestionService;

@Data
public class AddQuestionCommand implements Command
         {
    private final CreateQuestionDTO questionDTO;
    private final QuestionService questionService;

    @Override
    public Object execute() {
        return questionService.addQuestion(questionDTO);
    }
}
