package de.Hashem.bigbazar.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
* Klasse für Produkten und Kunden 
*/

@Entity
public class Bookmark extends BasicEntity{
    @OneToOne
    private Customer customer;

    @OneToMany
    private List<Product> products = new ArrayList<>();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
