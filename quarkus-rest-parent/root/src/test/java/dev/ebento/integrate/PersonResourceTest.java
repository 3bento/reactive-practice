package dev.ebento.integrate;

import dev.ebento.dto.PersonDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class PersonResourceTest {

    @Test
    @DisplayName("Should find a person by id")
    void shouldFindAPersonById() {

        PersonDTO expectedPersonDTO = PersonDTO.builder()
                .personId(1L)
                .personName("Eduardo Bento")
                .personBirthDate(OffsetDateTime.of(1890, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
                .build();

        PersonDTO personDTO = given().pathParam(PersonDTO.Fields.personId, 1)
                .when().get("/person/{personId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .as(PersonDTO.class);

        assertThat(personDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedPersonDTO);
    }

    @Test
    @DisplayName("Should find persons")
    void shouldFindPersons() {

        List<PersonDTO> expectedPersons = List.of(PersonDTO.builder()
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
                        .personBirthDate(OffsetDateTime.of(2010, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)).build()
        );

        List<PersonDTO> persons = given()
                .when().get("/person")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(new TypeRef<>() {});

        assertThat(persons)
                .usingRecursiveComparison()
                .isEqualTo(expectedPersons);
    }


}
