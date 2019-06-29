package com.ottamotta.yunar;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AppRepository extends CrudRepository<Pony, String> {

}
