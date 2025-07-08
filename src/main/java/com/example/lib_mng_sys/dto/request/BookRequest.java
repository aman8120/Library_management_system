package com.example.lib_mng_sys.dto.request;

import com.example.lib_mng_sys.dto.rules.OnlyAlphabetsAndSpace;
import com.example.lib_mng_sys.dto.rules.OnlyAlphabetsAndUnderScores;
import com.example.lib_mng_sys.model.Author;
import com.example.lib_mng_sys.model.Publisher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {

    @OnlyAlphabetsAndSpace
    private String title;

    @NotNull
    @NotBlank
    private String category;

    @NotNull
    @NotBlank
    private String authorName;

    @NotNull
    @NotBlank
    private String publisherName;

    @NotNull
    @NotBlank
    private String image;
}
