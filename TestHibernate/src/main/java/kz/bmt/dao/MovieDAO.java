package kz.bmt.dao;

import kz.bmt.entity.Movie;

import java.sql.SQLException;
import java.util.List;

public interface MovieDAO {

    //create
    void add(Movie movie) throws SQLException;

    //read
    List<Movie> getAll() throws SQLException;

    Movie getById(int id) throws SQLException;

    //update
    void update(Movie movie) throws SQLException;

    //delete
    void remove(Movie movie) throws SQLException;

}
