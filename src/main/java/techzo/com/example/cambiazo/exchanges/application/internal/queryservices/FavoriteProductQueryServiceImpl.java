package techzo.com.example.cambiazo.exchanges.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.UserNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.FavoriteProduct;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllFavoriteProductByUserId;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllFavoriteProductsQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.FavoriteProductQueryService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.FavoriteProductRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.UserRepository;

import java.util.List;


@Service
public class FavoriteProductQueryServiceImpl implements FavoriteProductQueryService {

    private final FavoriteProductRepository favoriteProductRepository;

    private final UserRepository userRepository;

    public FavoriteProductQueryServiceImpl(FavoriteProductRepository favoriteProductRepository, UserRepository userRepository){
        this.favoriteProductRepository=favoriteProductRepository;
        this.userRepository=userRepository;
    }

    @Override
    public List<FavoriteProduct>handle(GetAllFavoriteProductsQuery query){
        return favoriteProductRepository.findAll();
    }

    @Override
    public List<FavoriteProduct>handle(GetAllFavoriteProductByUserId query){
        User user = userRepository.findById(query.userId())
                .orElseThrow(() -> new UserNotFoundException(query.userId()));

        return favoriteProductRepository.findFavoriteProductByUserId(user);
    }

}
