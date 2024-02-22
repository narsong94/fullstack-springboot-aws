package com.springboot.aws.web;

import org.springframework.web.bind.annotation.RestController;
import com.springboot.aws.service.posts.PostsService;
import com.springboot.aws.web.dto.PostsResponseDto;
import com.springboot.aws.web.dto.PostsSaveRequestDto;
import com.springboot.aws.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RequiredArgsConstructor
@RestController
public class PostsApiController {
    
    private final PostsService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable(name = "id") Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        
        return postService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable(name = "id") Long id) {

        return postService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable(name = "id") Long id) {
        postService.delete(id);
        return id;
    }
    
}
