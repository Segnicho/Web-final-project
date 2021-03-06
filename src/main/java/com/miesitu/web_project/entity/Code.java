package com.miesitu.web_project.entity;

import java.util.Collection;


import javax.persistence.*;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Code {
    @Id
    private long code;

    private boolean status = true;
    

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
        name = "code_role",
        joinColumns = @JoinColumn(name = "code", referencedColumnName = "code"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId")
    )
    private Collection<Role> codeRole;

}
