package com.org.creational.prototype.shallowCopy;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 直接赋值
 */
@Data
public class Playlist {

    private Long id;
    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist() {
    }

    public void add(Song song){
        songs.add(song);
    }

    // 用于浅拷贝的构造器
    public Playlist(Playlist sourcePlayList) {
        this.id = sourcePlayList.getId();
        this.name = sourcePlayList.getName();
        this.songs = sourcePlayList.getSongs();
    }


    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.setId(1L);
        playlist.setName("杰伦");
        playlist.add(new Song("稻香","杰伦"));
        playlist.add(new Song("迷迭香","杰伦"));
        playlist.add(new Song("七里香","杰伦"));

        // 浅拷贝后的最喜爱的专辑
        Playlist favouriteList = new Playlist(playlist);
        favouriteList.add(new Song("曹操","林俊杰"));
        System.out.println(favouriteList);
    }
}
