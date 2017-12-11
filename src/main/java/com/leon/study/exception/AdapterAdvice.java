package com.leon.study.exception;

import com.leon.study.utils.Versioned;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import static java.lang.Integer.parseInt;

@ControllerAdvice
public class AdapterAdvice implements ResponseBodyAdvice<Versioned> {

    private static final String PROTOCOL_VERSION_HEADER = "X-Protocol-Version";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        for (Class<?> interf : ((Class) (((ParameterizedTypeImpl) methodParameter.getGenericParameterType()).getActualTypeArguments())[0]).getInterfaces()) {
            if (interf.equals(Versioned.class)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Versioned beforeBodyWrite(Versioned versioned,
                                     MethodParameter methodParameter,
                                     MediaType mediaType,
                                     Class<? extends HttpMessageConverter<?>> aClass,
                                     ServerHttpRequest serverHttpRequest,
                                     ServerHttpResponse serverHttpResponse) {
        String version = serverHttpRequest.getHeaders().getFirst(PROTOCOL_VERSION_HEADER);

        if (StringUtils.isEmpty(version)) {
            throw new RuntimeException(String.format("Missing '%s' header.", PROTOCOL_VERSION_HEADER));
        }

        return versioned.toVersion(parseInt(version));
    }

}
