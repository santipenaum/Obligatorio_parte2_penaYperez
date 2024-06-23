package uy.edu.um.prog2.adt;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String name;
    private List<Song> songs;

    // Constructor
    public Artist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    // Methods
    public void addSong(Song song) {
        songs.add(song);
    }
}