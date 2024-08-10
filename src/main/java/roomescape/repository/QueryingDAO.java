package roomescape.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import roomescape.Reservation;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QueryingDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public QueryingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("reservation")
                .usingGeneratedKeyColumns("id");
    }

    public List<Reservation> findAll() {
        return jdbcTemplate.query("SELECT * FROM reservation", new ReservationRowMapper());
    }

    public Reservation insert(Reservation reservation) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", reservation.getName());
        parameters.put("date", reservation.getDate());
        parameters.put("time", reservation.getTime());

        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        reservation.setId(id.longValue());
        return reservation;
    }

    public boolean delete(Long id) {
        int rowsAffected = jdbcTemplate.update("DELETE FROM reservation WHERE id = ?", id);
        return rowsAffected > 0;
    }

    public int count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM reservation", Integer.class);
    }

    private static class ReservationRowMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getLong("id"));
            reservation.setName(rs.getString("name"));
            reservation.setDate(rs.getDate("date").toLocalDate());
            reservation.setTime(rs.getTime("time").toLocalTime());
            return reservation;
        }
    }
}