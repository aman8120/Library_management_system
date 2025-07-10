package com.example.lib_mng_sys.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookResponse {

    private Long id;
    private String title;
    private String category;
    private boolean available;
    private String authorName;
    private String publisherName;
    private String image;
}
