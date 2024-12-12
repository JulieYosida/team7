package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.travel;

public interface TravelRepository extends JpaRepository<travel, Long> {
}
