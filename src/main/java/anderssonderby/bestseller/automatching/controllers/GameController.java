package anderssonderby.bestseller.automatching.controllers;

import anderssonderby.bestseller.automatching.AlreadyExistsException;
import anderssonderby.bestseller.automatching.models.Game;
import anderssonderby.bestseller.automatching.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/game")
    public ResponseEntity<Game> AddGame(@RequestBody @Valid Game game) throws AlreadyExistsException {
        var addedGame = gameService.addGame(game);

        return ResponseEntity.ok(addedGame);
    }

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getAllGames() {
        var games = gameService.getAllGames();

        return ResponseEntity.ok(games);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyExistsException.class)
    public String handleExistingException(AlreadyExistsException ex) {
        return ex.getMessage();
    }

}
