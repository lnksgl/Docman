package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import spring.dto.PostDto;
import spring.model.Post;

import java.time.Instant;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategory(String category);
    List<Post> findByTitle(String title);
    List<Post> findByUsername(String username);

    @Modifying
    @Query("update Post u set u.content = ?1, u.title = ?2, u.updatedOn = ?3, u.category = ?4 where u.id = ?5")
    void updatePost(String content, String title, Instant updateOn, String category, Long id);
}
