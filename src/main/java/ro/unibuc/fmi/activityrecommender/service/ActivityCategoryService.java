package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.activityrecommender.entity.ActivityCategory;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.repository.ActivityCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityCategoryService {

    private final ActivityCategoryRepository activityCategoryRepository;

    public ActivityCategory getBy(final Long id) {
        return activityCategoryRepository.findById(id).orElseThrow(() ->  {
            throw new GenericException("ActivityCategory by id " + id + " not found!");
        } );
    }

    public ActivityCategory save(final ActivityCategory activityCategory) {
        return activityCategoryRepository.save(activityCategory);
    }

    public void deleteBy(final Long id) {
        activityCategoryRepository.deleteById(id);
    }

    public List<ActivityCategory> getAll() {
        return activityCategoryRepository.findAll();
    }
}
