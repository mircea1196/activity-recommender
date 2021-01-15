package ro.unibuc.fmi.activityrecommender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.activityrecommender.model.AddAdminModel;
import ro.unibuc.fmi.activityrecommender.model.ChangePasswordModel;
import ro.unibuc.fmi.activityrecommender.model.UserModel;
import ro.unibuc.fmi.activityrecommender.service.UserService;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/details")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserModel> getUserDetails(final Principal principal) throws UserPrincipalNotFoundException {
        return ResponseEntity.ok(userService.getCurrentUser(principal));
    }

    @GetMapping("/ban")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> banUser(@RequestParam("id") final Long userId) {
        return ResponseEntity.ok(userService.banUser(userId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/change-password")
    @PreAuthorize("hasRole('USER')")
    public void changePassword(@RequestBody ChangePasswordModel changePasswordModel, final Principal principal) {
        userService.changePassword(principal, changePasswordModel);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/edit-profile")
    @PreAuthorize("hasRole('USER')")
    public void editProfile(@RequestBody UserModel userModel, final Principal principal) {
        userService.editProfile(principal, userModel);
    }

    @PostMapping("/edit-profile-admin/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void editProfileAdmin(@RequestBody UserModel userModel, @PathVariable("id") final Long id) {
        userService.editProfileAdmin(userModel, id);
    }

    @GetMapping("/profile/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") final Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserByIdForAdmin(@PathVariable("id") final Long userId) {
        return ResponseEntity.ok(userService.getUserByIdForAdmin(userId));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsersPaginated(final Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsersPaginated(pageable));
    }

    @GetMapping("/username")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getByUsername(@RequestParam("username") final String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @GetMapping("/email")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getByEmail(@RequestParam("email") final String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @GetMapping("/phone")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getByPhoneNumber(@RequestParam("phone") final String phone) {
        return ResponseEntity.ok(userService.getByPhoneNumber(phone));
    }

    @GetMapping("/admins")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAdminsAndMods() {
        return ResponseEntity.ok(userService.getAdminAndModerator());
    }


    @PostMapping("/add-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAdmin(@RequestBody final AddAdminModel addAdminModel) {
        return ResponseEntity.ok(userService.addAdmin(addAdminModel));
    }
}
