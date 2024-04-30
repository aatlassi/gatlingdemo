package io.gatling.demo;
import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class SwaggerApi extends Simulation{

    HttpProtocolBuilder httpProtocol = http.baseUrl("https://neoload-api.saas.neotys.com/explore/");

    ScenarioBuilder workspaces = scenario("Scenario 1")
            // will make a request to "https://gatling.io/docs/"
            .exec(
                    http("listWorkspaces").get("/v3/workspaces")
                            .queryParam("allWorkspaces", "true")
                            .queryParam("limit", "50")
                            .header("accept", "application/json")
                            // only for requests that have a body
                            .header("accountToken","bf0c843cd0b4697ba4f42efb0be14799442f6eb5dbd49d58")
            );

    ScenarioBuilder ping = scenario("Scenario 2")
            // will make a request to "https://gatling.io/docs/"
            .exec(
                    http("ping").get("/ping")
            );

    {
        setUp(
                workspaces.injectOpen(atOnceUsers(1)).protocols(httpProtocol)
               // ping.injectOpen(atOnceUsers(1)).protocols(httpProtocol)

        )/*.assertions(global().responseTime().max().lt(800))*/;
    }
}
