package com.sketchbook.reactivemultitenantservice.repository;

import com.sketchbook.reactivemultitenantservice.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
}
