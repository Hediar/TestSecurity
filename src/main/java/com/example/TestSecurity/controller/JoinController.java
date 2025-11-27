package com.example.TestSecurity.controller;

import com.example.TestSecurity.dto.JoinDTO;
import com.example.TestSecurity.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @Autowired // 현재는 필드 주입 방식이지만, 생성자주입 방식을 권장
    private JoinService joinService;

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {

        System.out.println(joinDTO.getUsername());

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
