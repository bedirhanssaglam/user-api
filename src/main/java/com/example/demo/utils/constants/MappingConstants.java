package com.example.demo.utils.constants;

/**
 * This class holds constant values for the API endpoint mappings used in the application.
 * The purpose of these constants is to centralize the API paths and reduce duplication
 * across the application, making it easier to maintain and update.
 */
public class MappingConstants {

    /**
     * The constant for the '/users' API endpoint, used for fetching or managing user data.
     */
    public static final String USERS = "/users";

    /**
     * The constant for the '/{id}' endpoint, used for accessing a specific user by their unique ID.
     */
    public static final String ID = "/{id}";

    /**
     * The constant for the '/email/{email}' endpoint, used for accessing a user by their email address.
     */
    public static final String EMAIL = "/email/{email}";

    /**
     * The constant for the '/name/{name}' endpoint, used for accessing users by their name.
     */
    public static final String NAME = "/name/";

}
