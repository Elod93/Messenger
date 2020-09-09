package org.messenger.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Authority {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String roleName;
    @ManyToMany(mappedBy = "authorities",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SecurityUser> securityUsers= new HashSet<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<SecurityUser> getSecurityUsers() {
        return securityUsers;
    }

    public void setSecurityUsers(Set<SecurityUser> securityUsers) {
        this.securityUsers = securityUsers;
    }
}
