package com.fsc.external.portal.mappers;

import com.fsc.external.portal.dtos.PageResponse;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

public interface BasePageResponseMapper<E, D> {

    @Named("getCurrentPageNumber")
    static int getCurrentPageNumber(int pageNumberInPageable) {
        return pageNumberInPageable + 1;
    }

    @Mapping(source = "totalPages", target = "totalNumberOfPages")
    @Mapping(source = "totalElements", target = "totalNumberOfElements")
    @Mapping(source = "pageable.pageNumber", target = "currentPage", qualifiedByName = "getCurrentPageNumber")
    @Mapping(source = "pageable.pageSize", target = "pageSize")
    @Mapping(source = "content", target = "result", defaultExpression = "java(java.util.List.of())")
    PageResponse<D> toPageResponse(PageImpl<E> pageInfo);

    @Mapping(source = "totalPages", target = "totalNumberOfPages")
    @Mapping(source = "totalElements", target = "totalNumberOfElements")
    @Mapping(source = "pageable.pageNumber", target = "currentPage")
    @Mapping(source = "pageable.pageSize", target = "pageSize")
    @Mapping(source = "content", target = "result", defaultExpression = "java(java.util.List.of())")
    PageResponse<D> toPageResponse(com.fsc.external.portal.data.PageImpl<E> pageInfo);
}
