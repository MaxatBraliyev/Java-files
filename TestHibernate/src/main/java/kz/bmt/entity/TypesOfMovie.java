package kz.bmt.entity;

import javax.persistence.*;

@Entity
@Table(name="type_movie")
public class TypesOfMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_type_movie;

    @Column(name="description_type_movie")
    private String desc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_type_movie", unique = true)
    private Movie movie;

    public TypesOfMovie() {
    }

    public Integer getId_type_movie() {
        return id_type_movie;
    }

    public void setId_type_movie(int id_type_movie) {
        this.id_type_movie = id_type_movie;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "TypesOfMovie{" +
                "id_type_movie=" + id_type_movie +
                ", desc='" + desc + '\'' +
                ", movie=" + movie +
                '}';
    }
}
