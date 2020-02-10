package spring.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.PostDto;
import spring.exception.PostNotFoundException;
import spring.mapper.PostMapper;
import spring.model.Post;
import spring.repository.PostRepository;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@Transactional(readOnly = true)
public class PostService {

    AuthService authService;
    PostRepository postRepository;
    PostMapper postMapper;

    public java.util.List<PostDto> showAllPosts() {
        java.util.List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    @Transactional
    public void createPost(PostDto postDto) {
        postRepository.save(mapFromDtoToPost(postDto));
    }

    @Transactional
    public void updatePost(PostDto postDto) {
        postRepository.updatePost(postDto.getContent(), postDto.getTitle(), Instant.now(), postDto.getCategory(),
                postDto.getId());
    }

    public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromPostToDto(post);
    }

    @Transactional
    public void deletePost(long id) {
        postRepository.delete(postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id)));
    }

    public java.util.List<PostDto> showCategoryPosts(String category) {
        return postsStream(postRepository.findByCategory(category));
    }

    public List<PostDto> showTitlePost(String title) {
        return postsStream(postRepository.findByTitle(title));
    }


    public List<PostDto> showTitleUsernamePosts(String title, String username) {
        return postsStream(postRepository.findByTitleAndUsername(title, username));
    }

    public List<PostDto> showUsernamePosts(String username) {
        return postsStream(postRepository.findByUsername(username));
    }

    public List<PostDto> postsStream(List<Post> posts) {
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    public PostDto mapFromPostToDto(Post post) {
        return postMapper.postToPostDto(post);
    }

    public Post mapFromDtoToPost(PostDto postDto) {
        Post post = postMapper.dtoToPost(postDto);
        org.springframework.security.core.userdetails.User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdatedOn(Instant.now());
        return post;
    }
}
