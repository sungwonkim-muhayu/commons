package org.github.swsz2.commons.model;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Pagination {

    @Getter
    @ToString
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response<T> {
        private List<T> content;
        private Metadata metadata;

        public static <T> Response<T> of(final Page<T> page) {
            return Response.<T>builder()
                    .content(page.getContent())
                    .metadata(Metadata.builder()
                            .first(page.isFirst())
                            .last(page.isLast())
                            .totalPages(page.getTotalPages())
                            .totalElements(page.getTotalElements())
                            .size(page.getSize())
                            .page(page.getNumber() + 1)
                            .build())
                    .build();
        }

        public <S> Response<S> map(Function<? super T, ? extends S> converter) {
            return Response
                    .<S>builder()
                    .content(getConvertedContent(converter))
                    .metadata(metadata)
                    .build();
        }

        private <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {
            return content.stream().map(converter).collect(Collectors.toList());
        }

        @Builder
        @Getter
        @ToString
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Metadata {
            private boolean first;
            private boolean last;
            private int totalPages;
            private long totalElements;
            private int size;
            private int page;
        }
    }


    @Getter
    @ToString
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request {
        @Builder.Default
        private int page = 1;
        @Builder.Default
        private int size = 10;

        public long offset() {
            return (long) (getPage() - 1) * getSize();
        }

        public int getPage() {
            if (page <= 0) {
                page = 1;
            }
            return page;
        }

        public int getSize() {
            if (size <= 0) {
                size = 10;
            }
            return size;
        }

        public Pageable pageable() {
            return PageRequest.of(getPage() - 1, getSize());
        }
    }


}
