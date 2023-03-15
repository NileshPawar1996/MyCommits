package com.sunbeam.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunbeam.entities.Album;
import com.sunbeam.entities.Artist;

public interface ArtistDao extends JpaRepository<Artist, Integer> {
	List<Artist> findByFirstName(String firstName);
	List<Artist> findByLastName(String lastName);
	List<Artist> findByFirstNameAndLastName(String firstName, String lastName);
	@Query("SELECT DISTINCT s.album FROM Song s INNER JOIN s.artistList ar WHERE ar.id=:p_artistId")
	List<Album> findAlbums(@Param("p_artistId") int artistId);
}
