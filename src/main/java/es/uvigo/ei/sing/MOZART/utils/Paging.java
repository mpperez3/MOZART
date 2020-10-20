package es.uvigo.ei.sing.MOZART.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Paging {
    private long count;
    private int current = 1;
    private int limit = 1;
    private boolean nextPage = false;
    private boolean prevPage = false;
    private Map<String, String> options = new HashMap<>();
    private String order = "";
    private int page = 1;
    private int pageCount = 1;
    private String paramType = "named";
}
