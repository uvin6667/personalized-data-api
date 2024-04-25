package com.isource.personalizeddataapi.repository;

import com.isource.personalizeddataapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {


}
