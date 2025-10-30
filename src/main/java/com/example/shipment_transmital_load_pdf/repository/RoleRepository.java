package com.example.shipment_transmital_load_pdf.repository;

import com.example.shipment_transmital_load_pdf.entity.User;
import com.example.shipment_transmital_load_pdf.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Integer> {
    User findUsersByRole(String role);

   // @Query("Select * from User us where us.role= :role")
    List<User> findAllUsersByRole(String role );
}
