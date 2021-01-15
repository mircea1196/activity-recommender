package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.unibuc.fmi.activityrecommender.entity.Role;
import ro.unibuc.fmi.activityrecommender.entity.Users;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.mapper.UserMapper;
import ro.unibuc.fmi.activityrecommender.model.*;
import ro.unibuc.fmi.activityrecommender.repository.UserRepository;
import ro.unibuc.fmi.activityrecommender.util.RandomPasswordUtil;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private static final String EMAIL_SUBJECT = "Resetare parola";
    private static final String MESSAGE = "Noua ta parola este: ";

    public UserModel getCurrentUser(final Principal principal) throws UserPrincipalNotFoundException {
        Users user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UserPrincipalNotFoundException("Nu a fost gasit in baza de date acest utilizator!"));
        return UserMapper.toModel(user);
    }


    @Transactional
    public void resetPassword(final String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizator cu acest email nu exista!"));

        String generatedPassword = RandomPasswordUtil.generate();

        EmailModel emailModel = EmailModel.builder()
                .receivers(email)
                .subject(EMAIL_SUBJECT)
                .message(MESSAGE + generatedPassword)
                .build();

        emailService.send(emailModel);
        user.setPassword(passwordEncoder.encode(generatedPassword));
        userRepository.save(user);
    }

    public void changePassword(final Principal principal, final ChangePasswordModel changePasswordModel) {
        Users user = userRepository.findByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException("Acest utilizator nu exista!"));

        if (BCrypt.checkpw(changePasswordModel.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordModel.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new GenericException("Parola introdusa nu corespunde contului dvs!");
        }
    }

    public void editProfileAdmin(final UserModel userModel, final Long id) {
        Users user = userRepository.findById(id).orElseThrow(()
                -> new UsernameNotFoundException("Acest utilizator nu exista!"));

        user.setPhoneNumber(userModel.getPhoneNumber());
        user.setEmail(userModel.getEmail());
        user.setLocation(userModel.getLocation());
        user.setDescription(userModel.getDescription());

        userRepository.save(user);
    }

    public void editProfile(final Principal principal, final UserModel userModel) {
        Users user = userRepository.findByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException("Acest utilizator nu exista!"));

        if (userRepository.findByEmail(userModel.getEmail()).isPresent() &&
                !user.getEmail().equals(userModel.getEmail())) {
            throw new GenericException("Acest email exista deja in baza noastra de date!");
        }

        user.setPhoneNumber(userModel.getPhoneNumber());
        user.setEmail(userModel.getEmail());
        user.setLocation(userModel.getLocation());

        userRepository.save(user);
    }

    public UserModel getUserById(final Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(()
                -> new GenericException("Utilizator cu id " + userId + " nu exista!"));

        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .profileImageUrl(user.getProfileImageUrl())
                .createdAt(user.getCreatedOn())
                .description(user.getDescription())
                .location(user.getLocation())
                .name(user.getName())
                .showPhone(user.getShowPhone())
                .rating(user.getRating())
                .enabled(user.getEnabled())
                .build();
    }

    public UserModel getUserByIdForAdmin(final Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(()
                -> new GenericException("Utilizator cu id " + userId + " nu exista!"));

        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .profileImageUrl(user.getProfileImageUrl())
                .createdAt(user.getCreatedOn())
                .description(user.getDescription())
                .location(user.getLocation())
                .name(user.getName())
                .showPhone(user.getShowPhone())
                .rating(user.getRating())
                .enabled(user.getEnabled())
                .email(user.getEmail())
                .build();
    }

    public Page<UserModel> getAllUsersPaginated(final Pageable pageable) {
        return userRepository.findAll(pageable).map(UserMapper::toModel);
    }

    public UserModel getByUsername(final String username) {
        Users user = userRepository.findByUsername(username).orElseThrow(() ->
                new GenericException("Utilizatorul cu numele de utilizator introdus nu exista!"));

        return UserMapper.toModel(user);
    }

    public UserModel getByEmail(final String email) {
        Users user = userRepository.findByEmail(email).orElseThrow(() ->
                new GenericException("Utilizatorul cu email-ul introdus nu exista!"));

        return UserMapper.toModel(user);
    }

    public UserModel getByPhoneNumber(final String phone) {
        Users user = userRepository.getByPhoneNumber(phone).orElseThrow(() ->
                new GenericException("Utilizatorul cu numarul de telefon introdus nu exista!"));

        return UserMapper.toModel(user);
    }

    public UserModel banUser(final Long userId) {
        Users user = userRepository.getById(userId);
        user.setEnabled(false);
        return UserMapper.toModel(userRepository.save(user));

    }

    public Set<UserModel> getAdminAndModerator() {
        Role adminRole = new Role(2, ERoles.ROLE_MODERATOR);
        Role moderatorRole = new Role(3, ERoles.ROLE_ADMIN);
        Set<UserModel> adminsAndModerators = userRepository.getAllByRoles(adminRole)
                .stream()
                .map(UserMapper::toModel)
                .collect(Collectors.toSet());
        adminsAndModerators.addAll(userRepository.getAllByRoles(moderatorRole)
                .stream()
                .map(UserMapper::toModel)
                .collect(Collectors.toSet()));
        return adminsAndModerators;
    }

    public UserModel addAdmin(final AddAdminModel addAdminModel) {
        Users user = userRepository.findByUsername(addAdminModel.getUsername()).orElseThrow(()
                -> new GenericException("Utilizatorul " + addAdminModel.getUsername() + " nu exista !"));

        Role userRole = new Role(1, ERoles.ROLE_USER);
        addAdminModel.addRole(userRole);
        user.setRoles(addAdminModel.getRoles());
        userRepository.save(user);
        return UserMapper.toModel(user);
    }

}
