package ro.unibuc.fmi.activityrecommender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.unibuc.fmi.activityrecommender.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResetPasswordController {

    private final UserService userService;

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody final String email) {
        userService.resetPassword(email);
        return ResponseEntity.ok(email);
    }


}
