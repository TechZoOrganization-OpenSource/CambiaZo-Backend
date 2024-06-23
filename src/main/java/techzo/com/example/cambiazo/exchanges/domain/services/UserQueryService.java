



package techzo.com.example.cambiazo.exchanges.domain.services;


import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllUsersByMembershipIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllUsersQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);

    List<User>handle(GetAllUsersByMembershipIdQuery query);
    Optional<User> handle(GetUserByIdQuery query);
}
