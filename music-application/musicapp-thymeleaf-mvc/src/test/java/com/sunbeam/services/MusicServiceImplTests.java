package com.sunbeam.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

import com.sunbeam.entities.Album;
import com.sunbeam.entities.Artist;
import com.sunbeam.entities.Song;

@TestMethodOrder(OrderAnnotation.class)
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
class MusicServiceImplTests {
	@Autowired
	private MusicServiceImpl musicService;

	@Order(1)
	@Test
	void testSaveArtist() {
		Artist artist = new Artist(0, "Mukesh", "", "mukesh.png", "Singer");
		musicService.saveArtist(artist);
		assertNotEquals(artist.getId(), 0);
	}

	@Test
	void testFindAllArtist() {
		List<Artist> list = musicService.findAllArtist();
		assertThat(list).isNotEmpty();
	}

	@Test
	void testFindArtistById() {
		Artist artist = musicService.findArtistById(1);
		assertNotNull(artist);
	}

	@Test
	void testFindArtistByName() {
		List<Artist> list1 = musicService.findArtistByName("Jagjit", null);
		List<Artist> list2 = musicService.findArtistByName(null, "Mangeshkar");
		List<Artist> list3 = musicService.findArtistByName("Shankar", "Singh");
		assertThat(list1).isNotEmpty();
		assertThat(list2).isNotEmpty();
		assertThat(list3).isNotEmpty();
	}

	@Test
	void testFindArtistByFullName() {
		List<Artist> list1 = musicService.findArtistByFullName("Shankar", "Singh");
		assertThat(list1).isEmpty();
		List<Artist> list2 = musicService.findArtistByFullName("Arijit", "Singh");
		assertThat(list2).isNotEmpty();
	}

	@Test
	void testFindSongArtists() {
		List<Song> songList = musicService.findSongByTitle("Tera Ban Jaunga");
		for (Song song : songList) {
			List<Artist> artistList = musicService.findSongArtists(song.getId());
			assertThat(artistList).isNotEmpty();
		}
	}

	@Test
	void testFindAlbumArtists() {
		Album album = musicService.findAlbumByTitle("Marasim");
		List<Artist> artistList = musicService.findAlbumArtists(album.getId());
		//artistList.forEach(System.out::println);
		assertThat(artistList).isNotEmpty();
	}

	@Order(2)
	@Test
	void testSaveAlbum() {
		Album album = new Album(0, "Mera Naam Joker", "joker.png");
		musicService.saveAlbum(album);
		assertNotEquals(album.getId(), 0);
	}

	@Test
	void testFindAllAlbum() {
		List<Album> list = musicService.findAllAlbum();
		assertThat(list).isNotEmpty();
	}

	@Test
	void testFindAlbumById() {
		Album album = musicService.findAlbumById(1);
		assertNotNull(album);
	}

	@Test
	void testFindAlbumByTitle() {
		Album album = musicService.findAlbumByTitle("Marasim");
		assertNotNull(album);
	}

	@Test
	void testFindSongAlbum() {
		List<Song> songList = musicService.findSongByTitle("Teri Mitti");
		for (Song song : songList) {
			Album album = song.getAlbum();
			assertNotNull(album);
		}
	}

	@Test
	void testFindArtistAlbums() {
		List<Artist> artistList = musicService.findArtistByFullName("Arijit", "Singh");
		for (Artist artist : artistList) {
			List<Album> albumList = musicService.findArtistAlbums(artist.getId());
			//albumList.forEach(System.out::println);
			assertThat(albumList).isNotEmpty();
		}
	}

	@Order(3)
	@Test
	void testSaveSong() {
		Song song = new Song(0, "Jaane Kaha Gaye Wo Din", "j1.mp3", "06:53");
		song.setAlbum(musicService.findAlbumByTitle("Mera Naam Joker"));
		Artist artist = musicService.findArtistByFullName("Mukesh", "").get(0);
		song.addArtist(artist);
		musicService.saveSong(song);
		assertNotEquals(song.getId(), 0);
		
		song = musicService.findSongById(song.getId());
		assertNotNull(song);
		assertNotNull(song.getAlbum());
		assertThat(song.getArtistList()).isNotEmpty();
	}
	
	@Order(4)
	@Test
	void testUpdateSongArtist() {
		Artist artist = new Artist(0, "Raj", "Kapoor", "raj.png", "Actor");
		musicService.saveArtist(artist);
		
		Song song = musicService.findSongByTitle("Jaane Kaha Gaye Wo Din").get(0);
		//song.getArtistList().clear();
		song.addArtist(artist);
		musicService.saveSong(song);
		
		List<Artist> artistList = musicService.findSongArtists(song.getId());
		//artistList.forEach(System.out::println);
		assertEquals(2, artistList.size());
	}

	@Test
	void testFindSongById() {
		Song song = musicService.findSongById(1);
		assertNotNull(song);
	}

	@Test
	void testFindAllSong() {
		List<Song> list = musicService.findAllSong();
		assertThat(list).isNotEmpty();
	}

	@Test
	void testFindAlbumSong() {
		Album album = musicService.findAlbumByTitle("Marasim");
		assertNotNull(album);
		List<Song> songList = musicService.findAlbumSong(album.getId());
		assertThat(songList).isNotEmpty();
	}

	@Test
	void testFindArtistSong() {
		List<Artist> artistList = musicService.findArtistByFullName("Arijit", "Singh");
		for (Artist artist : artistList) {
			List<Song> songList = musicService.findArtistSong(artist.getId());
			assertThat(songList).isNotEmpty();
		}
	}

}
