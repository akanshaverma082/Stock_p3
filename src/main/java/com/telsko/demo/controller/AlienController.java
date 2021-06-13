package com.telsko.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.telsko.demo.dao.AlienRepo;
import com.telsko.demo.model.Alien;

@RestController
public class AlienController {
	@Autowired
	AlienRepo repo;
	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	@PostMapping("/addAlien")
	public Alien addAlien(@RequestBody Alien alien)
	{
		repo.save(alien);
		return alien;
	}
	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable int aid) 
	{
		Alien a=repo.getOne(aid);
		repo.delete(a);
		return "deleted";
	}
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int aid)
	{
		ModelAndView mv=new ModelAndView("ShowAlien.jsp");
		Alien alien=repo.findById(aid).orElse(new Alien());
		System.out.println(repo.findBytech("java"));
		System.out.println(repo.findByTechSorted("java"));
		mv.addObject(alien);
		return mv;
	}
	@PutMapping("/alien")
	public Alien saveorUpdateAlien(@RequestBody Alien alien)
	{
		repo.save(alien);
		return alien;
	}
	@RequestMapping("/delAlien")
	public String delAlien(Alien alien,@RequestParam int aid)
	{
		repo.deleteById(aid);
		return repo.findAll().toString();
	}
	@GetMapping(path="/aliens")
	public List<Alien> getAliens()
	{
		return repo.findAll();
	}
	@RequestMapping("/aliens/{aid}")
	public Optional<Alien> getAlienid(@PathVariable("aid") int aid)
	{
		return repo.findById(aid);
	}
	
}
