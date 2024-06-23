package uy.edu.um.prog2.adt;

import uy.edu.um.prog2.adt.Hash.MyHashImpl;
import uy.edu.um.prog2.adt.LinkedList.MyLinkedListImpl;
import uy.edu.um.prog2.adt.LinkedList.MyList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Instanciamos los TADs necesarios
        MyHashImpl<String, Artist> artists = new MyHashImpl<>();
        MyHashImpl<String, Country> countries = new MyHashImpl<>();
        MyHashImpl<String, Song> songs = new MyHashImpl<>();
        CargaDeDatos cargaDeDatos = new CargaDeDatos();
        cargaDeDatos.cargaSongs(songs,artists,countries);
        Consultas consulta = new Consultas();

        boolean exit = false;
        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Top 10 canciones en un país en un día dado");
            System.out.println("2. Top 5 canciones que aparecen en más top 50 en un día dado");
            System.out.println("3. Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado");
            System.out.println("4. Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada");
            System.out.println("5. Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas");
            System.out.println("6. Salir");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (option) {
                case 1:
                    System.out.print("Ingrese el país: ");
                    String country = scanner.nextLine();
                    if (!countries.containsKey(country)) {
                        throw new RuntimeException("El país " + country + " no existe en la base de datos.");
                    }
                    System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
                    try {
                        LocalDate date = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
                        MyList<Song> top10Songs = consulta.consulta1(countries, country, date);
                        System.out.println("Top 10 canciones en " + country + " el " + date + ":");
                        for (int i = 0; i < top10Songs.size(); i++) {
                            Song song = top10Songs.get(i);
                            System.out.println((i + 1) + ". " + song.getTitle() + " - " + song.getArtist() + " (Puesto: " + song.getPosition() + ")");
                        }
                    } catch (DateTimeParseException e) {
                        throw new RuntimeException("La fecha ingresada es inválida. Debe ser en formato YYYY-MM-DD.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
                    try {
                        LocalDate date = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
                        List<String> top5Songs = consulta.consulta2( date, songs);
                        System.out.println("Top 5 canciones que aparecen en más top 50 el " + date + ":");
                        for (int i = 0; i < top5Songs.size(); i++) {
                            String song = top5Songs.get(i);
                            System.out.println((i + 1) + ". " + song);
                        }
                    } catch (DateTimeParseException e) {
                        throw new RuntimeException("La fecha ingresada es inválida. Debe ser en formato YYYY-MM-DD.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
                    try {
                        LocalDate startDate = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
                        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
                        try {
                            LocalDate endDate = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
                            if (startDate.isAfter(endDate)) {
                                throw new RuntimeException("La fecha de inicio no puede ser posterior a la fecha de fin.");
                            }
                            MyLinkedListImpl<String> top7Artists = consulta.consulta3(artists, countries, startDate, endDate);
                            System.out.println("Top 7 artistas que más aparecen en los top 50 del " + startDate + " al " + endDate + ":");
                            for (int i = 0; i < top7Artists.size(); i++) {
                                String artist = top7Artists.get(i);
                                System.out.println((i + 1) + ". " + artist);
                            }
                        } catch (DateTimeParseException e) {
                            throw new RuntimeException("La fecha de fin ingresada es inválida. Debe ser en formato YYYY-MM-DD.");
                        }
                    } catch (DateTimeParseException e) {
                        throw new RuntimeException("La fecha de inicio ingresada es inválida. Debe ser en formato YYYY-MM-DD.");
                    }


                case 4:
                    System.out.print("Ingrese el nombre del artista: ");
                    String artistName = scanner.nextLine();
                    if (!artists.containsKey(artistName)) {
                        throw new RuntimeException("El artista " + artistName + " no existe en la base de datos.");
                    }
                    System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
                    try {
                        LocalDate date = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
                        int count = consulta.consulta4(artistName, date, countries, artists);
                        System.out.println("El artista " + artistName + " aparece " + count + " veces en el top 50 el " + date + ".");
                    } catch (DateTimeParseException e) {
                        throw new RuntimeException("La fecha ingresada es inválida. Debe ser en formato YYYY-MM-DD.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
                    try {
                        LocalDate startDate = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
                        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
                        try {
                            LocalDate endDate = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
                            if (startDate.isAfter(endDate)) {
                                throw new RuntimeException("La fecha de inicio no puede ser posterior a la fecha de fin.");
                            }
                            System.out.print("Ingrese el tempo mínimo: ");
                            try {
                                double tempoMin = scanner.nextDouble();
                                System.out.print("Ingrese el tempo máximo: ");
                                try {
                                    double tempoMax = scanner.nextDouble();
                                    if (tempoMin > tempoMax) {
                                        throw new RuntimeException("El tempo mínimo no puede ser mayor que el tempo máximo.");
                                    }
                                    int count = consulta.consulta5(songs, tempoMin, tempoMax, startDate, endDate);
                                    System.out.println("Hay " + count + " canciones con un tempo entre " + tempoMin + " y " + tempoMax + " en el rango de fechas del " + startDate + " al " + endDate + ".");
                                } catch (InputMismatchException e) {
                                    throw new RuntimeException("El tempo máximo ingresado es inválido. Debe ser un número decimal.");
                                }
                            } catch (InputMismatchException e) {
                                throw new RuntimeException("El tempo mínimo ingresado es inválido. Debe ser un número decimal.");
                            }
                        } catch (DateTimeParseException e) {
                            throw new RuntimeException("La fecha de fin ingresada es inválida. Debe ser en formato YYYY-MM-DD.");
                        }
                    } catch (DateTimeParseException e) {
                        throw new RuntimeException("La fecha de inicio ingresada es inválida. Debe ser en formato YYYY-MM-DD.");
                    }
                    break;

                case 6:
                    exit = true;
                    System.out.println("Saliendo del programa.");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 7.");
                    break;
            }
        }

        scanner.close();
    }
    public static void measureExecutionTime() {
        int numIterations = 100; // número de iteraciones para calcular el tiempo de ejecución promedio
        long totalTime = 0;

        for (int i = 0; i < numIterations; i++) {
            long startTime = System.currentTimeMillis();
            // Código a medir

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            totalTime += executionTime;
        }

        long averageExecutionTime = totalTime / numIterations;
        System.out.println("Tiempo de ejecución promedio: " + averageExecutionTime + " milliseconds");
    }
}

