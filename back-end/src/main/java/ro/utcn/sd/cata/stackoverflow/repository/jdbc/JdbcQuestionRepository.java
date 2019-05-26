package ro.utcn.sd.cata.stackoverflow.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.sd.cata.stackoverflow.entity.Answer;
import ro.utcn.sd.cata.stackoverflow.entity.Question;
import ro.utcn.sd.cata.stackoverflow.entity.Tag;
import ro.utcn.sd.cata.stackoverflow.repository.QuestionRepository;

import java.util.*;

@AllArgsConstructor
public class JdbcQuestionRepository implements QuestionRepository {
    private final JdbcTemplate template;

    @Override
    public Question save(Question question) {
        if (question.getId() == null) {
            question.setId(insert(question));
        } else {
            update(question);
        }
        return question;
    }

    private int insert(Question question) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("question");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("title", question.getTitle());
        map.put("text", question.getTitle());
        map.put("creation_date",question.getCreationDate());
        map.put("author", question.getAuthor());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(Question question) {
        template.update("UPDATE question SET author = ?, title = ?, text = ?, creation_date = ? WHERE id = ?",
                question.getAuthor(), question.getTitle(), question.getText(), question.getCreationDate(), question.getId());
    }

    @Override
    public List<Question> findAll() {
        return template.query("SELECT * FROM question",new QuestionMapper());
    }

    @Override
    public void remove(Question question) {
        template.update("DELETE FROM question WHERE id=?",question.getId());
    }

    @Override
    public Optional<Question> findById(Integer id) {
        List<Question> questions = template.query("SELECT * FROM question WHERE id = ?", new QuestionMapper(), id);
        return questions.isEmpty() ? Optional.empty() : Optional.of(questions.get(0));
    }

    @Override
    public List<Question> findAllOrderByDate(){
        List<Question> questions = template.query("SELECT * FROM question ORDER BY creation_date DESC", new QuestionMapper());
        for(Question question: questions){
            List<Tag> tags = findTags(question);
            for(Tag tag: tags){
                question.getTag().add(tag);
            }
            List<Answer> answers = findAnswers(question);
            for(Answer answer: answers){
                question.getAnswer().add(answer);
            }
        }
        return questions;
    }

    private List<Tag> findTags(Question question){
        JdbcTagRepository jdbcTagRepository = new JdbcTagRepository(template);
        List<Tag> tagList = new ArrayList<>();
        List<Integer> tagIdList = template.query("SELECT tag_id FROM question_tag WHERE question_id=?", (resultSet, i) -> resultSet.getInt("tag_id"),question.getId());
        for(Integer i: tagIdList){
            tagList.add(jdbcTagRepository.findById(i).get());
        }
        return tagList;
    }

    private List<Answer> findAnswers(Question question){
        JdbcAnswerRepository jdbcAnswerRepository = new JdbcAnswerRepository(template);
        List<Answer> answerList = jdbcAnswerRepository.findByQuestionId(question.getId());
        return answerList;
    }

    public List<Question> findByTitle(String title){
        List<Question> foundQuestions = new ArrayList<>();
        for(Question question: findAll()){
            if(question.getTitle().contains(title)){
                foundQuestions.add(question);
            }
        }
        return foundQuestions;
    }

    public List<Question> findByTag(String tag){
        JdbcQuestionRepository jdbcQuestionRepository = new JdbcQuestionRepository(template);
        List<Question> questionList = new ArrayList<>();
        List<Integer> questionIdList = template.query("SELECT question_id FROM question_tag JOIN tag ON question_tag.tag_id = tag.id AND tag.name=?", (resultSet, i) -> resultSet.getInt("question_id"),tag);
        for(Integer i: questionIdList){
            Question currentQuestion = jdbcQuestionRepository.findById(i).get();
            List<Tag> tags = findTags(currentQuestion);
            for(Tag t: tags){
                currentQuestion.getTag().add(t);
            }
            questionList.add(currentQuestion);
        }
        return questionList;
    }

}
