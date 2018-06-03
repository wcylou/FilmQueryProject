package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

	// private void test() {
	// Film film = db.getFilmById(1);
	// System.out.println(film);
	// }

	private void launch() {
		System.out.println("Menu\n1. Look up a film by ID\n2. Look up film by keyword\n3. Exit");
		int choice = input.nextInt();
		switch (choice) {
		case 1:
			lookUpFilmById();
			break;
		case 2:
			lookUpFilmByKeyword();
			break;
		case 3:
			System.out.println("Bye");
			System.exit(0);
		}
		input.close();
	}

	private void lookUpFilmById() {
		System.out.println("Enter the film ID");
		int filmId = input.nextInt();
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		System.out.println("1. View all details\n2. View basic details\n3. Return to main menu");
		int choice = input.nextInt();
		switch (choice) {
		case 1:
			try {
				System.out.println(dao.getFilmById(filmId).toString());
				launch();
			} catch (Exception e) {
				System.out.println("Film ID not found");
				lookUpFilmById();
			}
			break;
		case 2:
			try {
				System.out.println(dao.getFilmById(filmId).toStringCompact());
				launch();
			} catch (Exception e) {
				System.out.println("Film ID not found");
				lookUpFilmById();
			}
			break;
		case 3:
			launch();
			break;
		}
	}

	private void lookUpFilmByKeyword() {
		System.out.println("Enter a keyword");
		String keyword = input.next();
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		System.out.println(dao.getFilmByKeyword(keyword).toString());
		if (dao.getFilmByKeyword(keyword).isEmpty()) {
			lookUpFilmByKeyword();
		}
		launch();
	}

}
