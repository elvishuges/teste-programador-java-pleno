package com.api.desafiobackend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.desafiobackend.entity.*;;

public interface OrderedRepository extends JpaRepository<Ordered, Long> {
    @Query("SELECT o FROM Ordered o" + " LEFT JOIN FETCH o.products p" + " WHERE :includeProducts = true")
    Set<Ordered> findAll(boolean includeProducts);

}
