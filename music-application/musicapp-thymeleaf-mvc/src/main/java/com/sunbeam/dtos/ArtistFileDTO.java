package com.sunbeam.dtos;

import org.springframework.web.multipart.MultipartFile;

public class ArtistFileDTO {
	private int id;
	private String firstName;
	private String lastName;
	private MultipartFile thumbnail;
	private String type;
	
	public ArtistFileDTO() {
	}

	public ArtistFileDTO(int id, String firstName, String lastName, MultipartFile thumbnail, String type) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.thumbnail = thumbnail;
		this.type = type;
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

	public MultipartFile getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", thumbnail=" + thumbnail
				+ ", type=" + type + "]";
	}
}

