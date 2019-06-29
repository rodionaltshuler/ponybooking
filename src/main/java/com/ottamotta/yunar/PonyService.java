package com.ottamotta.yunar;

import com.ottamotta.yunar.exceptions.CantReturnPonyException;
import com.ottamotta.yunar.exceptions.NoPonyAvailableException;
import com.ottamotta.yunar.exceptions.PonyDoesntExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;

@Service
public class PonyService {

    private AppRepository repository;

    @Autowired
    public PonyService(AppRepository repository) {
        this.repository = repository;
    }

    @Lock(LockModeType.PESSIMISTIC_READ)
    public Pony getStatus(String id) {
        return repository.findById(id).orElseThrow(PonyDoesntExistsException::new);
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Pony bookPony(String id) {

        Pony pony = repository.findById(id).orElseThrow(PonyDoesntExistsException::new);

        if (pony.isAvailable() && pony.isRecharged()) {
            pony.setAvailable(false);
            repository.save(pony);
            return pony;
        }

        throw new NoPonyAvailableException();
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
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
