package ro.utcn.sd.cata.stackoverflow.repository;

import ro.utcn.sd.cata.stackoverflow.entity.Answer;
import ro.utcn.sd.cata.stackoverflow.entity.Question;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository {

    Answer save(Answer answer, Question question);

    void remove(Answer answer);

    List<Answer> findByQuestionId(int id);

    Optional<Answer> findById(int id);

}
