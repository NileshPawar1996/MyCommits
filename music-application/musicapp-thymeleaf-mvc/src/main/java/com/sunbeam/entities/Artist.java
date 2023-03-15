package com.sunbeam.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="artist")
public class Artist {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String thumbnail;
	private String type;
	@ManyToMany(mappedBy = "artistList")
	private List<Song> songList;
	
	public Artist() {
		this.songList = new ArrayList<Song>();
	}

	public Artist(int id, String firstName, String lastName, String thumbnail, String type) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.thumbnail = thumbnail;
		this.type = type;
		this.songList = new ArrayList<Song>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}
	
	public void addSong(Song song) {
		this.songList.add(song);
		song.addArtist(this);
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", thumbnail=" + thumbnail
				+ ", type=" + type + "]";
	}
}

