package com.example.demo.adapter.postgres;

import com.example.demo.adapter.postgres.model.AuthorVO;
import com.example.demo.adapter.postgres.model.BookVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorJPARepository extends JpaRepository<AuthorVO, Integer> {

}
