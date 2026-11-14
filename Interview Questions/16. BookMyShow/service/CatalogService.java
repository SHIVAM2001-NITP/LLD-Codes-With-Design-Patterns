package service;

import entities.Movie;
import entities.Show;
import entities.Theatre;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// What is playing where: theatres, movies and shows, searchable by city and movie
public class CatalogService {
    private final List<Theatre> theatres = new ArrayList<>();
    private final Map<String, Movie> movies = new LinkedHashMap<>();
    private final Map<String, Show> shows = new LinkedHashMap<>();

    public void addTheatre(Theatre theatre) { theatres.add(theatre); }

    public void addMovie(Movie movie) { movies.put(movie.getId(), movie); }

    public void addShow(Show show) { shows.put(show.getId(), show); }

    public Show getShow(String showId) { return shows.get(showId); }

    public List<Movie> getMoviesInCity(String city) {
        List<Movie> result = new ArrayList<>();
        for (Show show : shows.values()) {
            if (show.getTheatre().getCity().equalsIgnoreCase(city) && !result.contains(show.getMovie())) {
                result.add(show.getMovie());
            }
        }
        return result;
    }

    public List<Show> searchShows(String city, String movieTitle) {
        List<Show> result = new ArrayList<>();
        for (Show show : shows.values()) {
            if (show.getTheatre().getCity().equalsIgnoreCase(city)
                    && show.getMovie().getTitle().equalsIgnoreCase(movieTitle)) {
                result.add(show);
            }
        }
        return result;
    }
}
