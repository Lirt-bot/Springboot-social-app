package se.jensen.linus.springboot1.mapper;

import org.springframework.stereotype.Component;
import se.jensen.linus.springboot1.DTO.UserRequestDTO;
import se.jensen.linus.springboot1.DTO.UserResponseDTO;
import se.jensen.linus.springboot1.Model.User;

@Component
public class UserMapper {
    // private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User fromDto(UserRequestDTO DTO) {
        User user = new User();

        user.setUsername(DTO.username());
        user.setEmail(DTO.email());
        user.setPassword(DTO.password());
        user.setRole(DTO.role());
        user.setDisplayName(DTO.displayName());
        user.setBio(DTO.bio());
        user.setProfileImagePath(DTO.profileImagePath());

        return user;
    }

    private void setUserValues(User user, UserResponseDTO userResponseDTO) {
        user.setUsername(userResponseDTO.username());
        user.setEmail(userResponseDTO.email());

    }


    public UserResponseDTO toDTO(User user) {
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

}

