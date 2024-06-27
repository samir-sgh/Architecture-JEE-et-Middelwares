package samir.spring.tpspring.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Roles")
@Data @NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="DESCRIPTION")
    private String desc;
    @Column(length = 20, unique = true)
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JoinTable(name = "USERS_ROLES")
    @ToString.Exclude
    public List<User> users=new ArrayList<>();
}
