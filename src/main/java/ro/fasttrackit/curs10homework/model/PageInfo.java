package ro.fasttrackit.curs10homework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageInfo<T> {
    private List<T> items;
    private int pageSize;
    private int currentPage;
    private long totalItems;
}
