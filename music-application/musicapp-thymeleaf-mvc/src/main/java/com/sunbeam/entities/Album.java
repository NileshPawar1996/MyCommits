package com.sunbeam.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "album")
public class Album {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(unique = true)
	private String title;
	private String thumbnail;
	@OneToMany(mappedBy = "album")
	private List<Song> songList;

	public Album() {
		this.songList = new ArrayList<Song>();
	}

	public Album(int id, String title, String thumbnail) {
		this.id = id;
		this.title = title;
		this.thumbnail = thumbnail;
		this.songList = new ArrayList<Song>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}
	
	public void addSong(Song song) {
		this.songList.add(song);
		song.setAlbum(this);
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", thumbnail=" + thumbnail + "]";
	}
}
