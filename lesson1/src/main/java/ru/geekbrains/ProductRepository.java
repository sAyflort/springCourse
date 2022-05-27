package ru.geekbrains;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductRepository {

    private final Map<Integer, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicInteger aId = new AtomicInteger(0);

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(int id) {
        return productMap.get(id);
    }

    public void insert(Product product) {
        int id = aId.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

}
