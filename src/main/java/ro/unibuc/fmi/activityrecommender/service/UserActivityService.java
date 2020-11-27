package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.activityrecommender.entity.UserActivity;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.repository.UserActivityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;

    public UserActivity getBy(final Long id) {
        return userActivityRepository.findById(id).orElseThrow(() ->  {
            throw new GenericException("UserActivity by id " + id + " not found!");
        } );
    }

    public UserActivity save(final UserActivity userActivity) {
        return userActivityRepository.save(userActivity);
    }

    public void deleteBy(final Long id) {
        userActivityRepository.deleteById(id);
    }

    public List<UserActivity> getAll() {
        return userActivityRepository.findAll();
    }


}
