package ro.utcn.sd.cata.stackoverflow.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.sd.cata.stackoverflow.entity.Answer;
import ro.utcn.sd.cata.stackoverflow.entity.Question;
import ro.utcn.sd.cata.stackoverflow.repository.AnswerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcAnswerRepository implements AnswerRepository {

    private final JdbcTemplate template;

    @Override
    public Answer save(Answer answer, Question question) {
        if(answer.getId()==null) {
            answer.setId(insert(answer));
        }
        else{
            update(answer);
        }
        question.getAnswer().add(answer);
        return answer;
    }

    private int insert(Answer answer){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("answer");
        insert.usingGeneratedKeyColumns("id");
        Map<String,Object> map = new HashMap<>();
        map.put("question_id",answer.getQuestionId());
        map.put("author_id",answer.getAuthorId());
        map.put("text",answer.getText());
        map.put("creation_date",answer.getCreationDate());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(Answer answer){
        template.update("UPDATE answer SET text=?, creation_date = ? WHERE id = ?",
                answer.getText(),answer.getCreationDate(),answer.getId());
    }

    public List<Answer> findByQuestionId(int id){
        List<Answer> foundAnswers = template.query("SELECT * FROM answer WHERE question_id=?",new AnswerMapper(),id);
        return foundAnswers;
    }

    @Override
    public void remove(Answer answer) {
        template.update("DELETE from answer WHERE id=?",answer.getId());
    }

    public Optional<Answer> findById(int id){
        List<Answer> found = template.query("SELECT * FROM answer WHERE id=?",new AnswerMapper(), id);
        return found.isEmpty()? Optional.empty() : Optional.of(found.get(0));
    }

}
