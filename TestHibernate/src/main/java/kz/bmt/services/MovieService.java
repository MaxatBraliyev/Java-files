package kz.bmt.services;

import kz.bmt.config.SessionUtil;
import kz.bmt.dao.MovieDAO;
import kz.bmt.entity.Movie;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public class MovieService extends SessionUtil implements MovieDAO {

    @Override
    public void add(Movie movie) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(movie);

        //close session with a transaction
        closeTransactionSession();
    }

    @Override
    public List<Movie> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM MOVIE";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Movie.class);
        List<Movie> movieAllList = query.list();

        //close session with a transaction
        closeTransactionSession();

        return movieAllList;
    }

    @Override
    public Movie getById(int id) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM MOVIE WHERE ID = ?";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Movie.class);
        query.setParameter(1, id);

        Movie movie = (Movie) query.getSingleResult();

        //close session with a transaction
        closeTransactionSession();

        return movie;
    }

    @Override
    public void update(Movie movie) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(movie);

        //close session with a transaction
        closeTransactionSession();
    }

    @Override
    public void remove(Movie movie) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(movie);

        //close session with a transaction
        closeTransactionSession();
    }
}
