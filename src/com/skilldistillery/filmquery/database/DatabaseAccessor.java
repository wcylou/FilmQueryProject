package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Inventory;

public interface DatabaseAccessor {
	public Film getFilmById(int filmId);

	public Actor getActorById(int actorId);

	public List<Actor> getActorsByFilmId(int filmId);

	public List<Film> getFilmByKeyword(String keyword);
	
	public List<Inventory> getInventoryFilms(int filmId);
	
	public List<String> getCategoriesByFilm(int filmId);
}
