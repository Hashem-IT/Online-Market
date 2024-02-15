package de.Hashem.bigbazar.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/*
*
* Klasse für Produkten Information
*/

@Entity
@Table(name = "\"Order\"")
public class Order extends BasicEntity {

    @OneToOne
    private Customer customer;

    @ElementCollection
    private Map<Product, Long> products = new HashMap<>();

    private BigDecimal sum;

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

    public void addProduct(Product product, Long amaount) {
        if (products.containsKey(product)) {
            products.replace(product, amaount);
        }
        products.put(product, amaount);
    }

    public void addAllProducts(Map<Product, Long> productsToBeAdded) {
        for (Product product : productsToBeAdded.keySet()) {
            addProduct(product, productsToBeAdded.get(product));
        }
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

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}