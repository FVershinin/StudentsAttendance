package ee.ttu.vk.attendance.repository;

import ee.ttu.vk.attendance.domain.*;
import ee.ttu.vk.attendance.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.util.List;

/**
 * Created by strukov on 3/24/16.
 */
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @EntityGraph(value = "attendance.all", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select a from Attendance a where a.timetable = ?1")
    Page<Attendance> findAll(Timetable timetable, Pageable pageable);

    List<Attendance> findByTimetable(Timetable timetable, Pageable pageable);

    @EntityGraph(value = "attendance.all", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select a from Attendance a where a.student = ?1 and a.timetable.subject = ?2")
    List<Attendance> findAll(Student student, Subject subject);

    @EntityGraph(value = "attendance.all", type = EntityGraph.EntityGraphType.LOAD)
    List<Attendance> findByStudent(Student student);

    @Query("select count(a) from Attendance a where a.timetable = ?1")
    Long size(Timetable timetable);
}
