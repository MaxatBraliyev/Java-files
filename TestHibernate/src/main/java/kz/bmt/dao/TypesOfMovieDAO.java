package kz.bmt.dao;

import kz.bmt.entity.TypesOfMovie;

import java.sql.SQLException;
import java.util.List;

public interface TypesOfMovieDAO {
    //create
    void add(TypesOfMovie typesOfMovie) throws SQLException;

    //read
    List<TypesOfMovie> getAll() throws SQLException;

    TypesOfMovie getById(int id) throws SQLException;

    //update
    void update(TypesOfMovie typesOfMovie) throws SQLException;

    //delete
    void remove(TypesOfMovie typesOfMovie) throws SQLException;
}
