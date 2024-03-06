package com.kodnest.tunehub.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.PlaylistRepository;
import com.kodnest.tunehub.service.PlaylistService;
@Service
public class PlaylistServiceImpl implements PlaylistService{
	@Autowired
	PlaylistRepository playlistRepository;

	public void addplaylist(Playlist playlist) {
		playlistRepository.save(playlist);
		
	}

	public List<Playlist> fetchAllPlaylists() {
		List<Playlist> allPlaylists =  playlistRepository.findAll();
		return allPlaylists;
	}

	
}
