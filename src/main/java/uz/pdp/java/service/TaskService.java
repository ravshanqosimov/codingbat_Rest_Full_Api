package uz.pdp.java.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.java.entity.Category;
import uz.pdp.java.entity.Language;
import uz.pdp.java.entity.Task;
import uz.pdp.java.payload.LanguageDto;
import uz.pdp.java.payload.TaskDto;
import uz.pdp.java.payload.response.ApiResponse;
import uz.pdp.java.repository.CategoryRepository;
import uz.pdp.java.repository.LanguageRepository;
import uz.pdp.java.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;


    @Autowired
    CategoryRepository categoryRepository;
    // METHODS

    /**
     * ADD NEW TASK
     *
     * @param taskDto
     * @return ApiResponse
     */


    public ApiResponse add(TaskDto taskDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        if (optionalCategory.isPresent()) {
            Task task = new Task();
            task.setName(taskDto.getName());
            task.setText(taskDto.getText());
            task.setExamples(taskDto.getExamples());
            task.setSolution(taskDto.getSolution());
            task.setCategory(optionalCategory.get());
            task.setHasStar(taskDto.isHasStar());
            taskRepository.save(task);
            return new ApiResponse("new task added", true);
        }
        return new ApiResponse("category ID not found", false);
    }

    /**
     * GET ALL TASK
     *
     * @return List<Task>
     */

    public List<Task> getAll() {
        return taskRepository.findAll();
    }


    /**
     * GET ONE TASK BY ID
     *
     * @param id
     * @return Task or null
     */
    public Task getOneById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);

    }

    /**
     * EDIT TASK
     *
     * @param id,Task
     * @return Apiresponse
     */

    //update
    public ApiResponse edit(Integer id, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {

            Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
            if (optionalCategory.isPresent()) {
                Task editingTask = optionalTask.get();
                editingTask.setName(taskDto.getName());
                editingTask.setText(taskDto.getText());
                editingTask.setExamples(taskDto.getExamples());
                editingTask.setSolution(taskDto.getSolution());
                editingTask.setCategory(optionalCategory.get());
                editingTask.setHasStar(taskDto.isHasStar());
                taskRepository.save(editingTask);
                return new ApiResponse("new task added", true);
            }
            return new ApiResponse("category ID not found", false);
        }
        return new ApiResponse("task ID not found", false);
    }

    /**
     * DELETE TASK BY ID
     *
     * @param id
     * @return Apiresponse
     */
    public ApiResponse delete(Integer id) {
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("task deleted ", true);
        } catch (Exception e) {
            return new ApiResponse("ERROR!!!", false);
        }
    }

}