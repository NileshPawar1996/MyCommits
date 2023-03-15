package com.sunbeam.entities;

import javax.persistence.Id;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoFile {
	@Id
	private String id;
	private Binary file;
	public MongoFile() {
	}
	public MongoFile(String id, Binary file) {
		this.id = id;
		this.file = file;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Binary getFile() {
		return file;
	}
	public void setFile(Binary file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "MongoFile [id=" + id + ", file=" + file + "]";
	}
	
}
