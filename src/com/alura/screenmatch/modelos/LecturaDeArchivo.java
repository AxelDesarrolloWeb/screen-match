package com.alura.screenmatch.modelos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecturaDeArchivo {
    public static void main(String[] args) {
        try {
            File archivo = new File("archivo.json");
            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(linea);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado!");
        }
    }

    /*
    * FileWriter escritura = new FileWriter("peliculas.txt");
        escritura.write(miTitulo.toString());
        escritura.close();


        // System.out.println("Titulo :" + miTitulo.getNombre());

//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenAccept(System.out::println)
//                .join();
        // File file1 = new File("C://miArchivo.txt");

        String directoryPath = "C:\\Users\\alvax\\Desktop";
        String filePath = directoryPath + "\\desdeJava.txt";
        File file = new File(filePath);

        FileReader reader = new FileReader(file);

        int data = reader.read();
        while (data != -1) {
            System.out.print((char) data);
            data = reader.read();
        }
        reader.close();

        FileWriter writer = new FileWriter(file);
        writer.write("Hola mundo!");
        writer.close();
    * */
}
