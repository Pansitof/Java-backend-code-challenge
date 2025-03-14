package com.codechallenge.technologies.restapi;

import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class JavaBackendCodeChallengeApplicationTests {
    /*private UserService userService = new UserService(new UserRepositoryMockWithUsers());
    private UserController userController = new UserController(userService);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void doesContainUsers_returnUserList() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        System.out.println(json);
        assert true;
    }
*/
}
