package br.com.murilobeltrame.springplayground.repositories;

import br.com.murilobeltrame.springplayground.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodosRepository extends JpaRepository<Todo, Long> {
}
