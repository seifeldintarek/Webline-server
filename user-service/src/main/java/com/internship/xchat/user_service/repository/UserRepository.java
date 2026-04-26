package com.internship.xchat.user_service.repository;

import com.internship.xchat.user_service.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    @Query(
       value = "SELECT * FROM users u" +
      " WHERE (:email IS NULL OR LOWER(u.email) like LOWER(concat('%',:email,'%')) )" +
         " AND (:number IS NULL OR LOWER(u.mobile_phone) like LOWER(concat('%',:number,'%')) )" +
         " AND (:name IS NULL OR LOWER(u.first_name) like LOWER(concat('%',:name,'%')) " +
               "OR LOWER(u.last_name) like LOWER(concat('%',:name,'%')) )",
    nativeQuery = true)
    Page<User> search(@Param("name") String name, @Param("email") String email, @Param("number") String number, Pageable pageable);

    @Query(value = "UPDATE users " +
            "SET image = :image " +
            "WHERE id = :id",
            nativeQuery = true)
    void updateUserImage(@Param("id") Long id, @Param("image") String image);

    @Query(nativeQuery = true,
    value = "Select u.image from users u where id = :id")
    String findImageByid(@Param("id") Long id);
}
