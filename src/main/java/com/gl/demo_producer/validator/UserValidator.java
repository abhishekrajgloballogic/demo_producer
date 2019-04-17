package com.gl.demo_producer.validator;

import com.gl.demo_producer.entity.User;
import com.gl.demo_producer.exception.UserException;
import org.springframework.http.HttpStatus;

public class UserValidator {

    public static Boolean isValidUserRequest(User user) {
        if (user.getName() == null)
            throw new UserException("User name can not be null", HttpStatus.BAD_REQUEST.value());

        if (user.getEmailId() == null)
            throw new UserException("Email Id can not be null", HttpStatus.BAD_REQUEST.value());

        if (user.getMobileNo() == null)
            throw new UserException("Mobile No can not be null", HttpStatus.BAD_REQUEST.value());

        return true;
    }
}
