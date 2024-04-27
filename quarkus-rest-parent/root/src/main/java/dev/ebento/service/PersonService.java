package dev.ebento.service;

import dev.ebento.dto.PersonDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@ApplicationScoped
public class PersonService {

    public Uni<PersonDTO> findById() {
        return Uni.createFrom().item(PersonDTO.builder()
                .personId(1L)
                .personName("Eduardo Bento")
                .personBirthDate(OffsetDateTime.of(1890, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
                .build());
    }

    public Multi<PersonDTO> findAll() {
        return Multi.createFrom().items(
                PersonDTO.builder()
                        .personId(1L)
                        .personName("Eduardo Bento")
                        .personBirthDate(OffsetDateTime.of(1890, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)).build(),
                PersonDTO.builder()
                        .personId(2L)
                        .personName("Eduardo Zento")
                        .personBirthDate(OffsetDateTime.of(1990, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)).build(),
                PersonDTO.builder()
                        .personId(3L)
                        .personName("Eduardo Vento")
                        .personBirthDate(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)).build(),
                PersonDTO.builder()
                        .personId(4L)
                        .personName("Eduardo Chento")
                        .personBirthDate(OffsetDateTime.of(2010, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)).build());
    }

}
