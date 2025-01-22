package com.magomed.events;

public record OrderCmd(String action, String article, int amount) {}
