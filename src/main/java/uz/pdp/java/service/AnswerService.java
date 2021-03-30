package uz.pdp.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.java.entity.*;
import uz.pdp.java.payload.AnswerDto;
import uz.pdp.java.payload.CategoryDto;
import uz.pdp.java.payload.response.ApiResponse;
import uz.pdp.java.repository.AnswerRepository;
import uz.pdp.java.repository.TaskRepository;
import uz.pdp.java.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;


    /**
     * ADD NEW ANSWER
     *
     * @param answerDto
     * @return ApiResponse
     */


    public ApiResponse add(AnswerDto answerDto) {
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (optionalTask.isPresent()) {
            Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
            if (optionalUser.isPresent()) {
                Answer answer = new Answer();
                answer.setBody(answerDto.getBody());
                answer.setTask(optionalTask.get());
                answer.setUser(optionalUser.get());
                answer.setTrue(answer.isTrue());
                answerRepository.save(answer);
                return new ApiResponse("new answer added", true);
            }
            return new ApiResponse("USER ID not found", false);
        }
        return new ApiResponse("TASK ID not found", false);
    }

    /**
     * GET ALL ANSWER
     *
     * @return List<Answer>
     */

    public List<Answer> getAll() {
        return answerRepository.findAll();
    }


    /**
     * GET ONE ANSWER BY ID
     *
     * @param id
     * @return Answer or null
     */
    public Answer getOneById(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElse(null);

    }

    /**
     * EDIT ANSWER
     *
     * @param id,AnswerDto
     * @return Apiresponse
     */

    //update
    public ApiResponse edit(Integer id, AnswerDto answerDto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()) {


            Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
            if (optionalTask.isPresent()) {
                Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
                if (optionalUser.isPresent()) {
                    Answer answer = optionalAnswer.get();
                    answer.setBody(answerDto.getBody());
                    answer.setTask(optionalTask.get());
                    answer.setUser(optionalUser.get());
                    answer.setTrue(answer.isTrue());
                    answerRepository.save(answer);
                    return new ApiResponse("new answer added", true);
                }
                return new ApiResponse("USER ID not found", false);
            }
            return new ApiResponse("TASK ID not found", false);
        }

        return new ApiResponse("ANSWER ID not found", false);
    }

    /**
     * DELETE ANSWER BY ID
     *
     * @param id
     * @return Apiresponse
     */
    public ApiResponse delete(Integer id) {
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("ANSWER deleted ", true);
        } catch (Exception e) {
            return new ApiResponse("ERROR!!!", false);
        }
    }

}
