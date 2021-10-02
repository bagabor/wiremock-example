package com.example.wiremockdemo.web.client;

import com.example.wiremockdemo.web.controller.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "simple-books-client", url = "${book.service.url}")
public interface RemoteClient {

    @GetMapping(value = "/books")
    BookDto getBookById();
}
