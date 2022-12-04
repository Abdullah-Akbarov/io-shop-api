package com.zero.ioshop.userservice.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity(name = "users")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
/**
 * this class creates user model and entity in database using jpa
 */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String photo;
    @Column(columnDefinition = "boolean default true")
    private Boolean isActive = true;
    @ManyToMany(cascade = {CascadeType.MERGE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
