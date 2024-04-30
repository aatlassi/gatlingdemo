package io.gatling.demo;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class Navigation extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://petstore.octoperf.com")
    .inferHtmlResources()
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
    Map.entry("accept-encoding", "gzip, deflate, br, zstd"),
    Map.entry("accept-language", "en-GB,en-US;q=0.9,en;q=0.8"),
    Map.entry("priority", "u=0, i"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"124\", \"Google Chrome\";v=\"124\", \"Not-A.Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "macOS"),
    Map.entry("sec-fetch-dest", "document"),
    Map.entry("sec-fetch-mode", "navigate"),
    Map.entry("sec-fetch-site", "same-origin"),
    Map.entry("sec-fetch-user", "?1"),
    Map.entry("upgrade-insecure-requests", "1")
  );
  
  private Map<CharSequence, String> headers_1 = Map.ofEntries(
    Map.entry("sec-ch-ua", "Chromium\";v=\"124\", \"Google Chrome\";v=\"124\", \"Not-A.Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "macOS")
  );


  private ScenarioBuilder Navigation = scenario("Navigation")
    .exec(
      http("request_0")
        .get("/actions/Catalog.action?viewCategory=&categoryId=BIRDS")
        .headers(headers_0)
        .resources(
          http("request_1")
            .get("/css/jpetstore.css")
            .headers(headers_1),
          http("request_2")
            .get("/images/logo-topbar.svg")
            .headers(headers_1),
          http("request_3")
            .get("/images/cart.gif")
            .headers(headers_1),
          http("request_4")
            .get("/images/separator.gif")
            .headers(headers_1),
          http("request_5")
            .get("/images/sm_fish.gif")
            .headers(headers_1),
          http("request_6")
            .get("/images/sm_dogs.gif")
            .headers(headers_1),
          http("request_7")
            .get("/images/sm_reptiles.gif")
            .headers(headers_1),
          http("request_8")
            .get("/images/sm_cats.gif")
            .headers(headers_1),
          http("request_9")
            .get("/images/sm_birds.gif")
            .headers(headers_1)
        )
    );

  {
	  setUp(Navigation.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
