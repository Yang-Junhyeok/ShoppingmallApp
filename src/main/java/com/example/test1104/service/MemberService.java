package com.example.test1104.service;

import com.example.test1104.entity.Member;
import com.example.test1104.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByUsername(member.getUsername());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

//    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);

        if(member == null){
            throw  new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    public Member create(String username, String email,String password,String address){
        Member member = new Member();
        member.setUsername(username);
        member.setEmail(email);
        member.setPassword(passwordEncoder.encode(password));
        member.setAddress(address);
        this.memberRepository.save(member);
        return member;
    }
}
