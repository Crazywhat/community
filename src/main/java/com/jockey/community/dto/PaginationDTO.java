package com.jockey.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirst;
    private boolean showLast;
    private Integer page;
    private Integer totalPages;
    private List<Integer> pages = new ArrayList<>();

    public PaginationDTO(List<T> data, Integer page, Integer totalPages, Integer totalSize){

        this.data = data;
        this.page = page;
        this.totalPages = totalPages;

        this.showPrevious = (page != 1 && totalSize != 0);
        this.showNext = (page != totalPages);

        this.showFirst = (page > 4);
        this.showLast = (page < totalPages - 3);

        for (int i = page-3; i <= page+3; i++) {
            if(i > 0 && i <= totalPages)
                pages.add(i);
        }

    }


}
