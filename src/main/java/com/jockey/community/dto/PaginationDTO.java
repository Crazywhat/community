package com.jockey.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirst;
    private boolean showLast;
    private Integer page;
    private Integer totalPages;
    private List<Integer> pages = new ArrayList<>();

    public PaginationDTO(List<QuestionDTO> questionDTOS, Integer page, Integer totalPages){

        this.questions = questionDTOS;
        this.page = page;
        this.totalPages = totalPages;

        this.showPrevious = (page != 1);
        this.showNext = (page != totalPages);

        this.showFirst = (page > 4);
        this.showLast = (page < totalPages - 3);

        for (int i = page-3; i <= page+3; i++) {
            if(i > 0 && i <= totalPages)
                pages.add(i);
        }

    }


}
