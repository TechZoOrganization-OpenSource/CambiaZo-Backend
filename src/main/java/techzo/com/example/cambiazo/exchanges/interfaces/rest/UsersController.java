package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllUsersQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetUserByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.UserCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.UserQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateUserResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.UserResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserCommandService userCommandService;

    private final UserQueryService userQueryService;

    public UsersController(UserCommandService userCommandService, UserQueryService userQueryService ){
        this.userCommandService=userCommandService;
        this.userQueryService=userQueryService;
    }
  
    @PostMapping
    public ResponseEntity<UserResource>createUser(@RequestBody CreateUserResource resource){
        Optional<User> user= userCommandService.handle(CreateUserCommandFromResourceAssembler.toCommandFromResource(resource));
        return user.map(source ->new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(source),CREATED)).orElseGet(()->ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<UserResource>>getAllUsers(){
        var getAllUsersQuery=new GetAllUsersQuery();
        var user = userQueryService.handle(getAllUsersQuery);
        var userResource=user.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource>getUserById(@PathVariable Long id){
        Optional<User>user=userQueryService.handle(new GetUserByIdQuery(id));
        return user.map(source->ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(()->ResponseEntity.notFound().build());
    }


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
