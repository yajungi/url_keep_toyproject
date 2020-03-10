package com.example.toy.link.repository;

import com.example.toy.link.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Integer> {

}
