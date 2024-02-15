// package de.Hashem.bigbazar.controller;

// import de.Hashem.bigbazar.service.GivawayService.GiveawayData;
// import de.Hashem.bigbazar.service.GivawayService.GiveawayServiceIF;
// import de.Hashem.bigbazar.service.GivawayService.ResponseDto;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.ResponseBody;

// /*
//  * Klasse für Order geben, war für andere Projketen zu verbinden
// */

// @Controller
// public class GiveawayController {

//     @Autowired
//     GiveawayServiceIF giveawayService;

//     @RequestMapping(method = RequestMethod.POST, value = "restapi/giveaway/execute")
//     @ResponseBody
//     public ResponseDto executeGivawayService(@RequestBody GiveawayData giveawayData) throws Exception {

//         return giveawayService.orderGiveaways(giveawayData);
//     }

// }
