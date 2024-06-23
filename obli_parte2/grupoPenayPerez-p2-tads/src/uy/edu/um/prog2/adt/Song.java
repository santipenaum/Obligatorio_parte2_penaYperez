package uy.edu.um.prog2.adt;
import java.time.LocalDate;

public class Song  implements Comparable<Song> {
    private String title;
    private String[] artistas;
    private int position;
    private String country;
    private LocalDate date;
    private double tempo;


    // Constructor
    public Song() {
    }

    public Song(String title, String[] artists, int position, String country, LocalDate date, double tempo) {
        this.title = title;
        this.artistas = artists;
        this.position = position;
        this.country = country;
        this.date = date;
        this.tempo = tempo;
    }

    // Getters y Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getArtist() {
        return artistas;
    }

    public void setArtist(String[] artistas) {
        this.artistas = artistas;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public boolean estaElArtista(String artista) {
        boolean esta = false;
        for (int i = 0; i < artistas.length; i++) {
            if (artista.equalsIgnoreCase(artistas[i])) {
                esta = !esta;
            }
        }
        return esta;
    }



    public int compareTo(Song other) {
        // implement the comparison logic here
        // for example:
        return this.getTitle().compareTo(other.getTitle());
    }

}
