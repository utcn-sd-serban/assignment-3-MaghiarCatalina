package ro.utcn.sd.cata.stackoverflow.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.sd.cata.stackoverflow.repository.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "stackoverflow.repository-type", havingValue = "JDBC")
public class JdbcRepositoryFactory implements RepositoryFactory {

    private final JdbcTemplate template;

    @Override
    public UserRepository createUserRepository() {
        return new JdbcUserRepository(template);
    }

    @Override
    public QuestionRepository createQuestionRepository() {
        return new JdbcQuestionRepository(template);
    }

    public TagRepository createTagRepository(){
        return new JdbcTagRepository(template);
    }

    public AnswerRepository createAnswerRepository(){return new JdbcAnswerRepository(template);}
}
