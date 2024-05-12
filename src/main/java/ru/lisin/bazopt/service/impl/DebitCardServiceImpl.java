package ru.lisin.bazopt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.DebitCard;
import ru.lisin.bazopt.model.User;
import ru.lisin.bazopt.repository.DebitCardRepository;
import ru.lisin.bazopt.service.DebitCardService;
import ru.lisin.bazopt.service.UserService;

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

        User currentUser = userService.getCurrentUser();

        if (existingDebitCard != null) {
            existingDebitCard.setUser(currentUser);
            existingDebitCard.setCardNumber(savedCard.getCardNumber());
            existingDebitCard.setCvv(savedCard.getCvv());
            existingDebitCard.setUserFullName(savedCard.getUserFullName());
            existingDebitCard.setExpirationDate(savedCard.getExpirationDate());
            return debitCardRepository.save(existingDebitCard);
        } else {
            deleteDebitCardByUserID(currentUser.getId());

            savedCard.setUser(currentUser);
            return debitCardRepository.save(savedCard);
        }
    }

    @Override
    public DebitCard getDebitCardByUserID(long userID) {
        return debitCardRepository.getDebitCardByUserID(userID);
    }

    @Override
    public void deleteDebitCardByUserID(long userID) {
        DebitCard debitCardByUserID = getDebitCardByUserID(userID);
        debitCardRepository.delete(debitCardByUserID);
    }
}
