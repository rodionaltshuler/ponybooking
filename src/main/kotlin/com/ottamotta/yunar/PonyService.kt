package com.ottamotta.yunar

import com.ottamotta.yunar.exceptions.CantReturnPonyException
import com.ottamotta.yunar.exceptions.NoPonyAvailableException
import com.ottamotta.yunar.exceptions.PonyDoesntExistsException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class PonyService @Autowired constructor(private val repository: AppRepository) {

    fun getStatus(id: String): Pony {
        return repository.findById(id).orElseThrow { PonyDoesntExistsException() }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun bookPony(id: String): Pony {

        val pony = repository.findById(id).orElseThrow { PonyDoesntExistsException() }

        if (pony.isAvailable && pony.isRecharged()) {
            return repository.save(pony.copy(isAvailable = false))
        }

        throw NoPonyAvailableException()
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun returnPony(id: String): Pony {
        val pony = repository.findById(id).orElseThrow { PonyDoesntExistsException() }

        if (pony.isAvailable) {
            throw CantReturnPonyException()
        }

        return repository.save(pony.copy(
                isAvailable = true,
                returnedTimestamp = System.currentTimeMillis()))

    }

    fun resetPony(name: String) {
        repository.findById(name)
                .map{it.copy(isAvailable = true, returnedTimestamp = -1) }
                .ifPresent{ repository.save(it) }
    }
}
