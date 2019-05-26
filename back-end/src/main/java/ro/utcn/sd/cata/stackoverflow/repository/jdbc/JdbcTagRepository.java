package ro.utcn.sd.cata.stackoverflow.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.sd.cata.stackoverflow.entity.Question;
import ro.utcn.sd.cata.stackoverflow.entity.Tag;
import ro.utcn.sd.cata.stackoverflow.exception.QuestionNotFoundException;
import ro.utcn.sd.cata.stackoverflow.repository.TagRepository;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository {

    private final JdbcTemplate template;

    @Override
    public Tag addTag(Tag tag, Question question) {
        if (tag.getId() == null) {
            tag.setId(insert(tag));
            insert(tag,question);
            question.getTag().add(tag);
        } else {
            insert(tag,question);
            question.getTag().add(tag);
        }
        return tag;
    }

    private int insert(Tag tag) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("tag");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("name", tag.getName());
        return insert.executeAndReturnKey(map).intValue();
    }

    private int insert(Tag tag, Question question) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("question_tag");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("question_id", question.getId());
        map.put("tag_id", tag.getId());
        return insert.executeAndReturnKey(map).intValue();
    }

    public Optional<Tag> findByName(String name){
        List<Tag> tagList = template.query("SELECT * FROM tag WHERE name=?",new TagMapper(),name);
        return tagList.isEmpty()? Optional.empty() : Optional.of(tagList.get(0));
    }

    public Optional<Tag> findById(Integer id){
        List<Tag> tagList = template.query("SELECT * FROM tag WHERE id=?",new TagMapper(),id);
        return tagList.isEmpty()? Optional.empty() : Optional.of(tagList.get(0));
    }

    public List<String> findByQuestionId(Integer questionId){
       return template
                .query("SELECT tag.name FROM tag JOIN question_tag ON tag.id = question_tag.tag_id " +
                        "WHERE question_tag.question_id=?",new TagListMapper(),questionId);

    }
}
