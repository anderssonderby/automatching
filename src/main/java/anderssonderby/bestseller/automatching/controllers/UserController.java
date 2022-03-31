package anderssonderby.bestseller.automatching.controllers;

import anderssonderby.bestseller.automatching.AlreadyExistsException;
import anderssonderby.bestseller.automatching.models.User;
import anderssonderby.bestseller.automatching.models.UserGame;
import anderssonderby.bestseller.automatching.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<User> EnrollUser(@RequestBody @Valid User user) throws AlreadyExistsException {
        var enrolledUser = userService.enrollUser(user);

        return ResponseEntity.ok(enrolledUser);
    }

    @PostMapping("/user/game")
    public ResponseEntity<UserGame> AddGame(String nickname, String game, String level) {
        var addedGame = userService.addGameToUser(nickname, game, level);

        return ResponseEntity.ok(addedGame);
    }

    @PostMapping("/user/credits")
    public ResponseEntity<User> AddCredits(String nickname, int credits) {

        var updatedUser = userService.addCreditsToUser(nickname, credits);

        return ResponseEntity.ok(updatedUser);
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
