package ro.utcn.sd.cata.stackoverflow.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.cata.stackoverflow.entity.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Tag(rs.getInt("id"),
                rs.getString("name")
        );
    }
}
