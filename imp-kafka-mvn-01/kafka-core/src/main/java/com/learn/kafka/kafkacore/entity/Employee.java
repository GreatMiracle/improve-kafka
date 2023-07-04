package com.learn.kafka.kafkacore.entity;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String employeeId;
    private String name;
    private LocalDate birthDate;
}
