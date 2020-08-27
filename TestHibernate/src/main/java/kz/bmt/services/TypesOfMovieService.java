package kz.bmt.services;

import kz.bmt.config.SessionUtil;
import kz.bmt.dao.TypesOfMovieDAO;
import kz.bmt.entity.TypesOfMovie;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class TypesOfMovieService extends SessionUtil implements TypesOfMovieDAO {
    @Override
    public void add(TypesOfMovie typesOfMovie) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(typesOfMovie);

        //close session with a transaction
        closeTransactionSession();
    }

    @Override
    public List<TypesOfMovie> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM TYPE_MOVIE";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(TypesOfMovie.class);
        List<TypesOfMovie> typeOfMovieList = query.list();

        //close session with a transaction
        closeTransactionSession();

        return typeOfMovieList;
    }

    @Override
    public TypesOfMovie getById(int id) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM TYPE_MOVIE WHERE ID = ?";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(TypesOfMovie.class);
        query.setParameter(1, id);

        TypesOfMovie typeOfMovieListById = (TypesOfMovie) query.getSingleResult();

        //close session with a transaction
        closeTransactionSession();

        return typeOfMovieListById;
    }

    @Override
    public void update(TypesOfMovie typesOfMovie) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(typesOfMovie);

        //close session with a transaction
        closeTransactionSession();
    }

    @Override
    public void remove(TypesOfMovie typesOfMovie) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(typesOfMovie);

        //close session with a transaction
        closeTransactionSession();
    }
}
