package co.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class PaginationProduct {
    private final List<Product> products;
    private final Pagination pagination;
}
