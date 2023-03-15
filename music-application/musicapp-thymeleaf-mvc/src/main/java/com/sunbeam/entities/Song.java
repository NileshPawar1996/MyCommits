package com.sunbeam.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "song")
public class Song {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String title;
	private String file;
	private String duration;
	@ManyToOne
	@JoinColumn(name="albumId")
	private Album album;
	@ManyToMany
	@JoinTable(name = "songartist",
		joinColumns = { @JoinColumn(name="songId") },
		inverseJoinColumns = { @JoinColumn(name="artistId") } )
	private List<Artist> artistList;

	public Song() {
		this.artistList = new ArrayList<Artist>();
	}
	public Song(int id, String title, String songFile, String duration) {
		this.id = id;
		this.title = title;
		this.file = songFile;
		this.duration = duration;
		this.artistList = new ArrayList<Artist>();
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
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public List<Artist> getArtistList() {
		return artistList;
	}
	public void setArtistList(List<Artist> artistList) {
		this.artistList = artistList;
	}
	
	@Override
	public String toString() {
		return "Song [id=" + id + ", title=" + title + ", file=" + file + ", duration=" + duration + ", album=" + album
				+ "]";
	}

	public void addArtist(Artist artist) {
		this.artistList.add(artist);
	}
}
