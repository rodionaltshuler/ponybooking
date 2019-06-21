package com.ottamotta.yunar;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface AppRepository extends CrudRepository<Entity, Long> {

    public List<Entity> findAllByTimestampBetweenOrderByTimestamp(long from, long to);

}
