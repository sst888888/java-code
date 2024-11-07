package org.cp.springframework.core.convert;
import org.jetbrains.annotations.Nullable;

/**
 * @author: cp
 * @date: 2024-11-07 15:46
 */
public interface ConversionService {

    /** Return {@code true} if objects of {@code sourceType} can be converted to the {@code targetType}. */
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    /** Convert the given {@code source} to the specified {@code targetType}. */
    <T> T convert(Object source, Class<T> targetType);

}
