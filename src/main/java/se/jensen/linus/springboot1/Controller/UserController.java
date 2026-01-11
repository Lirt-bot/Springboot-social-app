package se.jensen.linus.springboot1.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import se.jensen.linus.springboot1.DTO.*;
import se.jensen.linus.springboot1.security.MyuserDetails;
import se.jensen.linus.springboot1.service.PostService;
import se.jensen.linus.springboot1.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;
    private final PostService postService;


    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;

    }

    //    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping
//    public ResponseEntity<List<UserResponseDTO>> getAll() {
//        return ResponseEntity.ok(userService.findAll());
//    }
//    @GetMapping
//    public ResponseEntity<List<UserResponseDTO>> getAll() {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("AUTH = " + auth);
//        System.out.println("AUTHORITIES = " + auth.getAuthorities());
//
//        return ResponseEntity.ok(userService.findAll());
//    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());

        List<UserResponseDTO> usersFromDB = userService.findAll();
        return ResponseEntity.ok(usersFromDB);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO response = userService.addUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMe(
            @AuthenticationPrincipal MyuserDetails userDetails) {

        UserResponseDTO response = userService.getUserByUsername(userDetails.getUsername());

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{userId}/posts")
    public ResponseEntity<PostResponseDTO> createPostForUser(@PathVariable Long userId,
                                                             @Valid @RequestBody
                                                             PostRequestDTO postRequestDTO) {
        PostResponseDTO response =
                postService.createPost(userId, postRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}/with-posts")
    public ResponseEntity<UserWithPostsResponseDTO> getUserWithPosts(@PathVariable Long id) {
        UserWithPostsResponseDTO respone = userService.getUserWithPosts(id);
        return ResponseEntity.ok(respone);
    }


}
