package com.telsko.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.telsko.demo.model.Alien;


public interface AlienRepo extends JpaRepository<Alien,Integer>
{
List<Alien>findBytech(String tech);
@Query("from Alien where tech=?1 order by name")
List<Alien> findByTechSorted(String tech);
}
