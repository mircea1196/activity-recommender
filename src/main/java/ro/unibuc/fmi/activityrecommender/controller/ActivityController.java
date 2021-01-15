package ro.unibuc.fmi.activityrecommender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.activityrecommender.entity.Activity;
import ro.unibuc.fmi.activityrecommender.service.ActivityService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/activities")
    public ResponseEntity<Activity> createActivity(@Valid @RequestBody Activity activity) throws URISyntaxException {

        Activity result = activityService.save(activity);
        return ResponseEntity.created(new URI("/api/activities/" + result.getId()))
                .body(result);
    }

    @PutMapping("/activities")
    public ResponseEntity<Activity> updateActivity(@Valid @RequestBody Activity activity) throws URISyntaxException {
        Activity result = activityService.save(activity);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/activities")
    public List<Activity> getAllActivities() {
        return activityService.getAll();
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.getBy(id));

    }

    @DeleteMapping("/activities/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteBy(id);
        return ResponseEntity.noContent().build();
    }


}
