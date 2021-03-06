package com.junebookstore.controller;

import com.junebookstore.common.model.*;
import com.junebookstore.business.service.OrderService;
import com.junebookstore.business.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Api(tags = {"Users"})
@RestController
public class UserController {
    private UserService userService;
    private OrderService orderService;

    public UserController(
            @Autowired UserService userService,
            @Autowired OrderService orderService
    ) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @ApiOperation(value = "Login")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "login success"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "login failure"),
    })
    @PostMapping("/login")
    public void fakeLoginForMakeDocument(
            @ApiParam(value = "user information", required = true)
            @RequestBody Login request
    ) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @ApiOperation(value = "Create user account")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "create account success")
    })
    @PostMapping("/users")
    public ResponseEntity<?> createUserAccount(
            @ApiParam(value = "user information", required = true)
            @RequestBody Register request
    ) {
        userService.register(request);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Get user information")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "get information success", response = UserInformation.class),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Login required")
    })
    @GetMapping("/users")
    public ResponseEntity<?> getUserInformation(
            @ApiIgnore Principal principal
    ) {
        UserInformation userInformation = userService.getInformation(principal.getName());
        return ResponseEntity.ok(userInformation);
    }

    @ApiOperation(value = "Delete user activity")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "delete user activity success"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Login required")
    })
    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUserActivity(
            @ApiIgnore Principal principal
    ) {
        orderService.deleteOrderHistory(principal.getName());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Order books")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "order books success", response = OrderPrice.class),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Login required")
    })
    @PostMapping("/users/orders")
    public ResponseEntity<?> orderBooks(
            @ApiIgnore Principal principal,
            @ApiParam(value = "order information", required = true)
            @RequestBody OrderBooks request
    ) {
        OrderPrice orderPrice = orderService.orderBooks(principal.getName(), request);
        return ResponseEntity.ok(orderPrice);
    }
}
