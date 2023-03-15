package com.sunbeam.dtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sunbeam.entities.Album;
import com.sunbeam.entities.Artist;

public class SongFileDTO {
	private int id;
	private String title;
	private MultipartFile file;
	private String duration;
	private Album album;
	private List<Artist> artistList;

	public SongFileDTO() {
		this.artistList = new ArrayList<Artist>();
	}
	public SongFileDTO(int id, String title, MultipartFile songFile, String duration) {
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
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
