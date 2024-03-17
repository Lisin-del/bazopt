package ru.lisin.bazopt.services;

import ru.lisin.bazopt.model.WholesaleBase;

import java.util.List;

public interface WholesaleBaseService {
    List<WholesaleBase> getAllWholesaleBases();

    WholesaleBase getWholesaleBaseByName(String name);

    List<WholesaleBase> getAllWholesaleBasesByUserSearchText(String userSearchText);
}
