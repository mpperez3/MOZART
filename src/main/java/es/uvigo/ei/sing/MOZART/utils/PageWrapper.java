package es.uvigo.ei.sing.MOZART.utils;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageWrapper<T> {

    @JsonView(JSONViews.Datatable.class)
    private int rowsSize;
    @JsonView(JSONViews.Datatable.class)
    private int current;
    @JsonView(JSONViews.Datatable.class)
    private long total;
    @JsonView(JSONViews.Datatable.class)
    private int offset;
    @JsonView(JSONViews.Datatable.class)
    private int limit;
    @JsonView(JSONViews.Datatable.class)
    private List<T> rows;
    @JsonView(JSONViews.Datatable.class)
    private Paging paging;
    @JsonView(JSONViews.Datatable.class)
    private int pageCount;

    public PageWrapper(Page<T> resultPage) {
        this.rows = resultPage.getContent();
        this.current = resultPage.getNumber() + 1;
        this.rowsSize = resultPage.getSize();
        this.total = resultPage.getTotalElements();
        this.offset = (int) resultPage.getPageable().getOffset();
        this.limit = resultPage.getPageable().getPageSize();
        this.pageCount = resultPage.getTotalPages();

        Map<String, String> options = new HashMap<>();
        options.put("page", String.valueOf(current));
        this.paging = new Paging(this.total, current * limit, limit, this.hasMore(), this.hasPrevious(), options,
                resultPage.getSort().toString(), current, pageCount, "named");
    }

    public boolean hasMore() {
        return current > pageCount;
    }

    public boolean hasPrevious() {
        return current > 0 && total > 0;
    }
}