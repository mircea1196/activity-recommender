package ro.unibuc.fmi.activityrecommender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.activityrecommender.entity.Group;
import ro.unibuc.fmi.activityrecommender.service.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/groups")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Group> createGroup(@RequestBody Group group)  {
        Group result = groupService.save(group);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/groups")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group) {
        Group result = groupService.save(group);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping("/groups")
    @PreAuthorize("hasRole('USER')")
    public List<Group> getAllGroups() {
        return groupService.getAll();
    }

    @GetMapping("/groups/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Group> getGroup(@PathVariable Long id) {
        Group group = groupService.getBy(id);
        return ResponseEntity.ok(group);
    }

    @DeleteMapping("/groups/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
