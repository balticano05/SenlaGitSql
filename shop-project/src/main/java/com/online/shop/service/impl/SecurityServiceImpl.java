package com.online.shop.security.service;

import com.online.shop.entity.Course;
import com.online.shop.entity.Review;
import com.online.shop.entity.User;
import com.online.shop.repository.CourseDao;
import com.online.shop.repository.ReviewDao;
import com.online.shop.repository.UserDao;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final ReviewDao reviewDao;
    private final CourseDao courseDao;
    private final UserDao userDao;

    public boolean isReviewOwner(Long reviewId, String currentUserEmail) {
        Review review = reviewDao.getById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        return review.getUser().getEmail().equals(currentUserEmail);
    }

    public boolean isUserOwner(Long userId, String currentUserEmail) {
        User currentUser = userDao.getById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return currentUser.getEmail().equals(currentUserEmail);
    }

    public boolean isCoursePurchasedByUser(Long courseId, Long userId) {
//        List<Course> userCourses = courseDao.findCoursesByUserId(userId);
//        for (Course course : userCourses) {
//            if (course.getId().equals(courseId)) {
//                return true;
//            }
//        }
        return courseDao.findCoursesByUserId(userId)
                .stream()
                .filter(course -> course.getId().equals(courseId))
                .findAny()
                .isEmpty();
    }

}