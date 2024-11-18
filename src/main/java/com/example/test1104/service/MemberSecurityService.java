package com.example.test1104.service;

import com.example.test1104.constant.Role;
import com.example.test1104.entity.Member;
import com.example.test1104.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MemberSecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Member _siteUser = this.memberRepository.findByUsername(username);
        if(_siteUser == null){
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다");
        }
        String userId = _siteUser.getUsername();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(userId)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }
        return new User(userId, _siteUser.getPassword(), authorities);
    }
}
