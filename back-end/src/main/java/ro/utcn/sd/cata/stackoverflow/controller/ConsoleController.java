package ro.utcn.sd.cata.stackoverflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.utcn.sd.cata.stackoverflow.entity.Answer;
import ro.utcn.sd.cata.stackoverflow.entity.Question;
import ro.utcn.sd.cata.stackoverflow.entity.User;
import ro.utcn.sd.cata.stackoverflow.exception.UserNotFoundException;
import ro.utcn.sd.cata.stackoverflow.service.AnswerService;
import ro.utcn.sd.cata.stackoverflow.service.QuestionService;
import ro.utcn.sd.cata.stackoverflow.service.TagService;
import ro.utcn.sd.cata.stackoverflow.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleController //implements CommandLineRunner
 {
//    private final Scanner scanner = new Scanner(System.in);
//    private final UserService userService;
//    private final QuestionService questionService;
//    private final TagService tagService;
//    private final AnswerService answerService;
//    private boolean loggedIn = false;
//    private User loggedUser;
//
//    private boolean handleCommand(String command) {
//        switch (command) {
//            case "addUser":
//                handleAddUser();
//                return false;
//            case "removeUser":
//                handleRemoveUser();
//                return false;
//            case "login":
//                handleLogin();
//                return false;
//            case "addQuestion":
//                handleAddQuestion();
//                return false;
//            case "list":
//                handleList();
//                return  false;
//            case "findTag":
//                handleFindTag();
//                return false;
//            case "findTitle":
//                handleFindTitle();
//                return false;
//            case "addAnswer":
//                handleAddAnswer();
//                return false;
//            case "editAnswer":
//                handleEditAnswer();
//                return false;
//            case "removeAnswer":
//                handleRemoveAnswer();
//                return false;
//            case "exit":
//                return true;
//            default:
//                print("Unknown command. Try again.");
//                return false;
//        }
//    }
//
//    private void handleAddUser(){
//        print("Username:");
//        String username = scanner.next().trim();
//        print("Password:");
//        String password = scanner.next().trim();
//        User user = userService.addUser(username, password);
//        print("Created user: " + user + ".");
//    }
//
//    private void handleRemoveUser(){
//        print("User id:");
//        Integer id = scanner.nextInt();
//        userService.removeUser(id);
//        print("User removed.");
//    }
//
//    private void handleLogin(){
//        print("Enter username:");
//        String username = scanner.next().trim();
//        print("Enter password:");
//        String password = scanner.next().trim();
//        Optional<User> user = userService.findUserByUsernamePassword(username,password);
//        if(user.equals(Optional.empty())){
//            print("User or Password incorrect. Please try again");
//        }
//        else{
//            loggedUser =  user.get();
//            loggedIn = true;
//            print("Login successful. Welcome "+loggedUser.getUsername()+"! \n");
//        }
//    }
//
//    private void handleAddQuestion(){
//        if(loggedIn){
//            String getNewlineRemainedFromPreviousCommand = scanner.nextLine().trim();
//            print("Enter title:");
//            String title = scanner.nextLine().trim();
//            print("Enter text:");
//            String text = scanner.nextLine().trim();
//            Question addedQuestion = questionService.addQuestion(title,text,loggedUser);
//            boolean doneAddingTags = false;
//            while(!doneAddingTags){
//                print("Enter tag or type done: ");
//                String tag = scanner.next().trim();
//                if(tag.equals("done")){
//                    doneAddingTags=true;}
//                else{
//                    tagService.addTag(tag,addedQuestion);}
//            }
//            print(("Question added: "+ addedQuestion.toString()));
//        }
//        else print("Log in in order to add questions.");
//    }
//
//    private void handleList(){
//        if(loggedIn){
//            for(Question question: questionService.findAllOrderByDate())
//                print(question.toString());
//        }
//        else print("Log in in order to see questions.");
//    }
//
//    private void handleFindTag(){
//        if(loggedIn){
//            print("Write the tag: ");
//            String tag = scanner.next().trim();
//            if(tagService.findByName(tag).isPresent()){
//                for(Question question: questionService.findByTag(tagService.findByName(tag).get())){
//                    print(question.toString());
//                }
//            }
//            else{
//                print("No questions found with tag "+tag);
//            }
//        }
//        else print("Log in in order to search questions.");
//    }
//
//    private void handleFindTitle(){
//        if(loggedIn){
//            print("Write title: ");
//            String title = scanner.next().trim();
//            List<Question> foundQuestions = questionService.findByTitle(title);
//            if(foundQuestions.isEmpty()){
//                print("No questions found with title containing "+title);
//            }
//            else
//            {
//                for(Question question: foundQuestions){
//                    print(question.toString());}
//            }
//        }
//        else print("Log in in order to search questions.");
//    }
//
//    public void handleAddAnswer(){
//        if(loggedIn){
//            print("Give the question number: ");
//            Integer questionId = scanner.nextInt();
//            String getNewlineRemainedFromPreviousCommand = scanner.nextLine();
//            print("Write answer: ");
//            String text = scanner.nextLine().trim();
//            Answer addedAnswer = answerService.addAnswer(loggedUser,questionId,text);
//            print("Answer added: "+addedAnswer.toString());
//        }
//        else print("Log in in order to add answers.");
//    }
//
//    public void handleEditAnswer(){
//        if(loggedIn){
//            print("Give the answer id: ");
//            Integer answerId = scanner.nextInt();
//            String getNewlineRemainedFromPreviousCommand = scanner.nextLine();
//            print("Write new text: ");
//            String text = scanner.nextLine().trim();
//            Answer edited = answerService.editAnswer(loggedUser,answerId,text);
//            if(edited.getId()!=null){
//                print("Answer edited.");
//                print(edited.toString());
//            }
//            else{
//                print("You must be the author of the answer to edit it.");
//            }
//        }
//        else print("Log in in order to edit your answers.");
//    }
//
//    public void handleRemoveAnswer(){
//        if(loggedIn){
//            print("Give the answer id: ");
//            Integer answerId = scanner.nextInt();
//            if(answerService.removeAnswer(answerId,loggedUser.getId())){
//                print("Answer removed.");
//            }
//            else{
//                print("You must be the author of the answer to remove it.");
//            }
//        }
//        else print("Log in in order to remove your answers.");
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        print("Welcome to Stack-Overflow.");
//        boolean done = false;
//        while (!done) {
//            print("Enter a command: ");
//            String command = scanner.next().trim();
//            try {
//                done = handleCommand(command);
//            } catch (UserNotFoundException e) {
//                print("Exception in run method of Controller");
//            }
//        }
//    }
//
//    private void print(String value){System.out.println(value);}
}
