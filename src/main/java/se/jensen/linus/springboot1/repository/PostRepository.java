package se.jensen.linus.springboot1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.linus.springboot1.Model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {


}
