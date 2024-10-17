package com.investor.bee.controller;

import com.investor.bee.model.user.CreateUserDto;
import com.investor.bee.model.user.DataUserDto;
import com.investor.bee.model.user.UpdateUserDto;
import com.investor.bee.model.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<DataUserDto> create(@RequestBody @Valid CreateUserDto userDto, UriComponentsBuilder uriBuilder) {
        var user = userService.save(userDto);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.id()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    // Ler todos
    @GetMapping
    public ResponseEntity<Page<DataUserDto>> read(Pageable pageable) {
        var lista = userService.read(pageable);
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    public ResponseEntity<UpdateUserDto> update(@RequestBody @Valid UpdateUserDto userDto) {
        var user = userService.update(userDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataUserDto> findById(@PathVariable Long id) {
        var user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/read-with-filter/{includeDeleted}")
    public ResponseEntity<Page<DataUserDto>> readWithFilter(@PathVariable boolean includeDeleted) {
        var lista = userService.findUsers(includeDeleted);

        return ResponseEntity.ok(lista);
    }
}