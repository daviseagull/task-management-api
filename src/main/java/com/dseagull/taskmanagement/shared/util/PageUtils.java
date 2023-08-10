package com.dseagull.taskmanagement.shared.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PageUtils {

    public static Pageable createPageable(int page, int size, String sortBy, String sortDir) {
        Sort sort = StringUtils.equals(sortDir.toUpperCase(), "ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        return PageRequest.of(page, size, sort);
    }
}
