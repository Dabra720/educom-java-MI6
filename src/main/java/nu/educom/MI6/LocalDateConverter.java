package nu.educom.MI6;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Converter(autoApply=true)
public class LocalDateConverter implements AttributeConverter<LocalDateTime, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDateTime d){
        return d !=null ? Date.from(d.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date d){
        return d !=null ? LocalDateTime.from(Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault())) : null;
    }

}
