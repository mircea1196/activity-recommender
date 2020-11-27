package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.activityrecommender.entity.Interval;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.repository.IntervalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IntervalService {

    private final IntervalRepository intervalRepository;

    public Interval getBy(final Long id) {
        return intervalRepository.findById(id).orElseThrow(() ->  {
            throw new GenericException("Interval by id " + id + " not found!");
        } );
    }

    public Interval save(final Interval interval) {
        return intervalRepository.save(interval);
    }

    public void deleteBy(final Long id) {
        intervalRepository.deleteById(id);
    }

    public List<Interval> getAll() {
        return intervalRepository.findAll();
    }


}
