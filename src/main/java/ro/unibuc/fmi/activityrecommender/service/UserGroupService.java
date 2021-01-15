package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.activityrecommender.entity.Group;
import ro.unibuc.fmi.activityrecommender.entity.UserGroup;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.model.UserGroupModel;
import ro.unibuc.fmi.activityrecommender.repository.UserGroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGroupService {

    private final UserGroupRepository userGroupRepository;
    private final UserService userService;

    public UserGroup getBy(final Long id) {
        return userGroupRepository.findById(id).orElseThrow(() ->
                new GenericException("UserGroup by id " + id + " not found!"));
    }

    public UserGroup save(final UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }

    public void deleteBy(final Long id) {
        userGroupRepository.deleteById(id);
    }

    public List<UserGroup> getAll() {
        return userGroupRepository.findAll();
    }


    public UserGroup addUser(final UserGroupModel userGroupModel) {
        UserGroup userGroup = UserGroup.builder()
                .groupId(userGroupModel.getGroupId())
                .userId(userService.getByUsername(userGroupModel.getUsername()).getId())
                .build();

        return userGroup;
    }

    public List<String> getUsers(final Long groupId) {
        List<String> userList = new ArrayList<>();
        userGroupRepository.getAllByGroupId(groupId).forEach(user ->  {
            String username = userService.getUserById(user.getUserId()).getUsername();
            userList.add(username);
        });

        return userList;
    }
}
