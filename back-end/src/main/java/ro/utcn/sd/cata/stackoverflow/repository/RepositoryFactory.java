package ro.utcn.sd.cata.stackoverflow.repository;

public interface RepositoryFactory {
    UserRepository createUserRepository();
    QuestionRepository createQuestionRepository();
    TagRepository createTagRepository();
    AnswerRepository createAnswerRepository();
}
