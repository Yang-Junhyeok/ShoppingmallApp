package com.example.test1104.controller;

import com.example.test1104.dto.MemberFormDto;
import com.example.test1104.entity.Member;
import com.example.test1104.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }
        try{
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping("login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 입력해주세여");
        return "/member/memberLoginForm";
    }

//    @GetMapping("/new")
//    public String signup(MemberFormDto memberFormDto){
//        return "member/memberForm";
//    }
//
//    @PostMapping("/new")
//    public String signup(@Valid MemberFormDto memberFormDto, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "member/memberForm";
//        }
//        if(!memberFormDto.getPassword1().equals((memberFormDto.getPassword2()))){
//            bindingResult.rejectValue("password", "passwordInCorrect" , "2개의 비밀번호가 일치하지 않습니다.");
//            return "member/memberForm";
//        }
//        try{
//            memberService.create(memberFormDto.getUsername(), memberFormDto.getEmail(), memberFormDto.getPassword1(), memberFormDto.getAddress());
//        }catch(DataIntegrityViolationException e){
//            e.printStackTrace();
//            bindingResult.reject("signupFailed", "이미 등록된 아이디입니다.");
//            return "member/memberForm";
//        }catch (Exception e){
//            e.printStackTrace();
//            bindingResult.reject("signupFailed", e.getMessage());
//            return "member/memberForm";
//        }
//
//        return "redirect:/";
//    }
//
//    @GetMapping("/login")
//    public String login(){
//        return "member/memberLoginForm";
//    }
}
