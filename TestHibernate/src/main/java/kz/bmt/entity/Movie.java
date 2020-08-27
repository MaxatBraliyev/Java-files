package kz.bmt.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="movie")
public class Movie implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users_seq")
        @SequenceGenerator(name="users_seq",
                sequenceName="movie_id_seq", allocationSize=0)
        @Column(name="id", updatable=false, nullable=false)
        private int id;

        @Column(name="id_type_movie")
        private int idTypeMovie;

        @Column(name="name")
        private String name;

        @Column(name="name_desc")
        private String nameDesc;

        public Movie() {
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getIdTypeMovie() {
                return idTypeMovie;
        }

        public void setIdTypeMovie(int idTypeMovie) {
                this.idTypeMovie = idTypeMovie;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getNameDesc() {
                return nameDesc;
        }

        public void setNameDesc(String nameDesc) {
                this.nameDesc = nameDesc;
        }

        @Override
        public String toString() {
                return "Movie{" +
                        "id=" + id +
                        ", idTypeMovie='" + idTypeMovie + '\'' +
                        ", name='" + name + '\'' +
                        ", nameDesc='" + nameDesc + '\'' +
                        '}';
        }
}
