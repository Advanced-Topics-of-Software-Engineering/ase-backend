package edu.tum.ase.ase23.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {
    @Id
    private String id;

    private RoleEnum roleEnum;

    public Role() {
    }

    public Role(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public String getId() {
        return id;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}
