package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.WholesaleBase;
import ru.lisin.bazopt.repository.WholesaleBaseRepository;
import ru.lisin.bazopt.services.WholesaleBaseService;

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
}
