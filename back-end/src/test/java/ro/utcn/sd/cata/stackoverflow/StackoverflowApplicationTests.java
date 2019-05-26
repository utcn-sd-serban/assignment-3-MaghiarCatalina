package ro.utcn.sd.cata.stackoverflow;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.sd.cata.stackoverflow.entity.Answer;
import ro.utcn.sd.cata.stackoverflow.entity.Question;
import ro.utcn.sd.cata.stackoverflow.entity.Tag;
import ro.utcn.sd.cata.stackoverflow.entity.User;
import ro.utcn.sd.cata.stackoverflow.exception.QuestionNotFoundException;
import ro.utcn.sd.cata.stackoverflow.exception.UserNotFoundException;
import ro.utcn.sd.cata.stackoverflow.repository.RepositoryFactory;
//import ro.utcn.sd.cata.stackoverflow.repository.memory.InMemoryRepositoryFactory;
import ro.utcn.sd.cata.stackoverflow.service.QuestionService;
import ro.utcn.sd.cata.stackoverflow.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class StackoverflowApplicationTests {

//	private static RepositoryFactory createMockedFactory() {
//		RepositoryFactory factory = new InMemoryRepositoryFactory();
//		List<Tag> tagList = new ArrayList<>();
//		tagList.add(new Tag(1,"spring"));
//		tagList.add(new Tag(2,"java"));
//		//
//		List<Answer> answerList = new ArrayList<>();
//		answerList.add(new Answer(1,2,1,"blaa","2019-04-06T19:41:20.686144800"));
//		factory.createUserRepository().save(new User(1,"ana","pass"));
//		factory.createUserRepository().save(new User(2,"dana","123"));
//		factory.createQuestionRepository().save(new Question(2,1, "titlu", "blabla", "2019-04-06T16:41:20.686144800",tagList,answerList));
//		return factory;
//	}
//
//	@Test
//	public void testRemoveWorksWithExistingId() {
//		RepositoryFactory factory = createMockedFactory();
//		UserService service = new UserService(factory);
//		service.removeUser(1);
//		Assert.assertEquals(1, factory.createUserRepository().findAll().size());
//		Assert.assertTrue(factory.createUserRepository().findById(2).isPresent());
//	}
//
//	@Test(expected = UserNotFoundException.class)
//	public void testRemoveThrowsWithNotExistingId() {
//		RepositoryFactory factory = createMockedFactory();
//		UserService service = new UserService(factory);
//		service.removeUser(999);
//	}
//
//	@Test
//	public void testAddUserPutsNewUserInMemory(){
//		RepositoryFactory factory = createMockedFactory();
//		UserService service = new UserService(factory);
//		service.addUser("cata","345");
//		Assert.assertTrue(factory.createUserRepository().findByUsernameAndPassword("cata","345").isPresent());
//	}
//
//	@Test(expected = QuestionNotFoundException.class)
//	public void testRemoveQuestionWhenIdNotExists(){
//		RepositoryFactory factory = createMockedFactory();
//		QuestionService service = new QuestionService(factory);
//		service.remove(1);
//		Assert.assertTrue(!factory.createQuestionRepository().findById(1).isPresent());
//	}
//
//	@Test
//	public void testRemoveQuestionWhenIdExists(){
//		RepositoryFactory factory = createMockedFactory();
//		QuestionService service = new QuestionService(factory);
//		service.remove(2);
//		Assert.assertTrue(!factory.createQuestionRepository().findById(2).isPresent());
//	}
//
//	@Test
//	public void testAddQuestion(){
//		RepositoryFactory factory = createMockedFactory();
//		QuestionService service = new QuestionService(factory);
//		service.addQuestion("this is a title","this is a text",factory.createUserRepository().findById(1).get());
//		Assert.assertEquals(2, factory.createQuestionRepository().findAll().size());
//	}

}
