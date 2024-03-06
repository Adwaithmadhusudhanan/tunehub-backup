package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimplementation.SongServiceImpl;

@Controller
public class SongController {
	@Autowired
	SongServiceImpl songServiceImpl;

	@PostMapping("/addsong")
	public String addsong(@ModelAttribute Song song) {
		boolean songStatus = songServiceImpl.songExists(song.getName());
		if (songStatus == false) {
			songServiceImpl.addSong(song);
			System.out.println("song added successfully");

		} else {
			System.out.println("song already exists");
		}

		return "adminhome";
	}

	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
		List<Song> songList = songServiceImpl.fetchAllSongs();  
		model.addAttribute("songs", songList);
		return "displaysongs";

	}
	@GetMapping("/playsongs")
	public String playsongs(Model model) {
		boolean premium = false;
		if(premium) {
			List<Song> songList = songServiceImpl.fetchAllSongs();  
			model.addAttribute("songs", songList);
			return "displaysongs";

		}else {
			return "subscrptionfrom";
		}
		
	}
}
 