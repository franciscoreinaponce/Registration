package com.franciscoreina.registration.controller;

import com.franciscoreina.registration.dto.UserRequestDTO;
import com.franciscoreina.registration.dto.ResponseDTO;
import com.franciscoreina.registration.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(path = "/register/v1/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        LOGGER.info("+++ Calling /user/register/v1/ API +++");
        return new ResponseEntity<>(userService.register(userRequestDTO), HttpStatus.OK);
    }

}
