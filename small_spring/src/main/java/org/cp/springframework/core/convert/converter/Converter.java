package org.cp.springframework.core.convert.converter;


public interface Converter <S, T>{

    T convert(S source);
}
