package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.activityrecommender.entity.UserIntervals;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.repository.UserIntervalsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserIntervalService {

    private final UserIntervalsRepository userIntervalsRepository;

    public UserIntervals getBy(final Long id) {
        return userIntervalsRepository.findById(id).orElseThrow(() ->  {
            throw new GenericException("UserInterval by id " + id + " not found!");
        } );
    }

    public UserIntervals save(final UserIntervals userInterval) {
        return userIntervalsRepository.save(userInterval);
    }

    public void deleteBy(final Long id) {
        userIntervalsRepository.deleteById(id);
    }

    public List<UserIntervals> getAll() {
        return userIntervalsRepository.findAll();
    }

}
