package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.activityrecommender.entity.Activity;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.repository.ActivityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public Activity getBy(final Long id) {
        return activityRepository.findById(id).orElseThrow(() ->  {
            throw new GenericException("Activity by id " + id + " not found!");
        } );
    }

    public Activity save(final Activity activity) {
        return activityRepository.save(activity);
    }

    public void deleteBy(final Long id) {
        activityRepository.deleteById(id);
    }

    public List<Activity> getAll() {
        return activityRepository.findAll();
    }


}
