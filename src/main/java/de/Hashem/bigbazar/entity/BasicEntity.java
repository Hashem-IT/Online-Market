package de.Hashem.bigbazar.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/*
*
* Klasse für Id Kunden
*/

@MappedSuperclass
public abstract class BasicEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        final BasicEntity other = (BasicEntity) o;
        if (!Objects.equals(getId(), other.getId()))
            return false;
        return true;
    }

    @Override
    public int hashCode(){
        if (getId()==null)
            return 0;
        else
            return getId().hashCode();
    }
}
