package com.MusicMash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Small mesh API application.
 *
 * By providing a mbid a client
 * can query an artist from MusicBrainz.
 *
 * The response contains a description of the artist
 * and an array of albums.
 *
 * @Author Johan Besseling
 * @Date 2022-02-22
 */
@SpringBootApplication
public class MusicMashApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicMashApplication.class);
	}

}
