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
        savedCard.setUser(userService.getCurrentUser());
        return debitCardRepository.save(savedCard);
    }
}
