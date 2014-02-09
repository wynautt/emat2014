package com.zephyrusapps.edu.emat.service.persistence;

import com.zephyrusapps.edu.emat.service.operations.ViewExamOp;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class ViewExamOpFilterMap extends ViewExamOp {
    Map<String, Object> filterMap = new HashMap<String, Object>();

    public ViewExamOpFilterMap(ViewExamOp op) {
        BeanUtils.copyProperties(op, this);

        if(hasCourse()) {
            filterMap.put("course", getCourse());
        }
        if(hasYear()) {
            filterMap.put("year", getYear());
        }
        if(hasPhase()) {
            filterMap.put("phase", getPhase());
        }
    }

    public Map<String, Object> filterMap() {
        return filterMap;
    }

    public Set<String> filterKeys() {
        return filterMap.keySet();
    }

    public List<Object> filterValues() {
        return new ArrayList<Object>(filterMap.values());
    }

    public Object getValue(String filter) {
        return filterMap.get(filter);
    }

}
