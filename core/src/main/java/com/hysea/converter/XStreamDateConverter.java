//package com.hysea.converter;
//
//import com.thoughtworks.xstream.converters.SingleValueConverter;
//
//public class XStreamDateConverter implements SingleValueConverter {
//    @Override
//    public boolean canConvert(Class arg0) {
//        return Date.class == arg0;
//    }
//
//    @Override
//    public Object fromString(String arg0) {
//        try {
//            return DateUtil.parse(arg0, "yyyy-MM-dd");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public String toString(Object arg0) {
//        return DateUtil.getDateStrByPattern((Date) arg0, "yyyy-MM-dd");
//    }
//}