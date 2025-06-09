package com.mysql.java;

import java.util.List;

import com.mysql.java.board.Task;
import com.mysql.java.board.TaskDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaApplication {

	public static void main(String[] args) {

		SpringApplication.run(JavaApplication.class, args);

		TaskDAO dao = new TaskDAO();

		Task newTask = new Task(0, "Estuda Java", "Estudar JDBC e MySQL", "TODO", null);
		dao.insert(newTask);

		List<Task> tasks = dao.getAllTalks();
		for(Task t : tasks){
			System.out.println(t);
		}

		dao.updateStatus(1,"DONE");

	}

}
