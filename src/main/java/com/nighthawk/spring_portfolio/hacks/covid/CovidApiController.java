package com.nighthawk.spring_portfolio.hacks.covid;

import java.io.IOException;
import java.net.URI;
import java.util.Date;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/covid")
public class CovidApiController {
    private JSONObject body; // last run result
    private HttpStatus status; // last run status
    String last_run = null; // last run day of month

    // GET Covid 19 Stats
    @GetMapping("/daily")
    public ResponseEntity<JSONObject> getCovid() {
        Date date = new Date();
        String today = date.toString().substring(8, 10);
        if (last_run == null || !last_run.equals(today)) {
            last_run = today;
            try {
                CloseableHttpClient client = HttpClients.createDefault();
                HttpGet request = new HttpGet("https://api.covidtracking.com/v1/us/current.json");
                CloseableHttpResponse response = client.execute(request);
                String json = EntityUtils.toString(response.getEntity());
                JSONParser parser = new JSONParser();
                body = (JSONObject) parser.parse(json);
                status = HttpStatus.OK;
            } catch (IOException | ParseException | org.json.simple.parser.ParseException e) {
                body = new JSONObject();
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
        return ResponseEntity.status(status).body(body);
    }
}