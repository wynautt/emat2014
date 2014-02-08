package com.zephyrusapps.edu.emat.service.persistence;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Date: 18-05-2013
 * Time: 23:36
 */
public class OfyService {

    private static Logger LOG = LoggerFactory.getLogger(OfyService.class);

    static {
        LOG.info("Starting objectify service ...");
        for(Class<?> clazz: doScan("com.zephyrusapps.edu.emat.service.domain")) {
            ObjectifyService.register(clazz);
            LOG.info("Objectify entity registered: " + clazz.getName());
        }
    }

    private static List<Class<?>> doScan(String ...basePackages) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (String basePackage : basePackages) {
            LOG.info("Scanning package [" + basePackage + "]");
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
            scanner.addIncludeFilter(new AnnotationTypeFilter(com.googlecode.objectify.annotation.Entity.class));
            //scanner.addIncludeFilter(new AnnotationTypeFilter(javax.persistence.Entity.class));
            Set<BeanDefinition> candidates = scanner.findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                Class<?> clazz = ClassUtils.resolveClassName(candidate.getBeanClassName(), ClassUtils.getDefaultClassLoader());
                classes.add(clazz);
            }
        }
        return classes;
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
