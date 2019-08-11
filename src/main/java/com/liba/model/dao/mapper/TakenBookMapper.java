package com.liba.model.dao.mapper;

import com.liba.model.entity.TakenBook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public class TakenBookMapper implements ObjectMapper<TakenBook> {
    @Override
    public TakenBook extractFromResultSet(ResultSet rs) throws SQLException {
        TakenBook takenBook = new TakenBook();
        takenBook.setId(rs.getLong("id"));

        Timestamp taken_time = rs.getTimestamp("taken_time");
        takenBook.setTaken_time(taken_time != null ? taken_time.toLocalDateTime() : null);

        Timestamp returned_time = rs.getTimestamp("returned_time");
        takenBook.setReturned_time(returned_time != null ? returned_time.toLocalDateTime() : null);


        return takenBook;
    }

    @Override
    public TakenBook makeUnique(Map<Long, TakenBook> cache, TakenBook takenBook, ResultSet rs) throws SQLException {
        cache.putIfAbsent(takenBook.getId(), takenBook);
        return cache.get(takenBook.getId());
    }
}
