package ru.rehtang.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.rehtang.films.dto.ApiResponseDto;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class FilmsProviderService {

    @Value("${filmsApi.url}")
    private String filmApiUrl;

    @PostConstruct
    public void init() {
        System.out.println(getFilm());
    }

    public ApiResponseDto getFilm() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(filmApiUrl, ApiResponseDto.class);
    }
}