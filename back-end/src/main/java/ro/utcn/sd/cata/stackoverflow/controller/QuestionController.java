package ro.utcn.sd.cata.stackoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.cata.stackoverflow.command.AddQuestionCommand;
import ro.utcn.sd.cata.stackoverflow.command.Command;
import ro.utcn.sd.cata.stackoverflow.dto.CreateQuestionDTO;
import ro.utcn.sd.cata.stackoverflow.dto.QuestionDTO;
import ro.utcn.sd.cata.stackoverflow.entity.Question;
import ro.utcn.sd.cata.stackoverflow.service.QuestionService;

import java.util.List;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/view-all-questions")
    public List<QuestionDTO> listAll(){
        return questionService.findAllOrderByDate();
    }

    @PostMapping("/home")
    public QuestionDTO addQuestion(@RequestBody CreateQuestionDTO questionDTO){
        Command command = new AddQuestionCommand(questionDTO,questionService);
        return (QuestionDTO) command.execute();
    }

    @GetMapping("/title-search/{title}")
    public List<QuestionDTO> searchByTitle(@PathVariable(value = "title") String title){
        return questionService.findByTitle(title);
    }

    @GetMapping("/tag-search/{tag}")
    public List<QuestionDTO> searchByTag(@PathVariable(value = "tag") String tag){
        return questionService.findByTag(tag);
    }
}
