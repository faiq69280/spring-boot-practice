package com.example.exceptions;

import com.example.ChatMessage;

public record MessageNotSavedResponse(ChatMessage msg, String description) {
}
