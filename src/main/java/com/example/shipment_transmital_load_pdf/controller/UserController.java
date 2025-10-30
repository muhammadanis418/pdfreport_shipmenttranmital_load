package com.example.shipment_transmital_load_pdf.controller;

import com.example.shipment_transmital_load_pdf.entity.User;
import com.example.shipment_transmital_load_pdf.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("user")
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> findAll(@RequestParam int pageNo, @RequestParam int pageSize) {
        return userService.findAll(pageNo,pageSize);
    }

    @PostMapping
    public String save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("by-id-and-email")
    public User findByIdAndEmail(@RequestParam int id, @RequestParam String email) {
        return userService.findByIdAndEmail(id, email);
    }

    @DeleteMapping("/{id}")

    public String delete(@PathVariable int id) {
        return userService.delete(id);
    }
}
