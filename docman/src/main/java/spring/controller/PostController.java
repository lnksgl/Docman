package spring.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.dto.PostDto;
import spring.security.PostService;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/posts")
public class PostController {

    PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<List<PostDto>> editPost(@RequestBody PostDto postDto) {
        postService.updatePost(postDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllPosts() {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping({"/get/{id}", "/edit/{id}"})
    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<List<PostDto>> deletePost(@PathVariable @RequestBody Long id) {
        postService.deletePost(id);
        return showAllPosts();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<PostDto>> getCategoryPosts(@PathVariable String category) {
        return new ResponseEntity<>(postService.showCategoryPosts(category), HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<PostDto>> getNamePosts(@PathVariable String title) {
        return new ResponseEntity<>(postService.showTitlePost(title), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<PostDto>> getUsernamePosts(@PathVariable String username) {
        return new ResponseEntity<>(postService.showUsernamePosts(username), HttpStatus.OK);
    }
}
