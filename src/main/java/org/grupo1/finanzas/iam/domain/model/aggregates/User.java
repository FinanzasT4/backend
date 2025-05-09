package org.grupo1.finanzas.iam.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.grupo1.finanzas.iam.domain.model.entities.Role;
import org.grupo1.finanzas.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable( name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
        this.roles = new HashSet<>();
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
    }

    public User(String username, String password, List<Role> roles) {
        this(username,password);
        addRoles(roles);
    }
    public User addRoles(Role role) {
        this.roles.add(role);
        return  this;
    }

    public User addRoles(List<Role> roles) {
        var validateRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validateRoleSet);
        return  this;
    }
}
