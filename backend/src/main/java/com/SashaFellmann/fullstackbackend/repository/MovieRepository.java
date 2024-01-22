package com.SashaFellmann.fullstackbackend.repository;

import com.SashaFellmann.fullstackbackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
