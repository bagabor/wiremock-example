package com.example.wiremockdemo.service;

import com.example.wiremockdemo.web.client.RemoteClient;
import com.example.wiremockdemo.web.controller.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {

    private final RemoteClient remoteClient;

    public BookDto getBookById(final Long id) {
        log.info("Data fetching has been started with id: [%s]", id.toString());
        final BookDto responseDto = remoteClient.getBookById();
        return BookDto.builder()
                .author(responseDto.getAuthor())
                .title(responseDto.getTitle())
                .build();
    }
}
