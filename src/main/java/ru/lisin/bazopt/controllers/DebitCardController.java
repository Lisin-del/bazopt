package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.lisin.bazopt.model.DebitCard;
import ru.lisin.bazopt.services.DebitCardService;

@Controller
@RequestMapping(path = "/debitCard")
public class DebitCardController {
    private final DebitCardService debitCardService;

    @Autowired
    public DebitCardController(DebitCardService debitCardService) {
        this.debitCardService = debitCardService;
    }

    @PostMapping(path = "/save")
    @ResponseBody
    public void saveDebitCard(@RequestBody DebitCard debitCard) {
        DebitCard debitCard1 = debitCardService.saveCard(debitCard);
        int i = 0;
    }
}
