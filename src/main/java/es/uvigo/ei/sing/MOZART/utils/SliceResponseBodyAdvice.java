package es.uvigo.ei.sing.MOZART.utils;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ResponseBodyAdvice to support Spring data Slice object in JSON responses.
 * If the value is a slice, we'll write the List as an array, and add a header to the HTTP response
 *
 * @author blagerweij
 */
@SuppressWarnings("ALL")
@ControllerAdvice
public class SliceResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    // https://stackoverflow.com/questions/31913410/spring-data-pagination-returns-no-results-with-jsonview
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Slice) {
            Slice slice = (Slice) body;
            response.getHeaders().add("X-Has-Next-Page", String.valueOf(slice.hasNext()));
            return slice.getContent();
        }
        return body;
    }
}