package com.zero.ioshop.accountservice.domain;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
/**
 * creates role model and entity in database using jpa
 */
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
