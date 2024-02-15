package de.Hashem.bigbazar.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/*
*
* Klasse für Einkaufen Funktionen 
*/

@Entity
public class ShoppingCart extends BasicEntity {

    @OneToOne
    private Customer customer;

    @ElementCollection
    private Map<Product, Long> products = new HashMap<>();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Product, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Long> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        }
        else {
            products.put(product, 1L);
        }
    }

    public void addProduct(Product product, Long amount) {
        if (products.containsKey(product)) {
            products.replace(product, amount);
        }
        products.put(product, amount);
    }

    public void remove(Product product) {
        if (products.containsKey(product)) {
            products.remove(product);
        }
        return;
    }

    public BigDecimal claculateSum() {
        if (products.isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal temp = product.getBrutto().multiply(BigDecimal.valueOf(products.get(product)));
            sum = sum.add(temp);
        }
        return sum;
    }

    public void clearShoppingCart() {
        products.clear();
    }
}
