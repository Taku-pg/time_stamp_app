package org.example.timestampapp.Service;

import org.example.timestampapp.Model.Entity.Status;
import org.example.timestampapp.Repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StatusService {
    private final StatusRepository statusRepository;
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status getStatus(String type) {
        return statusRepository
                .findStatusByType(type)
                .orElseThrow(()-> new NoSuchElementException("No status found for type " + type));
    }
}
