package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String url = "jdbc:mysql://localhost:3306/sdvid";
	private static final String user = "student";
	private static final String password = "student";

	@Override
	public Film getFilmById(int filmId) {
		Film film = null;
		String sql = "select f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name from film f join language l on l.id = f.language_id where f.id = ?";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString(3));
				film.setReleaseYear(rs.getInt(4));
				film.setLanguageId(rs.getInt(5));
				film.setRentalDuration(rs.getInt(6));
				film.setRentalRate(rs.getDouble(7));
				film.setLength(rs.getInt(8));
				film.setReplacementCost(rs.getDouble(9));
				film.setRating(rs.getString(10));
				film.setSpecialFeatures(rs.getString(11));
				film.setActors(getActorsByFilmId(filmId));
				film.setLanguageName(rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public List<Film> getFilmByKeyword(String keyword) {
		String sql = "select * from film where title like ? or description like ?";
		List<Film> films = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();	
			while (rs.next()) {
				Film film;
				film = new Film();
				film.setId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString(3));
				film.setReleaseYear(rs.getInt(4));
				film.setLanguageId(rs.getInt(5));
				film.setRentalDuration(rs.getInt(6));
				film.setRentalRate(rs.getDouble(7));
				film.setLength(rs.getInt(8));
				film.setReplacementCost(rs.getDouble(9));
				film.setRating(rs.getString(10));
				film.setSpecialFeatures(rs.getString(11));
				films.add(getFilmById(rs.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Actor getActorById(int actorId) {
		Actor actor = null;
		String sql = "select * from actor where id = ?";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt(1));
				actor.setFirstName(rs.getString(2));
				actor.setLastName(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
	}
	
	@Override
	public List<Actor> getActorsByFilmId(int filmId) {
		String sql = "select a.id from actor a join film_actor fa on fa.actor_id = a.id join film f on f.id = fa.film_id where f.id = ?";
		List<Actor> actors = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			int count = 1;
			while (rs.next()) {
				actors.add(getActorById(count++));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actors;
	}


}
