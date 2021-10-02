package com.example.wiremockdemo;

import com.example.wiremockdemo.web.client.RemoteClient;
import com.example.wiremockdemo.web.controller.BookController;
import com.example.wiremockdemo.web.controller.dto.BookDto;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.util.StreamUtils.copyToString;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
public class RemoteClientIT {

    @Autowired
    private WireMockServer mockBooksService;

    @Autowired
    private RemoteClient remoteClient;

    @Autowired
    private BookController bookController;

    @Test
    public void whenGetBook_thenBookTitleShouldBeCorrect() throws IOException {
        mockBooksService.stubFor(WireMock.get(WireMock.urlEqualTo("/books"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        RemoteClientIT.class.getClassLoader().getResourceAsStream("payload/get-books-response.json"),
                                        defaultCharset()))));

        assertEquals("Dune", remoteClient.getBookById().getTitle());
    }

    @Test
    public void checkMockedRemoteClient() throws IOException {
        mockBooksService.stubFor(WireMock.get(WireMock.urlEqualTo("/books"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        RemoteClientIT.class.getClassLoader().getResourceAsStream("payload/get-books-response.json"),
                                        defaultCharset()))));

        BookDto bookDto = bookController.getBookById(1L);
        assertEquals("Dune", bookDto.getTitle());
    }
}
