package de.Hashem.bigbazar.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
*
* Klasse für Kunde Role
*/

@Entity
public class Role {
    @Id
    private Integer rechteId;

    private String rechteName;

    @OneToMany(mappedBy = "role", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    private Set<CustomerRole> kundenRechte = new HashSet<>();

    public Integer getRechteId() {
        return rechteId;
    }

    public void setRechteId(Integer rechteId) {
        this.rechteId = rechteId;
    }

    public String getRechteName() {
        return rechteName;
    }

    public void setRechteName(String rechteName) {
        this.rechteName = rechteName;
    }

    public Set<CustomerRole> getKundenRechte() {
        return kundenRechte;
    }

    public void setKundenRechte(Set<CustomerRole> kundenRechte) {
        this.kundenRechte = kundenRechte;
    }
}
