package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Residence;
public interface ResidenceRepository extends JpaRepository<Residence, Integer>{

}
