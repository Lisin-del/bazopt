package ru.lisin.bazopt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilter {
    private String producer;
    private String base;
    private float price;
    private long quantity;
}
