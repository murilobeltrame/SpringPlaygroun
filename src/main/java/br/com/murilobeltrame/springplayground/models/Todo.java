package br.com.murilobeltrame.springplayground.models;

import br.com.murilobeltrame.springplayground.controllers.TodoCreateRequest;
import br.com.murilobeltrame.springplayground.controllers.TodoUpdateRequest;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Validated
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter
    @NotNull
    @NotEmpty
    @NotBlank
    private String description;

    @Getter
    private boolean finished;

    // REQUIRED BY JPA
    private Todo() {
    }

    Todo(String description) {
        this.description = description;
        this.finished = false;
    }

    public static Todo from(TodoCreateRequest request) {
        return new Todo(request.getDescription());
    }

    public Todo with(TodoUpdateRequest request) {
        this.description = request.getDescription();
        this.finished = request.isFinished();
        return this;
    }
}
