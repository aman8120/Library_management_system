package com.example.lib_mng_sys.mapper;

import com.example.lib_mng_sys.dto.request.BookRequest;
import com.example.lib_mng_sys.dto.response.BookResponse;
import com.example.lib_mng_sys.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "publisher.name", target = "publisherName")
    @Mapping(source = "image", target = "image")
    public BookResponse mapToBookResponse(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "available", constant = "true")
    @Mapping(target = "image", ignore = true)
    public void mapToNewBook(BookRequest bookRequest,@MappingTarget Book book);

    List<BookResponse> mapToBookResponseList(List <Book> books);
}
