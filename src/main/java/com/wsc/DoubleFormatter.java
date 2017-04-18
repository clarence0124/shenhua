package com.wsc;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/4/18.
 */
public class DoubleFormatter implements ValueFilter {

    DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public Object process(Object object, String name, Object value) {
        if(null != value && value instanceof Double) {
            Double d = (Double) value;
            return df.format(d);
        }
        return value;
    }
}
