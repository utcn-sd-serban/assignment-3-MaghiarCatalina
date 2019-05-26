package ro.utcn.sd.cata.stackoverflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.cata.stackoverflow.entity.Answer;
import ro.utcn.sd.cata.stackoverflow.entity.Question;
import ro.utcn.sd.cata.stackoverflow.entity.User;
import ro.utcn.sd.cata.stackoverflow.exception.AnswerNotFoundException;
import ro.utcn.sd.cata.stackoverflow.exception.QuestionNotFoundException;
import ro.utcn.sd.cata.stackoverflow.repository.RepositoryFactory;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public Answer addAnswer(User author, int questionId, String text){
        return repositoryFactory.createAnswerRepository().save(
                new Answer(questionId, author.getId(),text, LocalDateTime.now().toString()),repositoryFactory.createQuestionRepository().findById(questionId).orElseThrow(QuestionNotFoundException::new));
    }

    @Transactional
    public boolean removeAnswer(int id, int authorId){
        Answer answerToRemove = repositoryFactory.createAnswerRepository().findById(id).orElseThrow(AnswerNotFoundException::new);
        if(answerToRemove.getAuthorId().equals(authorId)) {
            repositoryFactory.createAnswerRepository().remove(answerToRemove);
            return true;
        }
        else return false;
    }

    @Transactional
    public Answer editAnswer(User author,int answerId, String text){
        Answer answerToBeEdited = repositoryFactory.createAnswerRepository().findById(answerId).orElseThrow(AnswerNotFoundException::new);
        int questionId = answerToBeEdited.getQuestionId();
        Question mainQuestion = repositoryFactory.createQuestionRepository().findById(questionId).orElseThrow(QuestionNotFoundException::new);
        if(answerToBeEdited.getAuthorId().equals(author.getId())) {
            answerToBeEdited.setText(text);
            answerToBeEdited.setCreationDate(LocalDateTime.now().toString());
            return repositoryFactory.createAnswerRepository().save(answerToBeEdited,mainQuestion);
        }
        else{
            return new Answer();
        }
    }

}
