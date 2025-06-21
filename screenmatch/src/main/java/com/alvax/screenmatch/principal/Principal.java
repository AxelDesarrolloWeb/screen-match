package com.alvax.screenmatch.principal;

import com.alvax.screenmatch.model.DatosEpisodio;
import com.alvax.screenmatch.model.DatosSerie;
import com.alvax.screenmatch.model.DatosTemporadas;
import com.alvax.screenmatch.model.Episodio;
import com.alvax.screenmatch.service.ConsumoAPI;
import com.alvax.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();

    private final String API_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=9ddb6ba8";

    private final ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu() {
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();
        String nombreSerieEscapado = nombreSerie.replace(" ", "+");

        String url = API_BASE + nombreSerieEscapado + API_KEY;
        var json = consumoApi.obtenerdatos(url);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(json);

        // Convertir total de temporadas a entero
        int totalTemporadas;
        try {
            totalTemporadas = Integer.parseInt(String.valueOf(datos.totalTemporadas()));
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir total de temporadas: " + datos.totalTemporadas());
            totalTemporadas = 0;
        }

        // Busca los datos de todas las temporadas:
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= totalTemporadas; i++) {
            // URL CORREGIDA: agregar &Season=i
            String urlTemporada = API_BASE + nombreSerieEscapado + "&Season=" + i + API_KEY;
            json = consumoApi.obtenerdatos(urlTemporada);
            System.out.println("URL temporada: " + urlTemporada); // Para depuración

            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        temporadas.forEach(System.out::println);

        // Mostrar solo el titulo de los episodios para las temporadas
//        for (int i = 0; i < datos.totalTemporadas(); i++){
//            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//          }
        // Forma corta:
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));


        // Convertir todas las informaciones a una lista del tipo DatosEpisodio
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());


//        // Top 5 episodios
//        System.out.println("Top 5 episodios:");
//        datosEpisodios.stream()
//                .filter(e -> e.evaluacion() != null && !e.evaluacion().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primer filtro N/A " + e))
//                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
//                .peek(e -> System.out.println("Segundo filtro ordenación (M>m) " + e))
//                .map(e -> e.titulo().toUpperCase())
//                .peek(e -> System.out.println("Tercer filtro ordenación (m>M) " + e))
//                .limit(5)
//                .forEach(System.out::println);

        // Convirtiendo los datos a una lista de tipo episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());
        // episodios.forEach(System.out::println);


        // Busqueda de episodios a partir de x año
//        System.out.println("Por favor indica el año a partir del cual deseas ver los episodios");
//        var fecha = teclado.nextLine();
//        teclado.nextLine();

        //LocalDate fechaBusqueda = LocalDate.of(Integer.parseInt(fecha), 1, 1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getFechaDelanzamiento() != null && e.getFechaDelanzamiento().isAfter(fechaBusqueda))
//                .forEach(e -> System.out.println(
//                        "Temporada " + e.getTemporada() +
//                                "Episodio " + e.getTitulo() +
//                                "Fecha de lanzamiento " + e.getFechaDelanzamiento().format(dtf)
//                ));

//        // Busca episodios por pedazo del título
//        System.out.println("Por favor escriba el título del episodio que desea ver");
//        var pedazoTitulo = teclado.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
//                .findFirst();
//        if (episodioBuscado.isPresent()) {
//            System.out.println(" Episodio encontrado");
//            System.out.println("Los datos son: " + episodioBuscado.get());
//        } else {
//            System.out.println("Episodio no encontrado");
//        }

        Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.groupingBy(
                        Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)
                ));
        // Formatear y mostrar los resultados
        System.out.println("Evaluaciones por temporada:");
        evaluacionesPorTemporada.forEach((temporada, evaluacion) ->
                System.out.printf("Temporada %d: %.2f%n", temporada, evaluacion)
        );

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        System.out.println("Media de las evaluaciones: " + est.getAverage());
        System.out.println("Episodio mejor evaluado: " + est.getMax());
        System.out.println("Episodio peor evaluado: " + est.getMin());
        System.out.println("Cantidad " + est.getCount());

// Unos simples ejemplo *ignorar* :
//        List<List<String>> lista = List.of(
//                List.of("a", "b"),
//                List.of("c", "d")
//        );
//        Stream<String> stream = lista.stream()
//                .flatMap(Collection::stream);
//        stream.forEach(System.out::println);
//
//        List<Integer> numeros = List.of(1, 2, 3, 4, 5);
//        Optional<Integer> resultado = numeros.stream().reduce(Integer::sum);
//        resultado.ifPresent(System.out::println); // imprime 15
// FIN DE | "Unos simples ejemplo *ignorar*"


    }
}