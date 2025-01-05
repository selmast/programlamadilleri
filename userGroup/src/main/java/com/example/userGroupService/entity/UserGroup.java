
package com.example.userGroupService.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "user_group_members", joinColumns = @JoinColumn(name = "user_group_id"))
    @Column(name = "user_id")
    private Set<Long> memberIds = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "user_group_roles", joinColumns = @JoinColumn(name = "user_group_id"))
    @Column(name = "role_id")
    private Set<Long> roleIds = new HashSet<>();
}
