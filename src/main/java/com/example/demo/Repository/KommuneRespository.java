package com.example.demo.Repository;

import com.example.demo.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KommuneRespository extends JpaRepository<Kommune, String> {
}
