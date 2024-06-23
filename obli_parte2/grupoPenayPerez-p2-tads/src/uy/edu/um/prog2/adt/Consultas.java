package uy.edu.um.prog2.adt;

import uy.edu.um.prog2.adt.BinaryTree.SearchBinaryTreeImpl;
import uy.edu.um.prog2.adt.Hash.MyHashImpl;
import uy.edu.um.prog2.adt.LinkedList.MyLinkedListImpl;
import uy.edu.um.prog2.adt.LinkedList.MyList;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class Consultas {

    public MyLinkedListImpl<Song> consulta1(MyHashImpl<String, Country> countries, String countryName, LocalDate date) {
        long tiempoInicio = System.currentTimeMillis();
        long memoriaInicio = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        MyHashImpl<Integer, Song> top50SongsListPaisPorFecha = new MyHashImpl<>();
        MyLinkedListImpl<Song> top10Songs = new MyLinkedListImpl<>();
        Country pais = countries.get(countryName);
        List<Song> top50Pais = pais.getTopSongs();
        for (int i = 0; i < top50Pais.size(); i++) {
            Song cancion = top50Pais.get(i);
            if (cancion.getDate().equals(date)) {
                top50SongsListPaisPorFecha.put(cancion.getPosition(),cancion);
            }
        }
        List<Integer> keys = ordenarMenAMayor(top50SongsListPaisPorFecha.keys());
        for (int i = 0; i < 10; i++) {
                int key = keys.get(i);
                Song song = top50SongsListPaisPorFecha.get(key);
                top10Songs.add(song);

            }
        long tiempoFin = System.currentTimeMillis();
        long tiempo = tiempoFin - tiempoInicio;
        long memoriaFin = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long memoriaUsada = memoriaFin - memoriaInicio;
        System.out.print("Tiempo de ejecucion de la consulta: " + (tiempoFin - tiempoInicio) + " ms\n");
        System.out.print("Memoria usada por la consulta: " + memoriaUsada + " bytes\n");
        return top10Songs;
        }


    public List<String> consulta2(LocalDate date, MyHashImpl<String, Song> songs) {
        SearchBinaryTreeImpl<Song> songTree = new SearchBinaryTreeImpl<>();

        // Agrega todas las canciones al árbol binario
        for (Song song : songs.values()) {
            songTree.add(song);
        }

        // Filtra las canciones que se ajustan a la fecha específica
        List<Song> filteredSongs = songTree.inOrder().stream()
                .filter(song -> song.getDate().isEqual(date))
                .collect(Collectors.toList());

        // Crea un mapa para contar la frecuencia de cada canción
        MyHashImpl<String, Integer> songFrequency = new MyHashImpl<>();
        for (Song song : filteredSongs) {
            String title = song.getTitle();
            if (songFrequency.containsKey(title)) {
                songFrequency.put(title, songFrequency.get(title) + 1);
            } else {
                songFrequency.put(title, 1);
            }
        }

        // Ordena las canciones por frecuencia de aparición
        List<String> sortedSongs = new ArrayList<>();
        List<Integer> frequencies = new ArrayList<>();

// Iteramos sobre el mapa songFrequency
        for (String key : songFrequency.keys()) {
            int value = songFrequency.get(key);
            // Buscamos la posición adecuada para insertar la canción en la lista ordenada
            int i = 0;
            while (i < frequencies.size() && frequencies.get(i) > value) {
                i++;
            }
            frequencies.add(i, value);
            sortedSongs.add(i, key);
        }

// Seleccionamos las top 5 canciones
        List<String> top5Songs = new ArrayList<>();
        for (int i = 0; i < 5 && i < sortedSongs.size(); i++) {
            top5Songs.add(sortedSongs.get(i));
        }

        // Selecciona las top 5 canciones
        List<String> top5SongsFinal = new ArrayList<>();
        for (int i = 0; i < 5 && i < sortedSongs.size(); i++) {
            top5SongsFinal.add(sortedSongs.get(i));
        }

        return top5SongsFinal;
    }

    public MyLinkedListImpl<String> consulta3(MyHashImpl<String,Artist> artists, MyHashImpl<String,Country> countries, LocalDate mindate, LocalDate maxdate){
        long tiempoInicio = System.currentTimeMillis();
        long memoriaInicio = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        MyHashImpl<Integer,MyLinkedListImpl<String>> artistasConCantidad = new MyHashImpl<>();
        MyLinkedListImpl<String> lista = new MyLinkedListImpl<>();
        MyLinkedListImpl<String> listaFinal = new MyLinkedListImpl<>();
        for (int i = 0; i < artists.values().size(); i++) {
            Artist artista = artists.values().get(i);
            int count = 0;
            String nombre = artista.getName();
            for (int j = 0; j < artista.getSongs().size(); j++) {
                Song songA = artista.getSongs().get(j);
                if(songA.getDate().isAfter(mindate) && songA.getDate().isBefore(maxdate) ){
                    for (int k = 0; k < countries.values().size(); k++) {
                        Country country = countries.values().get(k);
                        for (int l = 0; l < country.getTopSongs().size(); l++) {
                            if(songA.getDate().isAfter(mindate) && songA.getDate().isBefore(maxdate) ) {
                                Song songC = country.getTopSongs().get(l);
                                if (songC.estaElArtista(nombre) == true) {
                                    count++;
                                }
                            }

                        }

                    }
                }

            }
            if (artistasConCantidad.containsKey(count)) {
                artistasConCantidad.get(count).add(nombre);
            } else {
                MyLinkedListImpl<String> list = new MyLinkedListImpl<>();
                list.add(nombre);
                artistasConCantidad.put(count, list);
            }
        }

        List<Integer> keys = ordenarMayAMen(artistasConCantidad.keys());
        while (lista.size() < 7){
            for (int i = 0; i < 7; i++) {
                int key = keys.get(i);
                MyLinkedListImpl<String> titulos = artistasConCantidad.get(key);
                for (int j = 0; j < titulos.size(); j++) {
                    lista.add(titulos.get(i));
                }
            }
        }
        if (lista.size() > 7) {
            for (int i = 0; i < 7; i++) {
                listaFinal.add(lista.get(i));
            }
            return listaFinal;
        }
        long tiempoFin = System.currentTimeMillis();
        long tiempo = tiempoFin - tiempoInicio;
        long memoriaFin = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long memoriaUsada = memoriaFin - memoriaInicio;
        System.out.print("Tiempo de ejecucion de la consulta: " + (tiempoFin - tiempoInicio) + " ms\n");
        System.out.print("Memoria usada por la consulta: " + memoriaUsada + " bytes\n");
        return lista;
    }





    public int consulta4(String artistname, LocalDate date, MyHashImpl<String, Country> countries, MyHashImpl<String,Artist> artistas){
        long tiempoInicio = System.currentTimeMillis();
        long memoriaInicio = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        int count = 0;
        Artist artista = artistas.get(artistname);
        List<Song> canciones = artista.getSongs();
        for (int i = 0; i < canciones.size() ; i++) {
            Song song = canciones.get(i);
            if (song.estaElArtista(artistname) == true && song.getDate().equals(date)){
                for (int j = 0; j < countries.values().size(); j++) {
                    Country pais = countries.values().get(i);
                    if (song.getCountry() == pais.getName()){
                        count ++;
                    }

                }
            }

        }

        long tiempoFin = System.currentTimeMillis();
        long tiempo = tiempoFin - tiempoInicio;
        long memoriaFin = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long memoriaUsada = memoriaFin - memoriaInicio;
        System.out.print("Tiempo de ejecucion de la consulta: " + (tiempoFin - tiempoInicio) + " ms\n");
        System.out.print("Memoria usada por la consulta: " + memoriaUsada + " bytes\n");
        return count;

    }


    public int consulta5(MyHashImpl<String, Song> songs, double minTempo, double maxTempo, LocalDate minDate, LocalDate maxDate) {
        long tiempoInicio = System.currentTimeMillis();
        long memoriaInicio = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        int count = 0;
        for (int i = 0; i < songs.values().size(); i++) {
            Song song = songs.values().get(i);
            if (song.getTempo() >= minTempo && song.getTempo() <= maxTempo && song.getDate().isAfter(minDate) && song.getDate().isBefore(maxDate)){
                count++;
            }
        }
        long tiempoFin = System.currentTimeMillis();
        long tiempo = tiempoFin - tiempoInicio;
        long memoriaFin = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long memoriaUsada = memoriaFin - memoriaInicio;
        System.out.print("Tiempo de ejecucion de la consulta: " + (tiempoFin - tiempoInicio) + " ms\n");
        System.out.print("Memoria usada por la consulta: " + memoriaUsada + " bytes\n");
        return count;
    }

    public static List<Integer> ordenarMenAMayor(List<Integer> listaAOrdenar) {
        List<Integer> listaOrdenada = new ArrayList<>();
        while (listaAOrdenar.size() > 0) {
            int menorElemento = listaAOrdenar.get(0);

            for (int i = 1; i < listaAOrdenar.size(); i++) {
                int temp = listaAOrdenar.get(i);
                if (temp < menorElemento) {
                    menorElemento = temp;
                }
            }
            listaOrdenada.add(menorElemento);
            listaAOrdenar.remove((Integer) menorElemento);
        }
        return listaOrdenada;

    }

    public static List<Integer> ordenarMayAMen(List<Integer> listaAOrdenar) {
        List<Integer> listaOrdenada = new ArrayList<>();
        List<Integer> listaTemporal = new ArrayList<>(listaAOrdenar);
        while (listaTemporal.size() > 0) {
            int mayorElemento = listaTemporal.get(0);
            for (int i = 1; i < listaTemporal.size(); i++) {
                int temp = listaTemporal.get(i);
                if (temp > mayorElemento) {
                    mayorElemento = temp;
                }
            }
            listaOrdenada.add(mayorElemento);
            listaTemporal.remove((Integer) mayorElemento);
        }
        return listaOrdenada;
    }
}
