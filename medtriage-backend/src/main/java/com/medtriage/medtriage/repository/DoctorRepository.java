package com.medtriage.medtriage.repository;

import com.medtriage.medtriage.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
