package de.Hashem.bigbazar.service;

import de.Hashem.bigbazar.entity.*;
import java.util.List;

/*
* Klasse für ProductServiceInterFace
* und alle andere gekommentierte Funktionen und Bibliotheken war für vorherige Projekten
*/


public interface ProductServiceIF {

    List<Product> findAll();

    void save(Product product);

    void saveAll(List<Product> products);

    List<Product> findByName(String name);

    void addProductToShoppingCart(Long productId, String email);

    ShoppingCart findShoppinCartByEmail(String email);

    void removeProductFromShoppingCart(Long productId, String email);

    void changeAmountOfProductInShoppinCart(Long productId, String email, int amount);

    //public void orderAndBuyShoppinCart(String email);

}
