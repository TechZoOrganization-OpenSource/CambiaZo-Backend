package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllUsersByMembershipIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllUsersQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetUserByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.UserCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.UserQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateUserResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.UpdateUserResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.UserResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Users Management Endpoints")
public class UsersController {

    private final UserCommandService userCommandService;

    private final UserQueryService userQueryService;

    public UsersController(UserCommandService userCommandService, UserQueryService userQueryService ){
        this.userCommandService=userCommandService;
        this.userQueryService=userQueryService;
    }

    @Operation(summary = "Create a new User", description = "Create a new User with the input data.")
    @PostMapping
    public ResponseEntity<UserResource>createUser(@RequestBody CreateUserResource resource){
        Optional<User> user= userCommandService.handle(CreateUserCommandFromResourceAssembler.toCommandFromResource(resource));
        return user.map(source ->new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(source),CREATED)).orElseGet(()->ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all Users", description = "Get all Users.")
    @GetMapping
    public ResponseEntity<List<UserResource>>getAllUsers(){
        var getAllUsersQuery=new GetAllUsersQuery();
        var user = userQueryService.handle(getAllUsersQuery);
        var userResource=user.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResource);
    }

    @Operation(summary = "Get all Users by Membership ID", description = "Get all Users by Membership ID.")
    @GetMapping("/membership/{id}")
    public ResponseEntity<List<UserResource>>getAllUsersByMembership(@PathVariable Long id){
        try{
            var getAllUsersByMembershipIdQuery=new GetAllUsersByMembershipIdQuery(id);
            var user = userQueryService.handle(getAllUsersByMembershipIdQuery);
            var userResource=user.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(userResource);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }


    @Operation(summary = "Get User by ID", description = "Get User by ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UserResource>getUserById(@PathVariable Long id){
        Optional<User>user=userQueryService.handle(new GetUserByIdQuery(id));
        return user.map(source->ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(()->ResponseEntity.notFound().build());
    }


    @Operation(summary = "Update User by ID", description = "Update User by ID.")
    @PutMapping("/edit/{userId}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource resource) {
        var updateUserCommand = UpdateUserCommandFromResourceAssembler.toCommandFromResource(userId, resource);
        var user = userCommandService.handle(updateUserCommand);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }



    @Operation(summary = "Delete User by ID", description = "Delete User by ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userCommandService.handleDeleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
  
}
