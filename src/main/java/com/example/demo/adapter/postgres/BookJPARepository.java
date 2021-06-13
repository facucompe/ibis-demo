package com.example.demo.adapter.postgres;

import com.example.demo.adapter.postgres.model.BookVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookJPARepository extends JpaRepository<BookVO, Integer> {
    List<BookVO> findAllByAuthorId(Integer authorId);
}
