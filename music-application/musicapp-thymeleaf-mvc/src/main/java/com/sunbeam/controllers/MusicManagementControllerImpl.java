package com.sunbeam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunbeam.dtos.ArtistFileDTO;
import com.sunbeam.dtos.EntityDtoConverterImpl;
import com.sunbeam.dtos.SongFileDTO;
import com.sunbeam.entities.Album;
import com.sunbeam.entities.Artist;
import com.sunbeam.entities.Song;
import com.sunbeam.services.MusicServiceImpl;
import com.sunbeam.services.StorageService;

@Controller
public class MusicManagementControllerImpl {
	@Autowired
	private MusicServiceImpl musicService;
	@Autowired
	private EntityDtoConverterImpl converter;
	@Autowired
	private StorageService storageService;
	
	@RequestMapping("/manage")
	public String manage() {
		return "manage";
	}
	
	@RequestMapping("/artists/new")
	public String newArtist(Model model) {
		Artist artist = new Artist();
		model.addAttribute("artist", artist);
		return "artistform";
	}
	
	@RequestMapping("/artists/save")
	public String saveArtist(@RequestParam("fileName")String artistFileName, ArtistFileDTO artistDto) {
		Artist artist = converter.artistDtoToEntity(artistDto);
		if(!artistDto.getThumbnail().isEmpty()) {
			String fileName = storageService.store(artistDto.getThumbnail());
			artist.setThumbnail(fileName);
		}
		else
			artist.setThumbnail(artistFileName);
		musicService.saveArtist(artist);
		return "redirect:/artists/list";
	}
	
	@RequestMapping("/artists/edit")
	public String editArtist(@RequestParam("id") int artistId, Model model) {
		Artist artist = musicService.findArtistById(artistId);
		model.addAttribute("artist", artist);
		return "artistform";
	}
	
	@RequestMapping("/albums/new")
	public String newAlbum(Model model) {
		Album album = new Album();
		model.addAttribute("album", album);
		return "albumform";
	}
	
	@RequestMapping("/albums/save")
	public String saveAlbum(Album album) {
		musicService.saveAlbum(album);
		return "redirect:/albums/list";
	}
	
	@RequestMapping("/albums/edit")
	public String editAlbum(@RequestParam("id") int albumId, Model model) {
		Album album = musicService.findAlbumById(albumId);
		model.addAttribute("album", album);
		return "albumform";
	}

	@RequestMapping("/songs/new")
	public String newSong(Model model) {
		Song song = new Song();
		model.addAttribute("song", song);
		List<Album> albumList = musicService.findAllAlbum();
		model.addAttribute("albumList", albumList);
		List<Artist> artistList = musicService.findAllArtist();
		model.addAttribute("artistList", artistList);
		return "songform";
	}

	@RequestMapping("/songs/save")
	public String saveSong(SongFileDTO songDto) {
		Song song = converter.songDtoToEntity(songDto);
		String fileName = storageService.store(songDto.getFile());
		song.setFile(fileName);
		musicService.saveSong(song);
		return "redirect:/songs/list";
	}
	
	@RequestMapping("/songs/edit")
	public String editSong(@RequestParam("id") int songId, Model model) {
		Song song = musicService.findSongById(songId);
		model.addAttribute("song", song);
		List<Album> albumList = musicService.findAllAlbum();
		model.addAttribute("albumList", albumList);
		List<Artist> artistList = musicService.findAllArtist();
		model.addAttribute("artistList", artistList);
		return "songform";
	}
}


