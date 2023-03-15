package com.sunbeam.dtos;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.sunbeam.entities.Artist;
import com.sunbeam.entities.Song;

@Component
public class EntityDtoConverterImpl {
	public Song songDtoToEntity(SongFileDTO songDto) {
		Song song = new Song();
		BeanUtils.copyProperties(songDto, song, "file");
		return song;
	}
	public Artist artistDtoToEntity(ArtistFileDTO artistDto) {
		Artist artist = new Artist();
		BeanUtils.copyProperties(artistDto, artist, "thumbnail");
		return artist;
	}
}
