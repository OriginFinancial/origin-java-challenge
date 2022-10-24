package com.origin.challenge.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings({"unchecked", "rawtypes"})
public class PokemonControllerIntegrationTest {
    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private PokemonController pokemonController;


    private static HttpResponse httpResponse;


    @BeforeEach
    public void setup() {
        httpResponse = mock(HttpResponse.class);
        when(httpResponse.body()).thenReturn("{\"count\":1154,\"next\":\"https://pokeapi.co/api/v2/pokemon?offset=20" +
                "&limit=20\",\"previous\":null,\"results\":[{\"name\":\"bulbasaur\",\"url\":\"https://pokeapi.co/api" +
                "/v2/pokemon/1/\"}]}"
        );
    }


    @Test
    void test1() throws Exception {
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        final HttpRequest expected = HttpRequest.newBuilder()
                .uri(new URI("https://pokeapi.co/api/v2/pokemon?offset=0&limit=20"))
                .header(
                        "Authorization",
                        "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZ" +
                        "SIsImV4cCI6MTY2MjA0MjMzNCwiaWF0IjoxNjYyMDQyMzM0fQ.xi3uKpbHXXxE5iTOkDrkHJfpXQhGQGjLHXwC1SE-kFI"
                )
                .GET()
                .build();

        pokemonController.listPokemon(1);
        verify(httpClient, times(1)).send(expected, HttpResponse.BodyHandlers.ofString());
    }

    @Test
    void test2() throws Exception {
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        final HttpRequest expected = HttpRequest.newBuilder()
                .uri(new URI("https://pokeapi.co/api/v2/pokemon?offset=20&limit=20"))
                .header(
                        "Authorization",
                        "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZ" +
                                "SIsImV4cCI6MTY2MjA0MjMzNCwiaWF0IjoxNjYyMDQyMzM0fQ.xi3uKpbHXXxE5iTOkDrkHJfpXQhGQGjLHXwC1SE-kFI"
                )
                .GET()
                .build();

        pokemonController.listPokemon(2);
        verify(httpClient, times(1)).send(expected, HttpResponse.BodyHandlers.ofString());
    }
}
