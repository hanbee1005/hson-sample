package com.example.hsonsample.repository;

import com.example.hsonsample.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
