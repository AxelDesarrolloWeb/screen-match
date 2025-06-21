package com.alvax.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate fechaDelanzamiento;

    public Episodio(Integer numero, DatosEpisodio d) {
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numeroEpisodio = d.numeroEpisodio();

        try {
            this.evaluacion = Double.valueOf(d.evaluacion());
        } catch (NumberFormatException e) {
            this.evaluacion = 0.0;
        }

        // Manejo de fechas inválidas
        if (d.fechaDeLanzamiento() != null && !d.fechaDeLanzamiento().equalsIgnoreCase("N/A")) {
            try {
                this.fechaDelanzamiento = LocalDate.parse(d.fechaDeLanzamiento());
            } catch (DateTimeParseException e) {
                this.fechaDelanzamiento = null;
            }
        } else {
            this.fechaDelanzamiento = null;
        }
    }

//    public Episodio(Integer numero, DatosEpisodio d) {
//    }
//
//    public Episodio(Integer numero, DatosEpisodio d) {
//    }
//
//    public Episodio(Integer numero, DatosEpisodio d) {
//    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaDelanzamiento() {
        return fechaDelanzamiento;
    }

    public void setFechaDelanzamiento(LocalDate fechaDelanzamiento) {
        this.fechaDelanzamiento = fechaDelanzamiento;
    }

    @Override
    public String toString() {
        return "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", evaluacion=" + evaluacion +
                ", fechaDelanzamiento=" + (fechaDelanzamiento != null ? fechaDelanzamiento.toString() : "N/A");
    }
}