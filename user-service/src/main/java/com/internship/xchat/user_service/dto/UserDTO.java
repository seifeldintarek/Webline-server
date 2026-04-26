package com.internship.xchat.user_service.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Size(min = 10)
    private String password;
    private Long id;
    private String firstName;
    private String lastName;
    @NotNull
    @NotBlank
    @Email
    @Column(length = 50)
    @Size(max = 50)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email is not valid")
    private String email;
    private String mobilePhone;
    private String image;

}
