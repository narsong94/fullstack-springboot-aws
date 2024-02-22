package com.springboot.aws.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.aws.conifg.auth.dto.SessionUser;
import com.springboot.aws.domain.user.User;
import com.springboot.aws.service.posts.PostsService;
import com.springboot.aws.web.dto.PostsResponseDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave() {
        return "post-save";
    }
    
    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable(name = "id") Long id, Model model) {
        
        PostsResponseDto dto = postsService.findById(id);   // 무슨 영속성 때문에 바뀐다고 했던거 같음
        model.addAttribute("post", dto);

        return "post-update";
    }
}
