package com.roomManagement.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roomManagement.config.HttpClientConfig;
import com.roomManagement.dto.PaginationDTO;
import com.roomManagement.exception.FailedDependencyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static com.roomManagement.util.Converters.convertStringToObject;
import static java.lang.String.format;
import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.time.Duration.ofSeconds;
import static org.springframework.http.HttpHeaders.ACCEPT;

@Slf4j
@Component
public record UserGateway(
        ObjectMapper mapper,
        HttpClientConfig httpClientConfig
) {
  private static final String URL = "http://localhost:8080/api/user?id=%s";
  private static final Duration TIME_OUT = ofSeconds(10);
  private static final String FAIL_DEPENDENCY_VIA_CEP_MESSAGE = "error to get data from user";

  public PaginationDTO getUser(Long userId) {
    var uri = URI.create(format(URL, userId));
    var response = sendRequest(uri);

    return convertStringToObject(response.body(), PaginationDTO.class);
  }

  private HttpResponse<String> sendRequest(URI uri) {
    try {
      return httpClientConfig
              .getHttpClient()
              .build()
              .sendAsync(getHttpRequest(uri), ofString())
              .join();
    } catch (Exception ex) {
      log.error(FAIL_DEPENDENCY_VIA_CEP_MESSAGE, ex);
      throw new FailedDependencyException(FAIL_DEPENDENCY_VIA_CEP_MESSAGE, ex);
    }
  }

  private HttpRequest getHttpRequest(URI uri) {
    return HttpRequest.newBuilder()
            .uri(uri)
            .timeout(TIME_OUT)
            .header(ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build();
  }

}
