package br.com.murilobeltrame.springplayground.controllers;

import lombok.Getter;
import lombok.Setter;

public class TodoUpdateRequest {
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private boolean finished;
}
