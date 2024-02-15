package de.Hashem.bigbazar.service;

import de.Hashem.bigbazar.entity.*;
import de.Hashem.bigbazar.repository.*;
//import de.marcoedenhofer.edenbank.application.transactionservice.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
//import java.math.BigDecimal;
import java.util.*;

/*
* Klasse für ProductService
* und alle andere gekommentierte Funktionen und Bibliotheken war für vorherige Projekten
*/

@Service
public class ProductService implements ProductServiceIF{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    CustomerServiceIF customerService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restClient;

    //private final String edenbankUrl = "http://im-codd:8847/apis/transaction/execute";

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public void addProductToShoppingCart(Long id, String email) {
        Customer customer = customerService.findByEmail(email).get();
        Product product = productRepository.findById(id).get();
        ShoppingCart shoppingCart = shoppingCartRepository.findByCustomerEmail(customer.getEmail()).get();
        shoppingCart.addProduct(product);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart findShoppinCartByEmail(String email) {

        return shoppingCartRepository.findByCustomerEmail(email).get();
    }

    @Override
    @Transactional
    public void removeProductFromShoppingCart(Long productId, String email) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByCustomerEmail(email).get();
        Product product = productRepository.findById(productId).get();

        shoppingCart.remove(product);
        shoppingCartRepository.save(shoppingCart);

    }

    @Override
    @Transactional
    public void changeAmountOfProductInShoppinCart(Long productId, String email, int amount) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByCustomerEmail(email).get();
        Product product = productRepository.findById(productId).get();
        shoppingCart.addProduct(product, Long.valueOf(amount));
        shoppingCartRepository.save(shoppingCart);
    }

    // @Override
    // @Transactional
    // public void orderAndBuyShoppinCart(String email) {
    //     ShoppingCart shoppingCart = shoppingCartRepository.findByCustomerEmail(email).get();
    //     Order order = new Order();
    //     order.setCustomer(shoppingCart.getCustomer());

    //     makeAllPeyments(shoppingCart, order);
    //     order.setSum(order.claculateSum());
    //     orderRepository.save(order);
    //     shoppingCartRepository.save(shoppingCart);
    // }

    // private void makeAllPeyments(ShoppingCart shoppingCart, Order order) {
    //     Customer moneySender = shoppingCart.getCustomer();
    //     Map<Customer, BigDecimal> recipientAndAmount = new HashMap<>();
    //     for (Product product : shoppingCart.getProducts().keySet()) {
    //         Long numberOfThisProduct = shoppingCart.getProducts().get(product);
    //         BigDecimal amountOfMoney = product.getBrutto().multiply(new BigDecimal(numberOfThisProduct));
    //         if (recipientAndAmount.containsKey(product.getOfferdBy())) {
    //             BigDecimal oldAmount = recipientAndAmount.get(product.getOfferdBy());
    //             recipientAndAmount.replace(product.getOfferdBy(), oldAmount.add(amountOfMoney));
    //         } else {
    //             recipientAndAmount.put(product.getOfferdBy(), amountOfMoney);
    //         }
    //         makePayment(product.getOfferdBy(), amountOfMoney, moneySender, shoppingCart, order, product);
    //     }
    //     for (Product product : order.getProducts().keySet()) {
    //         shoppingCart.remove(product);
    //     }
    // }

    // private void makePayment(Customer recipient, BigDecimal amountOfMoney, Customer moneySender, ShoppingCart shoppingCart, Order order, Product product) {
    //     TransactionData transactionData = new TransactionData();
    //     transactionData.setAmount(amountOfMoney.doubleValue());
    //     transactionData.setSenderIban(moneySender.getPersonalData().getIban());
    //     transactionData.setReceiverIban(recipient.getPersonalData().getIban());
    //     transactionData.setSenderCustomerAccountId(moneySender.getPersonalData().getBankAccountId());
    //     transactionData.setSenderPassword(moneySender.getPersonalData().getBankPassword());
    //     transactionData.setUsageDetails("Payment for order from big bazar");
    //     try {
    //         ResponseEntity<TransactionData> resp = restClient.postForEntity("http://im-codd:8847/apis/transaction/execute", transactionData, TransactionData.class);
    //         if (resp.hasBody()) {
    //             order.addProduct(product, shoppingCart.getProducts().get(product));
    //         }
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }

    // }

    // private boolean makePayments(Map<Customer, BigDecimal> recipientAndAmount, Customer moneySender, Order order) {
    //     for (Customer recipient : recipientAndAmount.keySet()) {
    //         TransactionData transactionData = new TransactionData();
    //         transactionData.setAmount(recipientAndAmount.get(recipient).doubleValue());
    //         transactionData.setSenderIban(moneySender.getPersonalData().getIban());
    //         transactionData.setReceiverIban(recipient.getPersonalData().getIban());
    //         transactionData.setSenderCustomerAccountId(moneySender.getPersonalData().getBankAccountId());
    //         transactionData.setSenderPassword(moneySender.getPersonalData().getBankPassword());
    //         transactionData.setUsageDetails("Payment for order from big bazar" );
    //         ResponseEntity<TransactionData> resp = restClient.postForEntity("http://im-codd:8847/apis/transaction/execute", transactionData, TransactionData.class);
    //         System.out.println(resp.getBody().getAmount());
    //     }
    //     return true;
    // }

}
