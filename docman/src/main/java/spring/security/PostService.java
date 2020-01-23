package spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.PostDto;
import spring.exception.PostNotFoundException;
import spring.model.Post;
import spring.repository.PostRepository;
import spring.service.AuthService;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;

    @Transactional
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

    @Transactional
    public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromPostToDto(post);
    }

    @Transactional
    public void deletePost(long id) {
        postRepository.delete(postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id)));
    }

    @Transactional
    public List<PostDto> showCategoryPosts(String category) {
        return postsStream(postRepository.findByCategory(category));
    }

    @Transactional
    public List<PostDto> showTitlePost(String title) {
        return postsStream(postRepository.findByTitle(title));
    }

    @Transactional
    public List<PostDto> showUsernamePosts(String username) {
        return postsStream(postRepository.findByUsername(username));
    }

    @Transactional
    public List<PostDto> postsStream(List<Post> posts) {
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setCategory(post.getCategory());
        postDto.setUsername(post.getUsername());
        return postDto;
    }

    private Post mapFromDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(postDto.getCategory());
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdatedOn(Instant.now());
        return post;
    }
}
