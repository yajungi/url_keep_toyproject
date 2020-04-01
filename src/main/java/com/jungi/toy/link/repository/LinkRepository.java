package com.jungi.toy.link.repository;

import com.jungi.toy.link.domain.Link;
import org.hibernate.loader.collection.BasicCollectionJoinWalker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Integer> {
    Page<Link> findByRemoveFlag(boolean removeFlag, Pageable pageable);

    Page<Link> findByEmailAndRemoveFlag(String email, boolean removeFlag, Pageable pageable);
}
