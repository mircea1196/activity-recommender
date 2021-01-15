package ro.unibuc.fmi.activityrecommender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.activityrecommender.model.AuthRequest;
import ro.unibuc.fmi.activityrecommender.model.UserModel;
import ro.unibuc.fmi.activityrecommender.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.signIn(authRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserModel> signUp(@RequestBody final UserModel userModel) {
        return ResponseEntity.ok(authService.saveUser(userModel));
    }

}
