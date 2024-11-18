package com.example.test1104.repository;

import com.example.test1104.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);

    //Member findByEmail(String email);
}
