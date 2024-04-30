package io.gatling.demo;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class RecordedSimulation extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://neoload-api.saas.neotys.com")
    .inferHtmlResources()
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate, br");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("Cache-Control", "no-cache"),
    Map.entry("accountToken", "0d1dd475f94912dec1f5a187e86185953e5a8e64183b84c4")
  );
/*
-------------------A login endpoint for authentication------------------------------------------

*  private Map<CharSequence, String> headers_1 = Map.ofEntries(
            Map.entry("Cache-Control", "no-cache"),
    );
    *
    *
    *
    *
 private ScenarioBuilder scn = scenario("login")
    .exec(
      http("request_0")
        .get("/login")
        .queryParam("User", "admin")
        .queryParam("Password", "admin")
        .headers(headers_1).check(jsonPath("$..token").find().saveAs("accessToken"))
    );
-----------------------ping endpoint ------------------------------------------------------------

     private ScenarioBuilder auth = scenario("UseAuth")
          .exec(
                  http("headers_auth")
                          .get("/ping")
                          .headers(headers_1)
                          .header("token","${accessToken}")
          );
--------------------------------------------------------------------------------------------------
*  */


  private ScenarioBuilder scn = scenario("UseParams")
    .exec(
      http("request_0")
        .get("/v3/workspaces?allWorkspaces=true&limit=50")
        .headers(headers_0).check(jsonPath("$..id").find().saveAs("accessToken"))
    );


  //------------------------use the extracted value from request----------------------------------

  private ScenarioBuilder auth = scenario("UseAuth")
          .exec(
                  http("headers_auth")
                          .get("/v3/workspaces/")
                          .headers(headers_0)
                          .queryParam("id", "#{accessToken}")
          );

  //---------------------------------Injectors with assertion---------------------------------------
  {
	  setUp(
              scn.injectOpen(atOnceUsers(1)).protocols(httpProtocol),
              auth.injectOpen(atOnceUsers(1)).protocols(httpProtocol)
      ).assertions(global().responseTime().max().lt(800));
  }
}
