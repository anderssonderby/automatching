package anderssonderby.bestseller.automatching.controllers;

import anderssonderby.bestseller.automatching.models.MatchingPlayersDTO;
import anderssonderby.bestseller.automatching.services.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<MatchingPlayersDTO>> SearchForPlayers(String game, String level) {

        var matchedPlayers = searchService.searchForPlayers(game, level);

        return ResponseEntity.ok(matchedPlayers);
    }

    @GetMapping("/search/top")
    public ResponseEntity<List<MatchingPlayersDTO>> getTopPlayers() {

        var topPlayers = searchService.getTopPlayers();

        return ResponseEntity.ok(topPlayers);
    }
}
