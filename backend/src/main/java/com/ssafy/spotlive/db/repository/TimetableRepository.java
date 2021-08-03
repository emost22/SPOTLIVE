package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
