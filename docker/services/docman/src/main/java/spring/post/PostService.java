package spring.post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.auth.AuthService;
import spring.post.PostDto;
import spring.post.PostNotFoundException;
import spring.post.PostMapper;
import spring.post.Post;
import spring.post.PostRepository;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"service"})
public class PostService {

    private static final Logger LOG = Logger.getLogger(AuthService.class.getName());

    AuthService authService;
    PostRepository postRepository;
    PostMapper postMapper;

    @Cacheable
    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        LOG.log(Level.INFO, "show posts success");
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    @Transactional
    public void createPost(PostDto postDto) {
        postRepository.save(mapFromDtoToPost(postDto));
        LOG.log(Level.INFO, "create post success");
    }

    @Transactional
    public void updatePost(PostDto postDto) {
        postRepository.updatePost(postDto.getContent(), postDto.getTitle(), Instant.now(), postDto.getCategory(),
                postDto.getId());
        LOG.log(Level.INFO, "update post success");
    }

    @Cacheable
    public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        LOG.log(Level.INFO, "read single post success");
        return mapFromPostToDto(post);
    }

    @Transactional
    public void deletePost(long id) {
        postRepository.delete(postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id)));
        LOG.log(Level.INFO, "delete post success");
    }

    @Cacheable
    public List<PostDto> showCategoryPosts(String category) {
        LOG.log(Level.INFO, "show post-category success");
        return postsStream(postRepository.findByCategory(category));
    }

    @Cacheable
    public List<PostDto> showTitlePost(String title) {
        LOG.log(Level.INFO, "show post-title success");
        return postsStream(postRepository.findByTitle(title));
    }

    @Cacheable
    public List<PostDto> showTitleUsernamePosts(String title, String username) {
        LOG.log(Level.INFO, "show post-title-username success");
        return postsStream(postRepository.findByTitleAndUsername(title, username));
    }

    @Cacheable
    public List<PostDto> showUsernamePosts(String username) {
        LOG.log(Level.INFO, "show post-username success");
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
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdatedOn(Instant.now());
        return post;
    }
}
