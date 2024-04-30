package io.gatling.demo;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class MyApi extends Simulation{


    HttpProtocolBuilder httpProtocol = http.baseUrl("https://gatling.io");

    ScenarioBuilder login = scenario("Scenario 1")
            // will make a request to "https://gatling.io/docs/"
            .exec(
                    http("login").post("/login")
                            .header("accept", "application/json")
                            // only for requests that have a body
                            .header("content-type", "application/json")
            );

    ScenarioBuilder ping = scenario("Scenario 2")
            // will make a request to "https://gatling.io/docs/"
            .exec(
                    http("ping").get("/ping")
            );

    {
        setUp(
                login.injectOpen(atOnceUsers(1)).protocols(httpProtocol),
                ping.injectOpen(atOnceUsers(1)).protocols(httpProtocol)

        ).assertions(global().responseTime().max().lt(800));
    }
}
