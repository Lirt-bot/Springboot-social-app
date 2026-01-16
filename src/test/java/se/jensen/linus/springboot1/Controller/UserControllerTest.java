package se.jensen.linus.springboot1.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.jensen.linus.springboot1.DTO.UserResponseDTO;
import se.jensen.linus.springboot1.Model.User;
import se.jensen.linus.springboot1.repository.UserRepository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Disabled
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
        User user = new User();
        user.setRole("ROLE_ADMIN");
        user.setUsername("admin");
        user.setDisplayName("admin display");
        user.setPassword(passwordEncoder.encode("123"));
        user.setEmail("admin@gmail.com");
        user.setBio("my bio");
        user.setProfileImagePath("fake profile image");
        userRepository.save(user);
    }

    @Disabled
    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldGetAllUsers() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();

        List<UserResponseDTO> users = objectMapper.readValue(
                response, new TypeReference<List<UserResponseDTO>>() {
                });
        assertEquals(1, users.size());
    }

}
