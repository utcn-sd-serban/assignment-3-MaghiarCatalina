package ro.utcn.sd.cata.stackoverflow.repository;

import ro.utcn.sd.cata.stackoverflow.entity.Question;
import ro.utcn.sd.cata.stackoverflow.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

    Tag addTag(Tag tag, Question question);

    Optional<Tag> findByName(String name);

    Optional<Tag> findById(Integer id);

    List<String> findByQuestionId(Integer questionId);

}
