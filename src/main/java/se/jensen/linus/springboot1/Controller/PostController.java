package se.jensen.linus.springboot1.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensen.linus.springboot1.DTO.PostRequestDTO;
import se.jensen.linus.springboot1.DTO.PostResponseDTO;
import se.jensen.linus.springboot1.service.PostService;

@RestController
@RequestMapping()
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


//    @GetMapping("/all")
//    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
//
//        return ResponseEntity.ok(postService.findall());
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<PostResponseDTO> getPostByID(@PathVariable Long id) {
//
//        return ResponseEntity.ok(postService.findById(id));
//    }

    @PutMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<PostResponseDTO> updatePost(
            @PathVariable Long userId,
            @PathVariable Long postId,
            @Valid @RequestBody PostRequestDTO dto) {

        PostResponseDTO responseDTO =
                postService.updatePost(userId, postId, dto);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long userId,
            @PathVariable Long postId) {

        postService.deletePost(userId, postId);
        return ResponseEntity.noContent().build();
    }
}



