package com.online.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "course_plans")
public class CoursePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "lesson_count")
    private Integer lessonCount;
    @Column(name = "practice_count")
    private Integer practiceCount;
    @Column(name = "duration")
    private Integer duration;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Course course;
}