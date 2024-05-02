package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.DebitCard;
import ru.lisin.bazopt.repository.DebitCardRepository;
import ru.lisin.bazopt.services.DebitCardService;
import ru.lisin.bazopt.services.UserService;

@Service
public class DebitCardServiceImpl implements DebitCardService {
    private final DebitCardRepository debitCardRepository;
    private final UserService userService;

    @Autowired
    public DebitCardServiceImpl(DebitCardRepository debitCardRepository, UserService userService) {
        this.debitCardRepository = debitCardRepository;
        this.userService = userService;
    }

    @Override
    public DebitCard saveCard(DebitCard savedCard) {
        DebitCard existingDebitCard = debitCardRepository.getDebitCardByNumber(savedCard.getCardNumber());

        if (existingDebitCard != null) {
            existingDebitCard.setUser(userService.getCurrentUser());
            existingDebitCard.setCardNumber(savedCard.getCardNumber());
            existingDebitCard.setCvv(savedCard.getCvv());
            existingDebitCard.setUserFullName(savedCard.getUserFullName());
            existingDebitCard.setExpirationDate(savedCard.getExpirationDate());
            return debitCardRepository.save(existingDebitCard);
        } else {
            savedCard.setUser(userService.getCurrentUser());
            return debitCardRepository.save(savedCard);
        }
    }

    @Override
    public DebitCard getDebitCardByUserID(long userID) {
        return debitCardRepository.getDebitCardByUserID(userID);
    }
}
