package br.com.murilobeltrame.springplayground.controllers;

import lombok.Getter;
import lombok.Setter;

public class TodoCreateRequest {
    @Getter
    @Setter
    private String description;
}
