package ru.lisin.bazopt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.WholesaleBase;
import ru.lisin.bazopt.repository.WholesaleBaseRepository;
import ru.lisin.bazopt.service.WholesaleBaseService;

import java.util.List;

@Service
public class WholesaleBaseServiceImpl implements WholesaleBaseService {
    private final WholesaleBaseRepository repository;

    @Autowired
    public WholesaleBaseServiceImpl(WholesaleBaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WholesaleBase> getAllWholesaleBases() {
        return repository.findAll();
    }

    @Override
    public WholesaleBase getWholesaleBaseByName(String name) {
        return repository.getWholesaleBaseByName(name);
    }

    @Override
    public List<WholesaleBase> getAllWholesaleBasesByUserSearchText(String userSearchText) {
        return repository.getAllNamesByUserSearch(userSearchText.toLowerCase());
    }
}
