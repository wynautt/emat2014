package com.zephyrusapps.edu.emat.service.config.swagger;

import com.mangofactory.swagger.configuration.ExtensibilityModule;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

public class EmatExtensibilityModule extends ExtensibilityModule {

    @Override
    protected void customizeExcludedResources(List<String> excludedResources) {
        excludedResources.add("/connect");

        /*
        //Only Controllers with the swagger Api annotations should be included
        Map<RequestMappingInfo, HandlerMethod> handlerMethods =
                this.requestMappingHandlerMapping.getHandlerMethods();
        if (null == handlerMethods) {
            return;
        }

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry :
                handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = entry.getValue();
            boolean hasAnnotation = false;
            Class beanType = handlerMethod.getBeanType();
            Annotation[] declaredAnnotations = beanType.getDeclaredAnnotations();
            if (null != declaredAnnotations) {
                for (Annotation a : declaredAnnotations) {
                    if (isSwaggerAnnotation(a)) {
                        hasAnnotation = true;
                        break;
                    }
                }
            }
            if (!hasAnnotation) {
                excludedResources.addAll(controllerUris(beanType));
            }
        }
        */
    }
}
