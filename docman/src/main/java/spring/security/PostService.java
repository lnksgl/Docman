package spring.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.PostDto;
import spring.exception.PostNotFoundException;
import spring.mapper.PostMapper;
import spring.model.Post;
import spring.repository.PostRepository;
import spring.service.AuthService;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class PostService {

    AuthService authService;
    PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
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

    @Transactional(readOnly = true)
    public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromPostToDto(post);
    }

    @Transactional
    public void deletePost(long id) {
        postRepository.delete(postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id)));
    }

    @Transactional(readOnly = true)
    public List<PostDto> showCategoryPosts(String category) {
        return postsStream(postRepository.findByCategory(category));
    }

    @Transactional(readOnly = true)
    public List<PostDto> showTitlePost(String title) {
        return postsStream(postRepository.findByTitle(title));
    }

    @Transactional(readOnly = true)
    public List<PostDto> showUsernamePosts(String username) {
        return postsStream(postRepository.findByUsername(username));
    }

    @Transactional(readOnly = true)
    public List<PostDto> postsStream(List<Post> posts) {
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    @Transactional
    public PostDto mapFromPostToDto(Post post) {
        return PostMapper.INSTANCE.postToPostDto(post);
    }

    @Transactional
    public Post mapFromDtoToPost(PostDto postDto) {
        Post post = PostMapper.INSTANCE.dtoToPost(postDto);
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdatedOn(Instant.now());
        return post;
    }
}
