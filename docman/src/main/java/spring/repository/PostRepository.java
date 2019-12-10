package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
