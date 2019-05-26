package ro.utcn.sd.cata.stackoverflow.repository;

import ro.utcn.sd.cata.stackoverflow.entity.Question;
import ro.utcn.sd.cata.stackoverflow.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    Question save(Question question);

    List<Question> findAll();

    void remove(Question question);

    Optional<Question> findById(Integer id);

    List<Question> findAllOrderByDate();

    List<Question> findByTitle(String title);

    List<Question> findByTag(String tag);

}
