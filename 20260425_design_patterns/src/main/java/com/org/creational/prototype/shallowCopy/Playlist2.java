package com.org.creational.prototype.shallowCopy;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用clone方法
 */
@Data
public class Playlist2 implements Serializable, Cloneable {
    private Long id;
    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist2() {
    }

    public void add(Song song){
        songs.add(song);
    }

    public Playlist2(Playlist2 sourcePlayList) {
        this.id = sourcePlayList.getId();
        this.name = sourcePlayList.getName();
        this.songs = sourcePlayList.getSongs();
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Playlist2 playlist = new Playlist2();
        playlist.setId(1L);
        playlist.setName("杰伦");
        playlist.add(new Song("稻香","杰伦"));
        playlist.add(new Song("迷迭香","杰伦"));
        playlist.add(new Song("七里香","杰伦"));

        // 浅拷贝后的最喜爱的专辑
        Playlist2 favouriteList = (Playlist2) playlist.clone();
        System.out.println(favouriteList);
    }
}