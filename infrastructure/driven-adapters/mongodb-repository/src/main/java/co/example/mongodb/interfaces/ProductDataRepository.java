package co.example.mongodb.interfaces;

import co.example.mongodb.data.CompleteProductData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductDataRepository extends ReactiveCrudRepository<CompleteProductData, String>,
        ReactiveQueryByExampleExecutor<CompleteProductData> {
    Flux<CompleteProductData> findByFeaturedProductIsTrue();

    @Query(value = "{'productName': {$regex: ?0, $options: 'i'}}")
    Flux<CompleteProductData> findByName(String name, Pageable pageable);

    @Query(value = "{'productName': {$regex: ?0, $options: 'i'}}", count = true)
    Mono<Long> countByName(String name);
}
