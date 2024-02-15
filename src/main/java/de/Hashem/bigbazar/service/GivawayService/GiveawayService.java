// package de.Hashem.bigbazar.service.GivawayService;

// /*
//  * Klasse für Order geben, war für andere Projketen zu verbinden
// */

// import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService.SendOrder;
// import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Address;
// import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.PersonalData;
// import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.enums.Salutation;
// import de.Hashem.bigbazar.entity.Customer;
// import de.Hashem.bigbazar.entity.Order;
// import de.Hashem.bigbazar.entity.Product;
// import de.Hashem.bigbazar.repository.CustomerRepository;
// import de.Hashem.bigbazar.repository.OrderRepository;
// import de.Hashem.bigbazar.repository.ProductRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Scope;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// import java.math.BigDecimal;
// import java.util.*;

// @Service
// @Scope("singleton")
// public class GiveawayService implements GiveawayServiceIF {

//     @Autowired
//     RestTemplate restClient;

//     @Autowired
//     CustomerRepository customerRepository;

//     @Autowired
//     ProductRepository productRepository;

//     @Autowired
//     OrderRepository orderRepository;

//     @Override
//     public ResponseDto orderGiveaways(GiveawayData giveawayData) throws Exception {
//         Optional<Customer> senderCustomer = customerRepository.findByEmail(giveawayData.getSenderEmail());

//         if (!senderCustomer.isPresent())
//             throw new Exception("Kunde fuer givawayservice existiert nicht");

//         SendOrder sendOrder = new SendOrder();
//         PersonalData recpientData = buildRecipientData(giveawayData);
//         PersonalData senderData = buildSenderData(giveawayData);
//         sendOrder.setRecipientData(recpientData);
//         sendOrder.setSenderData(senderData);
//         sendOrder.setSenderIban(senderCustomer.get().getPersonalData().getIban());
//         sendOrder.setSenderAccountId(senderCustomer.get().getPersonalData().getBankAccountId());
//         sendOrder.setSenderAccountPassword(senderCustomer.get().getPersonalData().getBankPassword());
//         Order productOrder = orderProducts(senderCustomer.get(), giveawayData.getAmountOfProducts());
//         int parcelweightOfOrder = calcualteParcelWeightOfOrderInKg(productOrder);
//         sendOrder.setParcelWeight(parcelweightOfOrder);
//         ResponseEntity<Boolean> responseEntity = restClient.postForEntity("http://im-codd:8808/apis/order/makeOrder", sendOrder, Boolean.class);
//         ResponseDto responseDto = new ResponseDto();
//         responseDto.setSum(productOrder.getSum().doubleValue());
//         return responseDto;
//     }

//     private PersonalData buildRecipientData(GiveawayData giveawayData) {
//         PersonalData recipientData = new PersonalData();
//         Address recipientAddress = new Address();

//         recipientData.setSalutation(Salutation.ANY);
//         recipientData.setBirthDate(new Date());
//         recipientData.setBirthPlace("keine Angabe");
//         recipientData.setFirstName(giveawayData.getName());
//         recipientData.setLastName(giveawayData.getName());
//         recipientAddress.setCity(giveawayData.getPostalNumber());
//         recipientAddress.setPostalCode(Integer.parseInt(giveawayData.getPostalNumber()));
//         recipientAddress.setCountry(giveawayData.getCountry());
//         recipientAddress.setStreet(giveawayData.getStreetName() + giveawayData.getHouseNumber());

//         recipientData.setAddress(recipientAddress);

//         return recipientData;
//     }

//     private PersonalData buildSenderData(GiveawayData giveawayData) {
//         Customer customer = customerRepository.findByEmail(giveawayData.getSenderEmail()).get();
//         PersonalData senderData = new PersonalData();
//         senderData.setFirstName(customer.getPersonalData().getFirstName());
//         senderData.setLastName(customer.getPersonalData().getLastName());
//         senderData.setBirthDate(customer.getPersonalData().getBirthdate());
//         senderData.setBirthPlace("keine Angabe");
//         senderData.setSalutation(Salutation.ANY);
//         Address senderAddress = new Address();
//         senderAddress.setStreet(customer.getPersonalData().getAddress().getStreetName() + customer.getPersonalData().getAddress().getHouseNumber());
//         senderAddress.setCountry(customer.getPersonalData().getAddress().getCountry());
//         senderAddress.setPostalCode(Integer.parseInt(customer.getPersonalData().getAddress().getPostalNumber()));
//         senderAddress.setCity(customer.getPersonalData().getAddress().getPostalNumber());
//         senderData.setAddress(senderAddress);
//         return senderData;
//     }

//     private Order orderProducts(Customer customer, int amount) {
//         List<Product> products = new ArrayList<>();
//         productRepository.findAll().forEach(products::add);
//         int size = products.size();
//         System.out.println(size + " anz aller Produkte");
//         Random random = new Random();
//         int index = random.nextInt(size);
//         Product product = products.get(index);

//         Order order = new Order();
//         order.setCustomer(customer);
//         Map<Product, Long> orderedProduct = new HashMap<>();
//         orderedProduct.put(product, Long.valueOf(amount));
//         order.addAllProducts(orderedProduct);
//         order.setSum(product.getBrutto().multiply(BigDecimal.valueOf(amount)));
//         return orderRepository.save(order);
//     }

//     private int calcualteParcelWeightOfOrderInKg(Order order) {
//         BigDecimal totalWeightInGram = BigDecimal.ZERO;
//         for (Product product : order.getProducts().keySet()) {
//             BigDecimal weightOfProdcts = product.getWeightInGram();
//             BigDecimal weightOfThisAmountOfProducts = weightOfProdcts.multiply(BigDecimal.valueOf(order.getProducts().get(product)));
//             totalWeightInGram = totalWeightInGram.add(weightOfThisAmountOfProducts);
//         }

//         return totalWeightInGram.intValue();
//     }
// }
