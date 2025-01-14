package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Pass;
public interface PassRepository extends JpaRepository<Pass, Long>{

}