package com.sunbeam.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Song;

public interface SongDao extends JpaRepository<Song, Integer> {
	List<Song> findByTitle(String string);
}
