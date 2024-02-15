package de.Hashem.bigbazar.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*
*
* Klasse für Kunde Information
* gibt paar Funktionen nicht benutzt
*/

@Entity
public class Customer extends BasicEntity implements UserDetails {
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private PersonalData personalData;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CustomerRole> customerRoles = new HashSet<>();

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<CustomerRole> getCustomerRoles() {
        return customerRoles;
    }

    public void setCustomerRoles(Set<CustomerRole> customerRoles) {
        this.customerRoles = customerRoles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (CustomerRole customerRole : this.customerRoles) {
            authorities.add(new Authority(customerRole.getRole().getRechteName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
