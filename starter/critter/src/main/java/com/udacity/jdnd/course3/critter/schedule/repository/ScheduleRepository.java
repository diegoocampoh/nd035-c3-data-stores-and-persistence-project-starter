package com.udacity.jdnd.course3.critter.schedule.repository;

import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s inner join s.pets p where p.id = :petId")
    List<Schedule> findAllByPetId(@Param("petId") Long petId);

    @Query("select s from Schedule s inner join s.employees e where e.id = :employeeId")
    List<Schedule> findAllByEmployee(@Param("employeeId") Long employeeId);

    @Query("select s from Schedule s inner join s.pets p inner join p.owner o where o.id = :customerId")
    List<Schedule> findAllByCustomer(@Param("customerId") Long customerId);

}
