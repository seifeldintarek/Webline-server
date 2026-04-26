package com.internship.xchat.user_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
        @SequenceGenerator(sequenceName = "user_sequence", name = "user_sequence", allocationSize = 1)
        @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
        @Id
        private Long id;
        @NotNull
        @NotBlank
        @Email
        @Size(max = 50)
        @Column(length = 50, unique = true)
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email is not valid")
        private String email;
        @Size(min = 10)
        @NotNull
        @NotBlank
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
        @NotNull
        @NotBlank
        @JsonProperty(access = JsonProperty.Access.READ_WRITE)
        private String firstName;
        @NotNull
        @NotBlank
        @JsonProperty(access = JsonProperty.Access.READ_WRITE)
        private String lastName;
        @Column(length = 11)
        @Pattern(regexp = "^01[0125][0-9]{8}$", message = "mobile phone must be 11 digits and starts with 01")
        private String mobilePhone;
        @Transient
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private String fullName;
        private String image;
        public String getFullName() {
                return this.firstName + " " + this.lastName;
        }

        public void setFullName(String fullName) {
                this.fullName = fullName;
        }

}
