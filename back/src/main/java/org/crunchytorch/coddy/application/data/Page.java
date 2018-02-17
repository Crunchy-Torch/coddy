package org.crunchytorch.coddy.application.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {

    private static final long serialVersionUID = -808950184664034440L;

    private long totalElement;

    private long totalPage;

    private List<T> hits;

    public Page() {
        // this blank constructor is needed by the library jackson
    }

    public Page(org.springframework.data.domain.Page<T> page) {
        this.totalElement = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.hits = new ArrayList<>();
        page.forEach(hits::add);
    }

    public Page(long totalElement, long totalPage, List<T> hits) {
        this.totalElement = totalElement;
        this.totalPage = totalPage;
        this.hits = hits;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public List<T> getHits() {
        return hits;
    }
}
