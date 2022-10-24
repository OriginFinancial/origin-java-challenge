package com.origin.challenge.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private HttpClient httpClient;

    @GetMapping
    public ResponseEntity<PokemonDTO> listPokemon(@RequestParam(name = "page", defaultValue = "1") int page)
            throws Exception {
        final int limit = 20;
        final int offset = page - 1;
        final String url = String.format("https://pokeapi.co/api/v2/pokemon?offset=%d&limit=%d", offset, limit);

        final HttpRequest r = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header(
                    "Authorization",
                    "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZ" +
                    "SIsImV4cCI6MTY2MjA0MjMzNCwiaWF0IjoxNjYyMDQyMzM0fQ.xi3uKpbHXXxE5iTOkDrkHJfpXQhGQGjLHXwC1SE-kFI"
                )
                .GET()
                .build();
        final HttpResponse<String> r2 = this.httpClient.send(r, HttpResponse.BodyHandlers.ofString());

        final ObjectMapper objectMapper = new ObjectMapper();
        final PokemonDTO r3 = objectMapper.readValue(r2.body(), PokemonDTO.class);

        return ResponseEntity.ok(r3);
    }
}
