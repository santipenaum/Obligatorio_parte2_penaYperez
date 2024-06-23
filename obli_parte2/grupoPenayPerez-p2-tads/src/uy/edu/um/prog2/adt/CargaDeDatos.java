package uy.edu.um.prog2.adt;

import uy.edu.um.prog2.adt.Hash.MyHashImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class CargaDeDatos {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void cargaSongs(MyHashImpl<String, Song> songs, MyHashImpl<String,Artist> artists, MyHashImpl<String, Country> countrys) throws IOException {
        long tiempoInicio = System.currentTimeMillis();
        long memoriaInicio = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        String songsFile = "D:\\Administrador\\Desktop\\universal_top_spotify_songs.csv";

        try  {
            BufferedReader br = new BufferedReader(new FileReader(songsFile));
            String line = br.readLine();  // Leer la primera línea (posiblemente encabezados)
            line = br.readLine();  // Saltar la línea de encabezados

            while ((line=br.readLine()) != null) {

                line = line.replace("\"\"", "\"").replaceAll("^\"|\"$", "");
                String[] datos = line.split(",\"");
                for (int i = 0; i < datos.length; i++) {
                        // Remove double quotes from each element
                    datos[i] = datos[i].replace("\"", "");
                    datos[i] = cleanField(datos[i]);}  // Usar cleanField para limpiar cada campo


                // Verifica que hay suficientes columnas en la línea
                if (datos.length == 25) {
                    try {

                        // Crear objeto Song con los datos obtenidos
                        Song cancion = new Song();
                        cancion.setTitle(datos[1]);
                        cancion.setArtist(datos[2].split(","));
                        cancion.setPosition(Integer.parseInt(datos[3]));
                        cancion.setCountry(datos[6]);
                        cancion.setDate(LocalDate.parse(datos[7], DATE_FORMATTER));
                        cancion.setTempo(Double.parseDouble(datos[23]));


                        // Almacenar la canción en la estructura de datos songs
                        songs.put(datos[0], cancion);  // Usar spotify_id como clave única

                        for (int j = 0; j < cancion.getArtist().length; j++) {
                            Artist artista = artists.get(cancion.getArtist()[j]);
                            if (artista == null) {
                                artista = new Artist(cancion.getArtist()[j]);
                                artists.put(cancion.getArtist()[j], artista);
                                artista.addSong(cancion);
                            }
                            else {
                                artista.addSong(cancion);
                            }

                        }

                        Country pais = countrys.get(datos[6]);
                        if(pais == null){
                            pais = new Country(datos[6]);
                            countrys.put(datos[6],pais);
                            pais.addSong(cancion);
                        }else{
                            pais.addSong(cancion);
                        }

                    } catch (NumberFormatException | DateTimeParseException e) {
                        System.err.println("Error al parsear los datos: " + e.getMessage() + " en la línea: " + line);
                    }
                }
                if (datos.length == 26) {
                    try {

                        // Crear objeto Song con los datos obtenidos
                        Song cancion = new Song();
                        cancion.setTitle(datos[1]);
                        cancion.setArtist(datos[3].split(","));
                        cancion.setPosition(Integer.parseInt(datos[4]));
                        cancion.setCountry(datos[7]);
                        cancion.setDate(LocalDate.parse(datos[8], DATE_FORMATTER));
                        cancion.setTempo(Double.parseDouble(datos[24]));


                        // Almacenar la canción en la estructura de datos songs
                        songs.put(datos[0], cancion);  // Usar spotify_id como clave única

                        for (int j = 0; j < cancion.getArtist().length; j++) {
                            Artist artista = artists.get(cancion.getArtist()[j]);
                            if (artista == null) {
                                artista = new Artist(cancion.getArtist()[j]);
                                artists.put(cancion.getArtist()[j], artista);
                                artista.addSong(cancion);
                            }
                            else {
                                artista.addSong(cancion);
                            }

                        }

                        Country pais = countrys.get(datos[7]);
                        if(pais == null){
                            pais = new Country(datos[7]);
                            countrys.put(datos[7],pais);
                            pais.addSong(cancion);
                        }else{
                            pais.addSong(cancion);
                        }

                    } catch (NumberFormatException | DateTimeParseException e) {
                        System.err.println("Error al parsear los datos: " + e.getMessage() + " en la línea: " + line);
                    }
                }

                }

                line = br.readLine();  // Leer la siguiente línea
            } catch (IOException e) {
            System.err.println("Error al cargar los datos de canciones: " + e.getMessage());
        }

        long tiempoFin = System.currentTimeMillis();
        long tiempo = tiempoFin - tiempoInicio;
        long memoriaFin = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long memoriaUsada = memoriaFin - memoriaInicio;
        System.out.print("Tiempo de ejecucion de la consulta: " + (tiempoFin - tiempoInicio) + " ms\n");
        System.out.print("Memoria usada por la consulta: " + memoriaUsada + " bytes\n");
    }
    private String cleanField(String field) {
        return field.replaceAll("^\"|\"$", "").trim();  // Quitar comillas dobles al inicio y al final y eliminar espacios en blanco
    }

}


