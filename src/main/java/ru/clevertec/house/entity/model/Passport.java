
package ru.clevertec.house.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Embeddable
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"passport_series", "passport_number"}))
public class Passport {

    @Column(name = "passport_series")
    private String passportSeries;
    @Column(name = "passport_number")
    private String passportNumber;
}
