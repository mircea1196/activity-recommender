package ro.unibuc.fmi.activityrecommender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.activityrecommender.entity.UserGroup;
import ro.unibuc.fmi.activityrecommender.model.UserGroupModel;
import ro.unibuc.fmi.activityrecommender.service.UserGroupService;

import java.util.List;

@RestController
@RequestMapping("/api/user_group")
@RequiredArgsConstructor
public class UserGroupController {

    private final UserGroupService userGroupService;

    @PostMapping("/add-user")
    public UserGroup addUser(@RequestBody UserGroupModel userGroupModel) {
        return userGroupService.addUser(userGroupModel);
    }

    @GetMapping("/get-user-for-group/{id}")
    public List<String> getUsers(@PathVariable("id") Long id ) {
        return userGroupService.getUsers(id);
    }

}
