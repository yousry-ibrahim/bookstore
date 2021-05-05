package com.yousry.bookstore.dal.repository;

import com.yousry.bookstore.dal.domainobject.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
/**
 * Spring JPA DATA repository for USER entity
 * @author : yousry
 * @version : 1.0
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
