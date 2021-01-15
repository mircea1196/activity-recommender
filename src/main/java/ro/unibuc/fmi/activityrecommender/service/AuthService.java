package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.activityrecommender.entity.Role;
import ro.unibuc.fmi.activityrecommender.entity.Users;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.mapper.UserMapper;
import ro.unibuc.fmi.activityrecommender.model.AuthRequest;
import ro.unibuc.fmi.activityrecommender.model.ERoles;
import ro.unibuc.fmi.activityrecommender.model.JwtResponse;
import ro.unibuc.fmi.activityrecommender.model.UserModel;
import ro.unibuc.fmi.activityrecommender.repository.RoleRepository;
import ro.unibuc.fmi.activityrecommender.repository.UserRepository;
import ro.unibuc.fmi.activityrecommender.security.jwt.JwtUtils;
import ro.unibuc.fmi.activityrecommender.security.service.UserDetailsImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    public static final String SAME_USERNAME = "Un cont asociat cu acest username deja exista!";
    public static final String SAME_EMAIL = "Un cont asociat cu acest email deja exista!";
    public static final String SAME_PHONE = "Un cont asoctiat cu acest numar de telefon deja exista!";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public JwtResponse signIn(final AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JwtResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .phoneNumber(userDetails.getPhoneNumber())
                .profileImageUrl(userDetails.getProfileImageUrl())
                .roles(roles).build();
    }

    public UserModel saveUser(final UserModel userModel) {

        if (userRepository.existsByUsername(userModel.getUsername())) {
            throw new GenericException(SAME_USERNAME);
        } else if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new GenericException(SAME_EMAIL);
        } else if (userRepository.existsByPhoneNumber(userModel.getPhoneNumber())) {
            throw new GenericException(SAME_PHONE);
        }

        Users user = UserMapper.toEntity(userModel);
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERoles.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: role is not found.")));

        user.setRoles(roles);
        user.setShowPhone(true);
        userRepository.save(user);

        return userModel;
    }


}
