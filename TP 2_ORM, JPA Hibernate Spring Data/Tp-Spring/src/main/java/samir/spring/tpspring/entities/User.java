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
@Table(name = "USERS")
@Data @AllArgsConstructor @NoArgsConstructor
@ToString
public class User {
    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    @Column(name = "USER_NAME",unique = true, length = 20)
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();
}
