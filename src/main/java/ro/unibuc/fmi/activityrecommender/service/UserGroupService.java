package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.activityrecommender.entity.UserGroup;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.repository.UserGroupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGroupService {

    private final UserGroupRepository userGroupRepository;

    public UserGroup getBy(final Long id) {
        return userGroupRepository.findById(id).orElseThrow(() ->  {
            throw new GenericException("UserGroup by id " + id + " not found!");
        } );
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


}
