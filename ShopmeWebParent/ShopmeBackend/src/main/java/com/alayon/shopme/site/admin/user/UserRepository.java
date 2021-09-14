package com.alayon.shopme.site.admin.user;

import com.alayon.shopme.common.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
