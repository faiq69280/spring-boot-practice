package com.example.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record SaveFailureResponse<T>(T metadata, String msg) {
}
