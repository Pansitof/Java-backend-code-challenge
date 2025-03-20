package com.codechallenge.share.adapters.userrepository;

import com.codechallenge.application.domain.User;
import com.codechallenge.application.ports.driven.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileSystemUserRepository implements UserRepository {

    private final File jsonFile = new File("src/main/resources/templates/data.json");

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            for (JsonNode node : jsonNode) {
                users.add(convertJsonNodeToUser(node));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User createUser(User user) {
        return new User("","","","","");
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return null;
    }

    @Override
    public Optional<User> getById(String username) {
        return Optional.empty();
    }

    private User convertJsonNodeToUser(JsonNode node){
        User user = new User(
                node.get("username").asText(),
                node.get("name").asText(),
                node.get("email").asText(),
                node.get("gender").asText(),
                node.get("picture").asText());
        return user;
    }

    /*
    public void writeArrayOfUsersToJson() throws IOException {
        File jsonFile = new File("src/main/resources/templates/data.json");

        ObjectMapper objectMapper = new ObjectMapper();

        List<User> users = List.of(
                new User("username", "name", "email", "gender", "picture"),
                new User("UserB", "UserB", "UserB", "UserB", "UserB"),
                new User("UserC", "UserC", "UserC", "UserC", "UserC"),
                new User("UserD", "UserD", "UserD", "UserD", "UserD")
        );

        ArrayNode array = objectMapper.valueToTree(users);

        objectMapper.writeValue(jsonFile, array);
    }
    */
}
