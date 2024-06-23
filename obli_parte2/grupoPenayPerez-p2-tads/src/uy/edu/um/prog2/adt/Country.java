package uy.edu.um.prog2.adt;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String name;
    private List<Song> topSongs;

    public Country(String name) {
        this.name = name;
        this.topSongs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getTopSongs() {
        return topSongs;
    }

    public void addSong(Song song) {
        this.topSongs.add(song);
    }
}
