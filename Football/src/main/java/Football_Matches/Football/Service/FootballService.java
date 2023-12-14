package Football_Matches.Football.Service;

import Football_Matches.Football.FootballEntity.FootballApiResponses;
import Football_Matches.Football.FootballEntity.FootballData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FootballService {

    private final RestTemplate restTemplate;

    @Autowired
    public FootballService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public int getDrawnMatchesCountWithoutDelay(int year) {
        String url = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year + "&page=1";
        ResponseEntity<FootballApiResponses> responseEntity = restTemplate.getForEntity(url, FootballApiResponses.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            FootballApiResponses response = responseEntity.getBody();
            return countDrawnMatches(response);
        } else {
            System.err.println("Failed to fetch data. HTTP Status Code: " + responseEntity.getStatusCodeValue());
            return -1;
        }
    }

    public int getDrawnMatchesCountWithDelay(int year) {
        String url = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year + "&page=1";
        ResponseEntity<FootballApiResponses> responseEntity = restTemplate.getForEntity(url, FootballApiResponses.class);

        try {
            Thread.sleep((long) (3000 + Math.random() * 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            FootballApiResponses response = responseEntity.getBody();
            return countDrawnMatches(response);
        } else {
            System.err.println("Failed to fetch data. HTTP Status Code: " + responseEntity.getStatusCodeValue());
            return -1;
        }
    }

    private int countDrawnMatches(FootballApiResponses response) {
        int drawnMatches = 0;

        for (FootballData match : response.getData()) {
            int team1Goals = Integer.parseInt(match.getTeam1goals());
            int team2Goals = Integer.parseInt(match.getTeam2goals());

            if (team1Goals == team2Goals) {
                drawnMatches++;
            }
        }

        return drawnMatches;
    }

    public String fetchDataForYear(int year) {
        String apiUrl = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year +"&page=1";
        return restTemplate.getForObject(apiUrl, String.class);

    }

}
