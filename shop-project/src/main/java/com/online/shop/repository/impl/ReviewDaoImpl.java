package com.online.shop.repository.impl;

import com.online.shop.entity.Review;
import com.online.shop.repository.ReviewDao;

import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao<Review> {

    private List<Review> reviews;

    public ReviewDaoImpl() {
        this.reviews = new ArrayList<>();
    }

    @Override
    public Review findById(Long id) {
        return reviews.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Review> getAll() {
        return reviews;
    }

    @Override
    public void add(Review entity) {
        reviews.add(entity);
    }

    @Override
    public void update(Long id, Review entity) {
        entity.setId(id);
        reviews.replaceAll(review -> review.getId().equals(id) ? entity : review);
    }

    @Override
    public void delete(Long id) {
        reviews.removeIf(r -> r.getId().equals(id));
    }
}
