package com.SashaFellmann.fullstackbackend.controller;

import com.SashaFellmann.fullstackbackend.exception.MovieNotFoundException;
import com.SashaFellmann.fullstackbackend.model.Movie;
import com.SashaFellmann.fullstackbackend.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/movie")
    Movie newMovie(@RequestBody Movie newMovie){
        return movieRepository.save(newMovie);
    }

    @GetMapping("/movies")
    List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @GetMapping("/movie/{id}")
    Movie getMovieById(@PathVariable Long id){
        return movieRepository.findById(id)
                .orElseThrow(()->new MovieNotFoundException(id));
    }

    @PutMapping("/movie/{id}")
    Movie updateMovie(@RequestBody Movie newMovie, @PathVariable Long id) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setName(newMovie.getName());
                    movie.setReleasedate(newMovie.getReleasedate());
                    movie.setGenre(newMovie.getGenre());
                    movie.setDirector(newMovie.getDirector());

                    return movieRepository.save(movie);
                }).orElseThrow(() -> new MovieNotFoundException(id));
    }

    @DeleteMapping("/movie/{id}")
    String deleteMovie(@PathVariable Long id){
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundException(id);
        }
        movieRepository.deleteById(id);
        return  "Movie with id "+id+" has been deleted.";
    }
}