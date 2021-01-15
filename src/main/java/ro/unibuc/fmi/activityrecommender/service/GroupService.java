package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.activityrecommender.entity.Group;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.repository.GroupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public Group getBy(final Long id) {
        return groupRepository.findById(id).orElseThrow(() ->
             new GenericException("Group by id " + id + " not found!")
         );
    }

    public Group save(final Group group) {
        return groupRepository.save(group);
    }

    public void delete(final Long id) {
        groupRepository.deleteById(id);
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

}
