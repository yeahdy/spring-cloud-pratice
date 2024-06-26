package com.example.userservice.controller;

import com.example.userservice.common.response.ResponseMessage;
import com.example.userservice.config.MyRefreshListener;
import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final Greeting greeting;
    private final UserService userService;
    private final ModelMapper modelMapper;

    private final MyRefreshListener refreshListener;

    @GetMapping("/health_check")
    public String status() {
        return String.format("Working in User Service!"
                + "%n, Your port: " + refreshListener.getPort()
                + "%n, Your token expirationTime: "+ refreshListener.getExpirationTime()
                + "%n, Your secret key: "+ refreshListener.getSecret()
        );
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/user")
    public ResponseMessage<ResponseUser> createUser(@RequestBody @Valid RequestUser user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);
        ResponseUser responseUser = modelMapper.map(createdUser, ResponseUser.class);
        return ResponseMessage.createSuccess(responseUser);
    }

    @GetMapping("/user/list")
    @Timed(value = "user.list", longTask = true)
    public ResponseMessage<List<ResponseUser>> getUsers() {
        List<UserDto> userDtos = userService.getUserByAll();
        List<ResponseUser> result = new ArrayList<>();
        userDtos.forEach(
                user -> result.add(modelMapper.map(user, ResponseUser.class))
        );
        return ResponseMessage.success(result, "Membership Enquiry");
    }

    @GetMapping("/user/{userId}")
    @Timed(value = "user.userId", longTask = true)
    public ResponseMessage<ResponseUser> getUserInfo(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);
        return ResponseMessage.success(
                modelMapper.map(userDto, ResponseUser.class), "Member Enquiry");
    }

}
