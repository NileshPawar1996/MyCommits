package com.sunbeam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunbeam.dtos.PlayQueue;
import com.sunbeam.entities.Album;
import com.sunbeam.entities.Artist;
import com.sunbeam.entities.Song;
import com.sunbeam.services.MusicServiceImpl;

@Controller
public class MusicExplorerControllerImpl {
	@Autowired
	private MusicServiceImpl musicService;
	@Autowired
	private PlayQueue playQueue;
	
	@RequestMapping("/explore")
	public String explore() {
		return "explore";
	}
	
	@RequestMapping("/artists/list")
	public String artistList(Model model) {
		List<Artist> list = musicService.findAllArtist();
		model.addAttribute("artistList", list);
		return "artists";
	}
	
	@RequestMapping("/artists/albums")
	public String artistAlbumList(@RequestParam("id") int artistId, Model model) {
		List<Album> list = musicService.findArtistAlbums(artistId);
		model.addAttribute("albumList", list);
		return "albums";
	}
	
	@RequestMapping("/artists/songs")
	public String artistSongList(@RequestParam("id") int artistId, Model model) {
		List<Song> list = musicService.findArtistSong(artistId);
		model.addAttribute("songList", list);
		return "songs";
	}
	
	@RequestMapping("/albums/list")
	public String albumList(Model model) {
		List<Album> list = musicService.findAllAlbum();
		model.addAttribute("albumList", list);
		return "albums";
	}
	
	@RequestMapping("/albums/songs")
	public String albumSongList(@RequestParam("id") int albumId, Model model) {
		List<Song> list = musicService.findAlbumSong(albumId);
		model.addAttribute("songList", list);
		return "songs";
	}
	
	@RequestMapping("/songs/list")
	public String songList(Model model, Pageable page) {
		System.out.println("Paging object -------- " + page);
		Page<Song> list = musicService.findAllSong(page);
		model.addAttribute("songList", ((Streamable<Song>) list.get()).toList());
		return "songs";
	}
	
	@RequestMapping("/songs/addqueue")
	public String addSongInPlayQueue(@RequestParam("id") int songId) {
		playQueue.add(songId);
		System.out.println(playQueue);
		return "redirect:/songs/list";
	}
	
	@RequestMapping("/songs/queue")
	public String playQueue(Model model) {
		List<Song> list = musicService.findAllSongById(playQueue);
		model.addAttribute("playlist", true);
		model.addAttribute("songList", list);
		return "songs";
	}
}
