package com.ottamotta.yunar;

import com.ottamotta.yunar.exceptions.CantReturnPonyException;
import com.ottamotta.yunar.exceptions.NoPonyAvailableException;
import com.ottamotta.yunar.exceptions.PonyDoesntExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PonyService {

    private AppRepository repository;

    @Autowired
    public PonyService(AppRepository repository) {
        this.repository = repository;
    }

    public Pony getStatus(String id) {
        return repository.findById(id).orElseThrow(PonyDoesntExistsException::new);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Pony bookPony(String id) {

        Pony pony = repository.findById(id).orElseThrow(PonyDoesntExistsException::new);

        if (pony.isAvailable() && pony.isRecharged()) {
            pony.setAvailable(false);
            repository.save(pony);
            return pony;
        }

        throw new NoPonyAvailableException();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Pony returnPony(String id) {
        Pony pony = repository.findById(id).orElseThrow(PonyDoesntExistsException::new);

        if (pony.isAvailable()) {
            throw new CantReturnPonyException();
        }

        pony.setAvailable(true);
        pony.setReturnedTimestamp(System.currentTimeMillis());
        return pony;
    }
}
