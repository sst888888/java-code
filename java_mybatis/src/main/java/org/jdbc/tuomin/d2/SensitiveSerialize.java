package org.jdbc.tuomin.d2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Objects;
@NoArgsConstructor
public class SensitiveSerialize extends JsonSerializer<String> implements ContextualSerializer {
    /**
     * 脱敏类型
     */
    private SensitiveTypeEnum sensitiveTypeEnum;

    /**
     * 前几位不脱敏
     */
    private Integer prefixNoMaskLen;

    /**
     * 最后几位不脱敏
     */
    private Integer suffixNoMaskLen;

    /**
     * 用什么打码
     */
    private String symbol;

    public SensitiveSerialize(SensitiveTypeEnum sensitiveTypeEnum, Integer prefixNoMaskLen, Integer suffixNoMaskLen, String symbol) {
        this.sensitiveTypeEnum = sensitiveTypeEnum;
        this.prefixNoMaskLen = prefixNoMaskLen;
        this.suffixNoMaskLen = suffixNoMaskLen;
        this.symbol = symbol;
    }

    @Override
    public void serialize(final String value, final JsonGenerator gen,
                          final SerializerProvider serializerProvider) throws IOException {
        switch (sensitiveTypeEnum) {
            case CUSTOMER:
                gen.writeString(DesensitizedUtils.desValue(value, prefixNoMaskLen, suffixNoMaskLen, symbol));
                break;
            case NAME:
                gen.writeString(DesensitizedUtils.chineseName(value));
                break;
            case ID_NUM:
                gen.writeString(DesensitizedUtils.idCardNum(value));
                break;
            case PHONE_NUM:
                gen.writeString(DesensitizedUtils.mobilePhone(value));
                break;
            default:
                throw new IllegalArgumentException("unknown sensitive type enum " + sensitiveTypeEnum);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(final SerializerProvider prov,
                                              final BeanProperty property) throws JsonMappingException {

        if (Objects.isNull(property)){
            return prov.findNullValueSerializer(null);
        }

        if (Objects.equals(String.class, property.getType().getRawClass())) {
            Sensitive annotation = property.getAnnotation(Sensitive.class);
            if (Objects.isNull(annotation)){
                annotation = property.getContextAnnotation(Sensitive.class);
            }

            if (Objects.nonNull(annotation)) {
                return new SensitiveSerialize(annotation.type(),
                        annotation.prefixNoMaskLen(), annotation.suffixNoMaskLen(), annotation.symbol());
            }

        }
        return prov.findValueSerializer(property.getType(), property);
    }

}
