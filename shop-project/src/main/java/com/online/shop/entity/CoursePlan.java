package com.online.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "course_plans")
public class CoursePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "lesson_count", nullable = false)
    private Integer lessonCount;
    @Column(name = "practice_count", nullable = false)
    private Integer practiceCount;
    @Column(name = "duration", nullable = false)
    private Integer duration;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private Course course;
}