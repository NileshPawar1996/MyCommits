package com.sunbeam.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sunbeam.daos.AlbumDao;
import com.sunbeam.daos.ArtistDao;
import com.sunbeam.daos.SongDao;
import com.sunbeam.dtos.PlayQueue;
import com.sunbeam.entities.Album;
import com.sunbeam.entities.Artist;
import com.sunbeam.entities.Song;

@Service
public class MusicServiceImpl {
	@Autowired
	private AlbumDao albumDao;
	@Autowired
	private ArtistDao artistDao;
	@Autowired
	private SongDao songDao;

	public Artist saveArtist(Artist artist) {
		return artistDao.save(artist);
	}
	
	public List<Artist> findAllArtist() {
		return artistDao.findAll();
	}
	
	public Artist findArtistById(int artistId) {
		return artistDao.findById(artistId).orElse(null);
	}

	public List<Artist> findArtistByName(String firstName, String lastName) {
		List<Artist> fnameList = artistDao.findByFirstName(firstName);
		List<Artist> lnameList = artistDao.findByLastName(lastName);
		fnameList.addAll(lnameList);
		return fnameList;
	}
	
	public List<Artist> findArtistByFullName(String firstName, String lastName) {
		return artistDao.findByFirstNameAndLastName(firstName, lastName);
	}
	
	public List<Artist> findSongArtists(int songId) {
		Optional<Song> song = songDao.findById(songId);
		if(song.isPresent())
			return song.get().getArtistList();
		return null;
	}
	
	public List<Artist> findAlbumArtists(int albumId) {
		return albumDao.findArtists(albumId);
	}
	
	public Album saveAlbum(Album album) {
		return albumDao.save(album);
	}
	
	public List<Album> findAllAlbum() {
		return albumDao.findAll();
	}

	public Album findAlbumById(int albumId) {
		return albumDao.findById(albumId).orElse(null);
	}
	
	public Album findAlbumByTitle(String title) {
		return albumDao.findByTitle(title);
	}
	
	public Album findSongAlbum(int songId) {
		Optional<Song> song = songDao.findById(songId);
		if(song.isPresent())
			return song.get().getAlbum();
		return null;
	}
	
	public List<Album> findArtistAlbums(int artistId) {
		return artistDao.findAlbums(artistId);
	}

	public Song saveSong(Song song) {
		return songDao.save(song);
	}
	
	public Song findSongById(int songId) {
		return songDao.findById(songId).orElse(null);
	}
	
	public List<Song> findAllSong() {
		return songDao.findAll();
	}
	
	public List<Song> findAlbumSong(int albumId) {
		Optional<Album> album = albumDao.findById(albumId);
		if(album.isPresent())
			return album.get().getSongList();
		return null;
	}
	
	public List<Song> findArtistSong(int artistId) {
		Optional<Artist> artist = artistDao.findById(artistId);
		if(artist.isPresent())
			return artist.get().getSongList();
		return null;
	}

	public List<Song> findSongByTitle(String title) {
		return songDao.findByTitle(title);
	}

	public List<Song> findAllSongById(Collection<Integer> songIds) {
		return songDao.findAllById(songIds);
	}

	public Page<Song> findAllSong(Pageable page) {
		return songDao.findAll(page);
	}
}



