package com.example.pi_backend2.Service;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

@Service
public class APIService {

    private final WebClient webClient;

    public APIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8000").build();
    }

    public Mono<List<Map<String, Object>>> generateQuizQuestions(String cours) {
        System.out.println();
        System.out.println("in api service");
        System.out.println();
        System.out.println(cours.split("\n")[0]);
        System.out.println();

        System.out.println();
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/generate").queryParam("cours", cours).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {});
    }

//    public Mono<String> explainQuestion(String question, String answer) {
//        return webClient.get()
//                .uri(uriBuilder -> uriBuilder.path("/explain").queryParam("question", question).queryParam("answer", answer).build())
//                .retrieve()
//                .bodyToMono(String.class);
//    }
}