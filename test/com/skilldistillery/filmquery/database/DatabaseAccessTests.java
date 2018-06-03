package com.skilldistillery.filmquery.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Inventory;

class DatabaseAccessTests {
	private DatabaseAccessor db;
	Film film;
	List<Film> films;
	Actor actor;
	List<Actor> actors;
	Inventory inventory;
	List<Inventory> inventories;

	@BeforeEach
	void setUp() throws Exception {
		db = new DatabaseAccessorObject();
	}

	@AfterEach
	void tearDown() throws Exception {
		db = null;
	}

	@Test
	void test_getFilmById_with_invalid_id_returns_null() {
		Film f = db.getFilmById(-42);
		assertNull(f);
	}

	@Test
	@DisplayName("Test that getFilmByID with 100 returns BROOKLYN DESERT")
	void testFilmById100() {
		film = db.getFilmById(100);
		assertEquals("BROOKLYN DESERT", film.getTitle());
	}

	@Test
	@DisplayName("Test that getFilmByKeyword with monkey returns 87 results")
	void testFilmByKeywordMonkey() {
		films = db.getFilmByKeyword("monkey");
		assertEquals(87, films.size());
	}

	@Test
	@DisplayName("Test that getActorByID with actor ID 5 returns Johnny Lollobrigida")
	void testActorById5() {
		actor = db.getActorById(5);
		assertEquals("Johnny Lollobrigida", actor.getFirstName() + " " + actor.getLastName());
	}

	@Test
	@DisplayName("Test that getActorsByFilmID with film ID 5 returns 5 results")
	void testActorsByFilmID5() {
		actors = db.getActorsByFilmId(5);
		assertEquals(5, actors.size());
	}

	@Test
	@DisplayName("Test that getInventoryFilms with film ID 5 returns 17 results")
	void testInventorybyFilms5() {
		inventories = db.getInventoryFilms(5);
		assertEquals(17, inventories.size());
	}
}
