package com.sunbeam.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunbeam.daos.AlbumDao;
import com.sunbeam.daos.ArtistDao;
import com.sunbeam.daos.SongDao;
import com.sunbeam.daos.UserDao;
import com.sunbeam.entities.Account;
import com.sunbeam.entities.Album;
import com.sunbeam.entities.Artist;
import com.sunbeam.entities.Song;
import com.sunbeam.entities.User;

@Component
public class DbInit {
	@Autowired
	private UserDao userDao;
	@Autowired
	private AlbumDao albumDao;
	@Autowired
	private ArtistDao artistDao;
	@Autowired
	private SongDao songDao;
	
	public static Date date(int year, int month, int day) {
		return new Date(year-1900, month-1, day);
	}
	
	public List<User> userList() {
		User u;
		List<User> users = new ArrayList<>();
		u = new User(0, "Admin", "admin@gaana.com", "9876543210", "India", "admin", date(1980, 1, 1));
		users.add(u);
		u = new User(0, "Nilesh", "nilesh@gmail.com", "9527331338", "Pune", "nilesh", date(1983, 9, 28));
		u.setAccount(new Account(0, "Paid", date(2021, 9, 1), date(2021, 9, 30)));
		users.add(u);
		u = new User(0, "Vishal", "vishal@gmail.com", "9822012345", "Pune", "vishal", date(1980, 1, 1));
		u.setAccount(new Account(0, "Trial", date(2021, 9, 1), date(2022, 9, 1)));
		users.add(u);
		u = new User(0, "Rahul", "rahul@gmail.com", "9422012345", "Pune", "rahul", date(1983, 12, 31));
		u.setAccount(new Account(0, "Free", null, null));
		users.add(u);
		return users;
	}
	
	public List<Album> albumList() {
		List<Album> albums = new ArrayList<Album>();
		albums.add(new Album(0, "Marasim", "marasim.png"));
		albums.add(new Album(0, "Different Strokes", "differentstrokes.png"));
		albums.add(new Album(0, "Kesari", "kesari.png"));
		albums.add(new Album(0, "Kabir Singh", "kabirsingh.png"));
		return albums;
	}
	
	public List<Artist> artistList() {
		List<Artist> artists = new ArrayList<Artist>();
		artists.add(new Artist(0, "Lata", "Mangeshkar", "lata.png", "Singer"));
		artists.add(new Artist(0, "Asha", "Bhosale", "asha.png", "Singer"));
		artists.add(new Artist(0, "Shankar", "Mahadevan", "mahadevan.png", "Singer"));
		artists.add(new Artist(0, "Jagjit", "Singh", "jagjit.png", "Singer"));
		artists.add(new Artist(0, "Guljar", "", "guljar.png", "Lyricist"));
		artists.add(new Artist(0, "Sachet", "Tandon", "SachetTandon.png", "Singer"));
		artists.add(new Artist(0, "Arijit", "Singh", "ArijitSingh.png", "Singer"));
		artists.add(new Artist(0, "Akhil", "Sachdeva", "AkhilSachdeva.png", "Singer"));
		artists.add(new Artist(0, "Tulsi", "Kumar", "TulsiKumar.png", "Singer"));
		artists.add(new Artist(0, "B", "Praak", "BPraak.png", "Singer"));
		return artists;
	}
	
	public List<Song> songList() {
		Song s;
		List<Song> songs = new ArrayList<Song>();
		s = new Song(0, "Haath Chute Bhi To", "m1.mp3", "05:32");
		s.addArtist(artistDao.findByFirstName("Jagjit").get(0));
		s.setAlbum(albumDao.findByTitle("Marasim"));
		songs.add(s);
		
		s = new Song(0, "Shaam Se Aankh me", "m2.mp3", "04:55");
		s.addArtist(artistDao.findByFirstName("Jagjit").get(0));
		s.setAlbum(albumDao.findByTitle("Marasim"));
		songs.add(s);
		
		s = new Song(0, "Khwab", "m3.mp3", "01:06");
		s.addArtist(artistDao.findByFirstName("Guljar").get(0));
		s.setAlbum(albumDao.findByTitle("Marasim"));
		songs.add(s);
		
		s = new Song(0, "Babul Mora", "d1.mp3", "06:46");
		s.addArtist(artistDao.findByFirstName("Jagjit").get(0));
		s.setAlbum(albumDao.findByTitle("Different Strokes"));
		songs.add(s);
		
		s = new Song(0, "Baju Band Khul Khul Jaye", "d2.mp3", "07:31");
		s.addArtist(artistDao.findByFirstName("Jagjit").get(0));
		s.setAlbum(albumDao.findByTitle("Different Strokes"));
		songs.add(s);
	
		s = new Song(0, "Tarana", "d3.mp3", "05:03");
		s.addArtist(artistDao.findByFirstName("Jagjit").get(0));
		s.setAlbum(albumDao.findByTitle("Different Strokes"));
		songs.add(s);
		
		s = new Song(0, "Ve Maahi", "k1.mp3", "03:43");
		s.addArtist(artistDao.findByFirstName("Arijit").get(0));
		s.setAlbum(albumDao.findByTitle("Kesari"));
		songs.add(s);
		
		s = new Song(0, "Teri Mitti", "k2.mp3", "05:14");
		s.addArtist(artistDao.findByFirstName("B").get(0));
		s.setAlbum(albumDao.findByTitle("Kesari"));
		songs.add(s);

		s = new Song(0, "Bekhayali", "s1.mp3", "06:12");
		s.addArtist(artistDao.findByFirstName("Sachet").get(0));
		s.setAlbum(albumDao.findByTitle("Kabir Singh"));
		songs.add(s);

		s = new Song(0, "Tujhe Kitna Chahne Lage", "s2.mp3", "04:45");
		s.addArtist(artistDao.findByFirstName("Arijit").get(0));
		s.setAlbum(albumDao.findByTitle("Kabir Singh"));
		songs.add(s);
		
		s = new Song(0, "Tera Ban Jaunga", "s3.mp3", "03:56");
		s.addArtist(artistDao.findByFirstName("Akhil").get(0));
		s.addArtist(artistDao.findByFirstName("Tulsi").get(0));
		s.setAlbum(albumDao.findByTitle("Kabir Singh"));
		songs.add(s);
		
		return songs;
	}
	
	@PostConstruct
	public void init() {
		userList().forEach(u -> userDao.save(u));
		albumList().forEach(a -> albumDao.save(a));
		artistList().forEach(a -> artistDao.save(a));
		songList().forEach(s -> songDao.save(s));
	}
}

