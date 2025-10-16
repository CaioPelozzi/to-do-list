package todolist.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import todolist.demo.model.Todo;

public interface todoRepository extends JpaRepository<Todo, Long>{

}
