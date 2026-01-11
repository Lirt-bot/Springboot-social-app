package se.jensen.linus.springboot1.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.jensen.linus.springboot1.DTO.PostResponseDTO;
import se.jensen.linus.springboot1.DTO.UserRequestDTO;
import se.jensen.linus.springboot1.DTO.UserResponseDTO;
import se.jensen.linus.springboot1.DTO.UserWithPostsResponseDTO;
import se.jensen.linus.springboot1.Model.User;
import se.jensen.linus.springboot1.mapper.UserMapper;
import se.jensen.linus.springboot1.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public UserResponseDTO getUserByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "User not found: " + username));

        return userMapper.toDTO(user);
    }

    public List<UserResponseDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getId(), user.getUsername(), user.getEmail(), user.getRole(),
                        user.getDisplayName(), user.getBio(), user.getProfileImagePath())).toList();

    }

    public UserResponseDTO addUser(UserRequestDTO userDTO) {
        User user = userMapper.fromDto(userDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        boolean exists = userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (exists) {
            throw new IllegalArgumentException("Username or Email already exists!");
        }
        User savedUser = userRepository.save(user);


        return toDTO(savedUser);
    }

    private UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getDisplayName(),
                user.getBio(),
                user.getProfileImagePath()
        );
    }

    private User fromDTO(UserRequestDTO dto) {
        User user = new User();

        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        user.setDisplayName(dto.displayName());
        user.setBio(dto.bio());
        user.setProfileImagePath(dto.profileImagePath());

        return user;
    }


    public UserWithPostsResponseDTO getUserWithPosts(Long id) {
        User user = userRepository.findUserWithPosts(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id " + id));
        List<PostResponseDTO> posts = user.getPosts()
                .stream()
                .map(p -> new PostResponseDTO(
                        p.getId(),
                        p.getText(),
                        p.getCreatedAt()
                )).toList();
        UserResponseDTO DTO = new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getDisplayName(),
                user.getBio(),
                user.getProfileImagePath()
        );
        UserWithPostsResponseDTO dtoToReturn = new UserWithPostsResponseDTO(DTO, posts);

        return dtoToReturn;

    }


}
