package com.example.springcqrsexample.common.util;

public enum JwtTokenPayloadKeySet {
    USER_ID, REFRESH;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
