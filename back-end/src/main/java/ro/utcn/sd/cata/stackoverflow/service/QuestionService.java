package ro.utcn.sd.cata.stackoverflow.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.cata.stackoverflow.dto.CreateQuestionDTO;
import ro.utcn.sd.cata.stackoverflow.dto.QuestionDTO;
import ro.utcn.sd.cata.stackoverflow.entity.Question;
import ro.utcn.sd.cata.stackoverflow.entity.Tag;
import ro.utcn.sd.cata.stackoverflow.entity.User;
import ro.utcn.sd.cata.stackoverflow.exception.QuestionNotFoundException;
import ro.utcn.sd.cata.stackoverflow.repository.RepositoryFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final RepositoryFactory repositoryFactory;
    private final ModelMapper modelMapper;

    @Transactional
    public QuestionDTO addQuestion(CreateQuestionDTO questionDTO){
        Question newQuestion = new Question
                (questionDTO.getTitle(),questionDTO.getText(),LocalDateTime.now().toString(),
                        repositoryFactory.createUserRepository().findByUsername(questionDTO.getAuthor()).get().getId());
        newQuestion.setId(null);
        Question savedQuestion = repositoryFactory.createQuestionRepository().save(newQuestion);
        String[] splited = questionDTO.getTags().split(" ");
        for(int i=0;i<splited.length;i++) {
            //repositoryFactory.createTagRepository().addTag(new Tag(questionDTO.getTags()),savedQuestion);
            repositoryFactory.createTagRepository().addTag(new Tag(splited[i]),savedQuestion);
        }
        QuestionDTO savedQuestionDTO = new QuestionDTO();
        modelMapper.map(savedQuestion,savedQuestionDTO);
        savedQuestionDTO.setAuthor(repositoryFactory.createUserRepository().findById(savedQuestion.getAuthor()).get()
                .getUsername());
        savedQuestionDTO.setDate(savedQuestion.getCreationDate());
        savedQuestionDTO.setTags(questionDTO.getTags());
        return savedQuestionDTO;
    }

    @Transactional
    public List<QuestionDTO> findAllOrderByDate(){
        List<Question> questionList = repositoryFactory.createQuestionRepository().findAllOrderByDate();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question:questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            StringBuilder tags=new StringBuilder();
            modelMapper.map(question,questionDTO);
            questionDTO.setAuthor(repositoryFactory.createUserRepository()
                    .findById(question.getAuthor()).get().getUsername());
            for(String tag : repositoryFactory.createTagRepository().findByQuestionId(question.getId())){
                tags.append(" ").append(tag);
            }
            questionDTO.setTags(tags.toString());
            questionDTO.setDate(question.getCreationDate());
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

   @Transactional
   public void remove(int id){
       repositoryFactory.createQuestionRepository().remove(repositoryFactory.createQuestionRepository()
               .findById(id).orElseThrow(QuestionNotFoundException::new));
   }

    @Transactional
    public List<QuestionDTO> findByTag(String name){
        List<Question> questions = repositoryFactory.createQuestionRepository().findByTag(name);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for(Question question: questions){
            QuestionDTO questionDTO = new QuestionDTO();
            modelMapper.map(question,questionDTO);
            questionDTO.setAuthor(repositoryFactory.createUserRepository().findById(question.getAuthor()).get().getUsername());

            StringBuilder tagString = new StringBuilder();
            for(Tag tag: question.getTag()){
                tagString.append(tag.getName()).append(" ");
            }

            questionDTO.setTags(tagString.toString());
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    @Transactional
    public List<QuestionDTO> findByTitle(String title){
        List<Question> questions =  repositoryFactory.createQuestionRepository().findByTitle(title);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for(Question question: questions){
            QuestionDTO questionDTO = new QuestionDTO();
            modelMapper.map(question,questionDTO);
            questionDTO.setAuthor(repositoryFactory.createUserRepository().findById(question.getAuthor()).get().getUsername());
            List<String> tagList = repositoryFactory.createTagRepository().findByQuestionId(question.getId());
            StringBuilder tagString = new StringBuilder();
            for(String tag: tagList){
                tagString.append(" ").append(tag);
            }
            questionDTO.setTags(tagString.toString());
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;

    }

}
