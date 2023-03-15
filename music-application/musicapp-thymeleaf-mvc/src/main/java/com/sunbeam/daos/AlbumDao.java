package com.sunbeam.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunbeam.entities.Album;
import com.sunbeam.entities.Artist;

public interface AlbumDao extends JpaRepository<Album, Integer> {
	Album findByTitle(String title);
	
	@Query("SELECT s.artistList FROM Song s INNER JOIN s.album al WHERE al.id=:p_albumId")
	List<Artist> findArtists(@Param("p_albumId") int albumId);
}
