package Football_Matches.Football.Controller;

import Football_Matches.Football.Service.FootballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FootballController {

    private final FootballService footballService;


    @Autowired
    public FootballController(FootballService footballService) {
        this.footballService = footballService;
    }

    @GetMapping("/fetch_draw_matches")
    public ResponseEntity<Integer> getDrawnMatchesCountForYear(@RequestParam int year) throws Exception {
        try {
            int drawnMatchesCount = footballService.getDrawnMatchesCountWithoutDelay(year);
            return ResponseEntity.ok(drawnMatchesCount);
        } catch (Exception e) {
            throw new Exception("exception is : " + e);
        }

    }

    @GetMapping("/fetch_draw_matches_With_delay")
    public ResponseEntity<Integer> getDrawnMatchesCountForYearWithDelay(@RequestParam int year) throws Exception {
        try {
            int drawnMatchesCount = footballService.getDrawnMatchesCountWithDelay(year);
            return ResponseEntity.ok(drawnMatchesCount);
        } catch (Exception e) {
            throw new Exception("exception is : " + e);
        }

    }

    @GetMapping("/fetch_football_data")
    public ResponseEntity<String> fetchFootballDataForYear(@RequestParam int year) {
        String responseData = footballService.fetchDataForYear(year);
        return ResponseEntity.ok(responseData);
    }

}
