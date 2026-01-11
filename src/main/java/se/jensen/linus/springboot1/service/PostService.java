package se.jensen.linus.springboot1.service;

import org.springframework.stereotype.Service;
import se.jensen.linus.springboot1.DTO.PostRequestDTO;
import se.jensen.linus.springboot1.DTO.PostResponseDTO;
import se.jensen.linus.springboot1.Model.Post;
import se.jensen.linus.springboot1.Model.User;
import se.jensen.linus.springboot1.repository.PostRepository;
import se.jensen.linus.springboot1.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostService(UserRepository userRepository, PostRepository postRepository) {

        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public PostResponseDTO createPost(Long userId, PostRequestDTO postdto) {
        Post post = new Post();
        post.setText(postdto.text());
        post.setCreatedAt(LocalDateTime.now());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found" + userId));

        post.setUser(user);


        Post savedPost = postRepository.save(post);

        return new PostResponseDTO(
                savedPost.getId(),
                savedPost.getText(),
                savedPost.getCreatedAt());

    }

    public PostResponseDTO updatePost(Long userId,
                                      Long postId,
                                      PostRequestDTO postdto) {
        Post post = postRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Post not found " + postId));

        if (!post.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Post does not belong to user " + userId);

        }
        post.setText(postdto.text());

        Post updatedPost = postRepository.save(post);

        return new PostResponseDTO(
                updatedPost.getId(),
                updatedPost.getText(),
                updatedPost.getCreatedAt()
        );
    }

    public void deletePost(Long userId, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new NoSuchElementException("Post not found " + postId));

        if (!post.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException(
                    "Post does not belong to user " + userId);
        }

        postRepository.delete(post);
    }
}
