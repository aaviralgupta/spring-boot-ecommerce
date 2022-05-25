package com.web.ecommerce.dao;

import com.web.ecommerce.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface StateRepository extends JpaRepository<State, Long> {
}
