package com.rahi.springrediscaching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    public UserController( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }


    @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser( @PathVariable String userId ) {
        LOG.info("Getting user with ID {}.", userId);
        Optional<User> byId = userRepository.findById(Long.valueOf(userId));
        return byId.orElse(null);
    }

    @CachePut(value = "users", key = "#user.id")
    @PutMapping("/update")
    public User updatePersonByID( @RequestBody User user ) {
        userRepository.save(user);
        return user;
    }

    @CacheEvict(value = "users", allEntries = true)
    @DeleteMapping("/{userId}")
    public void deleteUserByID( @PathVariable Long userId ) {
        LOG.info("deleting person with id {}", userId);
        userRepository.deleteById(userId);
    }
}
