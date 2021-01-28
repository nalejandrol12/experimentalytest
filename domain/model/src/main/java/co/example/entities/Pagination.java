package co.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Pagination {
    private final int totalProducts;
    private final int totalPages;
    private final int currentPage;
}
