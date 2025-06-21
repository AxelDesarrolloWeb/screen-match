package com.alvax.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosTemporadas(
        @JsonAlias("Season") Integer numero,
        @JsonAlias("Title") String tituloSerie, // Agregar campo adicional
        @JsonAlias("Episodes") List<DatosEpisodio> episodios
) {
    @Override
    public String toString() {
        return "Temporada " + numero +
                " - " + tituloSerie +
                " - Episodios: " + (episodios != null ? episodios.size() : 0);
    }
}
