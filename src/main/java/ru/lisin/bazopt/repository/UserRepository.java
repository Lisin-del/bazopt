package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(@Param("email") String email);
}
