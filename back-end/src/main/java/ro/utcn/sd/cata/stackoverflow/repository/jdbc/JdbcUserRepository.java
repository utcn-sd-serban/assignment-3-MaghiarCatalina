package ro.utcn.sd.cata.stackoverflow.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.sd.cata.stackoverflow.entity.User;
import ro.utcn.sd.cata.stackoverflow.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate template;

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(insert(user));
        } else {
            update(user);
        }
        return user;
    }

    private int insert(User user) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("user");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(User user) {
        template.update("UPDATE user SET username = ?, password = ? WHERE id = ?",
                user.getUsername(), user.getPassword(), user.getId());
    }

    public List<User> findAll(){
        return template.query("SELECT * FROM user", new UserMapper());
    }

    public void remove(User user){
        template.update("DELETE FROM user WHERE id=?",user.getId());
    }

    public Optional<User> findById(Integer id){
        return Optional.ofNullable(template.query("SELECT * FROM user WHERE id=?",new UserMapper(),id).get(0));
    }

    public Optional<User> findByUsernameAndPassword(String username, String password){
        return Optional.ofNullable(template.query("SELECT * FROM user WHERE username=? AND password=?",
                new UserMapper(),username,password).get(0));
    }

    public Optional<User> findByUsername(String username){
        return Optional.ofNullable(template.query("SELECT * FROM user WHERE username=?",
                new UserMapper(),username).get(0));
    }

}
