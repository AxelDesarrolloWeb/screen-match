package com.alvax.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Year") String anio,
        @JsonAlias("Rated") String clasificacion,
        @JsonAlias("Released") String fechaLanzamiento,
        @JsonAlias("Runtime") String duracion,
        @JsonAlias("Genre") String genero,
        @JsonAlias("Director") String director,
        @JsonAlias("Writer") String escritores,
        @JsonAlias("Actors") String actores,
        @JsonAlias("Plot") String sinopsis,
        @JsonAlias("Language") String idioma,
        @JsonAlias("Country") String pais,
        @JsonAlias("Awards") String premios,
        @JsonAlias("Poster") String poster,
        @JsonAlias("Ratings") List<Rating> evaluaciones,
        @JsonAlias("Metascore") String metascore,
        @JsonAlias("imdbRating") String imdbRating,
        @JsonAlias("imdbVotes") String imdbVotos,
        @JsonAlias("imdbID") String imdbId,
        @JsonAlias("Type") String tipo,
        @JsonAlias("totalSeasons") Integer totalTemporadas,
        @JsonAlias("Response") String respuesta
) {
    public record Rating(
            @JsonAlias("Source") String fuente,
            @JsonAlias("Value") String valor
    ) {}
}