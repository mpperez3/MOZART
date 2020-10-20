package es.uvigo.ei.sing.MOZART.utils;


import net.kaczmarzyk.spring.data.jpa.domain.PathSpecification;
import net.kaczmarzyk.spring.data.jpa.utils.Converter;
import net.kaczmarzyk.spring.data.jpa.utils.QueryContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Filters with {@code path between arg1 and arg2} where-clause.
 *
 * @author Tomasz Kaczmarzyk
 */
public class LocalTimeDateBetween<T>extends PathSpecification<T> {

    private final Converter converter;
    private LocalDateTime after;
    private LocalDateTime before;

    public LocalTimeDateBetween(QueryContext queryContext, String path, String[] args, Converter converter) throws ParseException {
        super(queryContext, path);

        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("expected 2 http params (date boundaries), but was: " + args);
        }



        String afterDateStr = args[0];
        String beforeDateStr = args[1];
        this.after = this.convertToDate(afterDateStr);
        this.before = this.convertToDate(beforeDateStr);
        this.converter = converter;

    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.between(this.<LocalDateTime>path(root), after, before);
    }


    public LocalDateTime convertToDate(String value) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(value, formatter);

    }
}