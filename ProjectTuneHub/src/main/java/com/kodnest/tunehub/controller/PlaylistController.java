package com.kodnest.tunehub.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimplementation.PlaylistServiceImpl;
import com.kodnest.tunehub.serviceimplementation.SongServiceImpl;
   
@Controller
public class PlaylistController {
	@Autowired
	SongServiceImpl songServiceImpl; 
	@Autowired
	PlaylistServiceImpl playlistServiceImpl;
	
	
	@GetMapping("/createplaylists")
	public String createPlaylists(Model model) {
		List<Song> songList = songServiceImpl.fetchAllSongs();  
		model.addAttribute("songs", songList);
		return "createplaylists";
		

	}
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist){
		//updating the playlist table 
		playlistServiceImpl.addplaylist(playlist);
		//for updating the song table
		List<Song> songList = playlist.getSongs();
		for (Song s : songList) {
			s.getPlaylist().add(playlist);
			songServiceImpl.updateSong(s);
		}
		return "adminhome";
		
		
	}
	@GetMapping("viewplaylists")
	public String viewPlaylists(Model model) {
		List<Playlist> allPlaylists = playlistServiceImpl.fetchAllPlaylists();  
		model.addAttribute("allPlaylists", allPlaylists);
		return "displayplaylist";

	}
}
