package dev.ebento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.OffsetDateTime;

@Data
@Builder
@FieldNameConstants
public class PersonDTO {

    Long personId;
    String personName;
    OffsetDateTime personBirthDate;

}
