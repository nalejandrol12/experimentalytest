package co.example.mongodb.interfaces;

import co.example.mongodb.data.ProductData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductDataRepository extends ReactiveCrudRepository<ProductData, String>,
        ReactiveQueryByExampleExecutor<ProductData> {
    Flux<ProductData> findByFeaturedProductIsTrue();

    @Query(value = "{'productName': {$regex: ?0, $options: 'i'}}")
    Flux<ProductData> findByProductName(String productName, Pageable pageable);

    @Query(value = "{'productName': {$regex: ?0, $options: 'i'}}", count = true)
    Mono<Long> countByProductName(String productName);
}
