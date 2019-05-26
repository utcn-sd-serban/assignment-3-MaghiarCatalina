package ro.utcn.sd.cata.stackoverflow.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.cata.stackoverflow.entity.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {

    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Question(rs.getInt("id"),
                rs.getInt("author"),
                rs.getString("title"),
                rs.getString("text"),
                rs.getString("creation_date")
        );
    }
}
//might need diff args(also change constructor in Question entity)