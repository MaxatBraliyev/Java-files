package kz.bmt;

import kz.bmt.config.HibernateUtil;
import kz.bmt.entity.Movie;
import kz.bmt.services.MovieService;
import kz.bmt.services.TypesOfMovieService;

import java.sql.SQLException;

public class ApplicationMain {

    public static void main(String[] args) throws SQLException {

            MovieService movieService = new MovieService();
            TypesOfMovieService typesOfMovieService = new TypesOfMovieService();

            //Просмотр всех записей в таблице Movie
//            System.out.println("Результат выборки из таблицы всех записей: " + movieService.getAll());

            //Просмотр всех записей в таблицах Movie и Type_Movie
//            System.out.println("Результат выборки из таблиц всех записей: " + typesOfMovieService.getAll());


            //Создание записи в таблице Movie под id=какой то номер. У меня 6 запись, но может быть любой номер.
                //*Можно было создать конструктор в классе и пердать как одной строчкой, но это как учебное пособие... Movie movie = new Movie(1,"Доспехи Бога","Боевик с Джеки Чаном");
//            Movie movie = new Movie();
//            movie.setIdTypeMovie(1);
//            movie.setName("Доспехи Бога");
//            movie.setNameDesc("Боевик с Джеки Чаном");
//            movieService.add(movie);
//            System.out.println("Результат создания записи: " + movie);

            //Просмотр записи в таблице Movie id=6
//              System.out.println("Результат поиска по id=6: " + movieService.getById(6));

            //Обновление записи в таблице Movie id=6
//              Movie movie = new Movie();
//              movie.setIdTypeMovie(1);
//              movie.setName("Доспехи Бога 2");
//              movie.setNameDesc("Фильм про Джеки Чана \"Доспехи Бога 2\"");
//              movie.setId(6);
//              movieService.update(movie);
//              System.out.println("Результат изменения: " + movie);

            //Удаление записи в таблице Movie id=6
//            Movie movie = new Movie();
//            movie.setId(6);
//            movieService.remove(movie);
//              System.out.println("Запись удалена!");

            //закрытие коннекта.
            HibernateUtil.shutDown();
    }
}