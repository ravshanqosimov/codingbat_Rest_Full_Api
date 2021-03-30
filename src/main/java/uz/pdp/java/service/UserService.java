package uz.pdp.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.java.entity.StarBadge;
import uz.pdp.java.entity.User;
import uz.pdp.java.payload.UserDto;
import uz.pdp.java.payload.response.ApiResponse;
import uz.pdp.java.repository.StarBadgeRepository;
import uz.pdp.java.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StarBadgeRepository starBadgeRepository;

    // METHODS

    /**
     * ADD NEW USER
     *
     * @param userDto
     * @return ApiResponse
     */


    public ApiResponse add(UserDto userDto) {
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(userDto.getStarBadgeId());
        if (optionalStarBadge.isPresent()) {
            boolean exists = userRepository.existsByEmail(userDto.getEmail());
            if (!exists) {
                User user = new User();
                user.setEmail(userDto.getEmail());
                user.setPassword(userDto.getPassword());
                user.setStarBadge(optionalStarBadge.get());
                userRepository.save(user);
                return new ApiResponse("new User added", true);
            }
            return new ApiResponse("Email already exists", false);
        }
        return new ApiResponse("StarBadge not found", false);

    }

    /**
     * GET ALL USER
     *
     * @return List<User>
     */

    public List<User> getAll() {
        return userRepository.findAll();
    }


    /**
     * GET ONE USER BY ID
     *
     * @param id
     * @return User or null
     */
    public User getOneById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);

    }

    /**
     * EDIT USER
     *
     * @param id,userDto
     * @return Apiresponse
     */

    //update
    public ApiResponse edit(Integer id, UserDto userDto) {

        Optional<User> optionalUser = userRepository.findById(userDto.getStarBadgeId());
        if (optionalUser.isPresent()) {
            Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(userDto.getStarBadgeId());
            if (optionalStarBadge.isPresent()) {
                boolean exists = userRepository.existsByEmailAndIdNot(userDto.getEmail(), id);
                if (!exists) {
                    User user = optionalUser.get();
                    user.setEmail(userDto.getEmail());
                    user.setPassword(userDto.getPassword());
                    user.setStarBadge(optionalStarBadge.get());
                    userRepository.save(user);
                    return new ApiResponse(" User edited", true);
                }
                return new ApiResponse("user already exists", false);
            }
            return new ApiResponse("StarBadge not found", false);
        }
        return new ApiResponse("User not found", false);
    }

    /**
     * DELETE USER BY ID
     *
     * @param id
     * @return Apiresponse
     */
    public ApiResponse delete(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("user deleted ", true);
        } catch (Exception e) {
            return new ApiResponse("ERROR!!!", false);
        }
    }

}
