package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
