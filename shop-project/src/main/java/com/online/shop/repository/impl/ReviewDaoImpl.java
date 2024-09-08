package com.online.shop.repository.impl;

import com.online.shop.entity.Review;
import com.online.shop.repository.ReviewDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReviewDaoImpl implements ReviewDao {

    private List<Review> reviews;

    @Override
    public Optional<Review> getById(Long id) {
        return reviews.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Review> getAll() {
        return reviews;
    }

    @Override
    public Long insert(Review entity) {
        reviews.add(entity);
        return reviews.getLast().getId();
    }

    @Override
    public Optional<Review> update(Long id, Review entity) {
        entity.setId(id);
        reviews.replaceAll(review -> review.getId().equals(id) ? entity : review);
        return reviews.stream()
                .filter(review -> review.getId().equals(id))
                .findFirst();
    }

    @Override
    public Boolean delete(Long id) {
        Integer size = reviews.size();
        reviews.removeIf(r -> r.getId().equals(id));
        return reviews.size() < size;
    }

}