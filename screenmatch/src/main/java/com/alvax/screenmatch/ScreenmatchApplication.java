package com.alvax.screenmatch;

import com.alvax.screenmatch.model.DatosEpisodio;
import com.alvax.screenmatch.model.DatosSerie;
import com.alvax.screenmatch.model.DatosTemporadas;
// import com.alvax.screenmatch.principal.EjemploStreams;
import com.alvax.screenmatch.principal.Principal;
import com.alvax.screenmatch.service.ConsumoAPI;
import com.alvax.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hola mundo desde Spring!!!");

		var consumoApi = new ConsumoAPI();
		String url = "http://www.omdbapi.com/?t=Game+of+thrones&apikey=9ddb6ba8";
		var json = consumoApi.obtenerdatos(url);
		System.out.println("JSON recibido:\n" + json);

		ConvierteDatos conversor = new ConvierteDatos();
		var datos = conversor.obtenerDatos(json, DatosSerie.class);

		// Imprimir los datos convertidos
		System.out.println("\nDatos serie:");
		imprimirDatosSerie(datos);

		json = consumoApi.obtenerdatos("http://www.omdbapi.com/?t=Game+of+thrones&Season=1&Episode=1&apikey=9ddb6ba8");
		DatosEpisodio episodios = conversor.obtenerDatos(json, DatosEpisodio.class);
		System.out.println(episodios);

		Principal principal = new Principal();
		principal.muestraElMenu();

//		EjemploStreams ejemploStreams = new EjemploStreams();
//		ejemploStreams.muestraEjemplo();

	}

	private void imprimirDatosSerie(DatosSerie datos) {
		System.out.println("Título: " + datos.titulo());
		System.out.println("Año: " + datos.anio());
		System.out.println("Clasificación: " + datos.clasificacion());
		System.out.println("Lanzamiento: " + datos.fechaLanzamiento());
		System.out.println("Duración: " + datos.duracion());
		System.out.println("Género: " + datos.genero());
		System.out.println("Director: " + datos.director());
		System.out.println("Escritores: " + datos.escritores());
		System.out.println("Actores: " + datos.actores());
		System.out.println("Sinopsis: " + datos.sinopsis());
		System.out.println("Idioma: " + datos.idioma());
		System.out.println("País: " + datos.pais());
		System.out.println("Premios: " + datos.premios());
		System.out.println("Poster: " + datos.poster());
		System.out.println("Evaluaciones:");
		datos.evaluaciones().forEach(e ->
				System.out.println("  - " + e.fuente() + ": " + e.valor()));
		System.out.println("Metascore: " + datos.metascore());
		System.out.println("IMDb Rating: " + datos.imdbRating());
		System.out.println("IMDb Votos: " + datos.imdbVotos());
		System.out.println("IMDb ID: " + datos.imdbId());
		System.out.println("Tipo: " + datos.tipo());
		System.out.println("Temporadas: " + datos.totalTemporadas());
		System.out.println("Respuesta: " + datos.respuesta());


	}
}