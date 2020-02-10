package spring.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.dto.PostDto;
import spring.service.PostService;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<java.util.List<PostDto>> updatePost(@RequestBody PostDto postDto) {
        postService.updatePost(postDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PostDto>> showAllPosts() {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable Long id) {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/title/{title}")
    public ResponseEntity<List<PostDto>> getTitlePosts(@PathVariable String title) {
        return new ResponseEntity<>(postService.showTitlePost(title), HttpStatus.OK);
    }

    @PutMapping("/category/{category}")
    public ResponseEntity<List<PostDto>> getCategoryPosts(@PathVariable String category) {
        return new ResponseEntity<>(postService.showCategoryPosts(category), HttpStatus.OK);
    }

    @PutMapping("/username/{username}")
    public ResponseEntity<List<PostDto>> getUsernamePosts(@PathVariable String username) {
        return new ResponseEntity<>(postService.showUsernamePosts(username), HttpStatus.OK);
    }

    @PutMapping("{title}?{username}")
    public ResponseEntity<List<PostDto>> getTitleUsernamePosts(@PathVariable String title, @PathVariable String username) {
        return new ResponseEntity<>(postService.showTitleUsernamePosts(title, username), HttpStatus.OK);
    }
}
