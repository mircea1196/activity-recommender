package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.activityrecommender.entity.Users;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Users getBy(final Long id) {
        return userRepository.findById(id).orElseThrow(() ->  {
            throw new GenericException("User by id " + id + " not found!");
        } );
    }

    public Users save(final Users user) {
        return userRepository.save(user);
    }

    public void deleteBy(final Long id) {
        userRepository.deleteById(id);
    }

    public List<Users> getAll() {
        return userRepository.findAll();
    }

}
