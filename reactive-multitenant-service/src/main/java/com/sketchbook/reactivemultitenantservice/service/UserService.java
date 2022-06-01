package com.sketchbook.reactivemultitenantservice.service;

import com.sketchbook.reactivemultitenantservice.entity.User;
import com.sketchbook.reactivemultitenantservice.repository.UserRepository;
import com.sketchbook.reactivemultitenantservice.repository.UserTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserTemplateRepository templateRepository;

    public Mono<User> getUser(Integer id) {
        return templateRepository.findById(id);
//        example with multiple mono
/*        Mono<User> entity1 = templateRepository.findById(id);
        Mono<User> entity2 = templateRepository.findById(id);
        return Mono.zip(entity1, entity2, (user1, user2) -> {
            System.out.println(user1);
            System.out.println(user2);
            return user1;
        });*/
    }

}
