package com.sunbeam.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name = "account")
public class Account {
	@Id
	private int id;
	private String type;
	private Date start;
	private Date end;
	@PrimaryKeyJoinColumn
	@OneToOne
	@MapsId
	private User user;
	
	public Account() {
	}

	public Account(int id, String type, Date start, Date end) {
		this.id = id;
		this.type = type;
		this.start = start;
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", type=" + type + ", start=" + start + ", end=" + end + "]";
	}
}
