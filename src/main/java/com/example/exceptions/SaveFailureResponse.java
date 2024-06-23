package com.example.exceptions;

public record SaveFailureResponse<T>(T metadata, String msg) {
}
