package de.Hashem.bigbazar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/*
*
* Klasse für Adresse 
*/


@Entity
public class Address extends BasicEntity {
    @Column(name = "country")
    private String country;

    @Column(name = "postalnumber")
    private String postalNumber;

    @Column(name = "housenumber")
    private String houseNumber;

    @Column(name = "streetname")
    private String streetName;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalNumber() {
        return postalNumber;
    }

    public void setPostalNumber(String postalNumber) {
        this.postalNumber = postalNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
