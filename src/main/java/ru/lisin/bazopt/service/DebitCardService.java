package ru.lisin.bazopt.service;

import ru.lisin.bazopt.model.DebitCard;

public interface DebitCardService {
    DebitCard saveCard(DebitCard savedCard);

    DebitCard getDebitCardByUserID(long userID);

    void deleteDebitCardByUserID(long userID);
}
