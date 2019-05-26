package ro.utcn.sd.cata.stackoverflow.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.cata.stackoverflow.entity.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {
    public Answer mapRow(ResultSet rs, int rowNr) throws SQLException{
        return new Answer(rs.getInt("id"),
                rs.getInt("question_id"),
                rs.getInt("author_id"),
                rs.getString("text"),
                rs.getString("creation_date"));
    }
}
