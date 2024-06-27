package net.samir.tp3_hospital.security.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.CodePointBuffer;

import java.util.List;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @Id
    private String userId;
    @Column(unique=true)
    private String username;
    private String password;
    private String email;
    @ManyToMany(fetch= FetchType.EAGER)
    private List<AppRole> roles;

}