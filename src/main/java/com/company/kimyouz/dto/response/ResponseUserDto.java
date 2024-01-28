package com.company.kimyouz.dto.response;


import com.company.kimyouz.entity.Authorities;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUserDto implements UserDetails {
    private Integer userId;

    @NotNull(message = "Firstname cannot be null")
    @NotEmpty(message = "Firstname cannot be empty")
    private String firstname;

    @NotBlank(message = "Lastname cannot be null or empty")
    private String lastname;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email cannot be null or empty")
    private String email;

    @NotBlank(message = "Password cannot be null or empty")
    @Size(min = 8, max = 16, message = "Incorrect password size")
    private String password;

//    @Max(value = 150, message = "Age must be less than 150")
//    @Min(value = 1, message = "Age must be more than 1")
//    @NotNull(message = "Age cannot be null")
    private Integer age;

    private String username;

    private boolean enabled;

    private List<Authorities> authoritiesSet;

    private Set<ResponseCardDto> cards;



    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.of(authoritiesSet).map(
                        authorities -> authorities.stream().map(
                                a -> new SimpleGrantedAuthority(a.getAuthority())
                        ).toList())
                .orElse(new ArrayList<>());
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }




}
