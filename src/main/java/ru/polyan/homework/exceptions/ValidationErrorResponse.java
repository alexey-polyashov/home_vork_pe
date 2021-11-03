package ru.polyan.homework.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class ValidationErrorResponse {
    private final List<Violation> violations;
}
