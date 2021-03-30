package uz.pdp.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.java.entity.Language;
import uz.pdp.java.entity.StarBadge;
import uz.pdp.java.payload.StarBadgeDto;
import uz.pdp.java.payload.response.ApiResponse;
import uz.pdp.java.repository.LanguageRepository;
import uz.pdp.java.repository.StarBadgeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StarBadgeService {
    @Autowired
    StarBadgeRepository starBadgeRepository;
    @Autowired

    LanguageRepository languageRepository;
    // METHODS

    /**
     * ADD NEW  STARBADGE
     *
     * @param starBadgeDto
     * @return ApiResponse
     */


    public ApiResponse add(StarBadgeDto starBadgeDto) {
        Optional<Language> optionalLanguage = languageRepository.findById(starBadgeDto.getLanguageId());
        if (optionalLanguage.isPresent()) {
            StarBadge badge = new StarBadge();
            badge.setScore(starBadgeDto.getScore());
            badge.setLanguage(optionalLanguage.get());
            starBadgeRepository.save(badge);
            return new ApiResponse("new star Badge saved", true);
        }
        return new ApiResponse("language ID not found", false);
    }

    /**
     * GET ALL STARBADGE
     *
     * @return List<StarBadge>
     */

    public List<StarBadge> getAll() {
        return starBadgeRepository.findAll();
    }


    /**
     * GET ONE STARBADGE BY ID
     *
     * @param id
     * @return starBadge or null
     */
    public StarBadge getOneById(Integer id) {
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(id);
        return optionalStarBadge.orElse(null);

    }

    /**
     * EDIT CATEGORY
     *
     * @param id,StarBadgeDto
     * @return Apiresponse
     */

    //update
    public ApiResponse edit(Integer id, StarBadgeDto starBadgeDto) {
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(id);
        if (optionalStarBadge.isPresent()) {
            Optional<Language> optionalLanguage = languageRepository.findById(starBadgeDto.getLanguageId());
            if (optionalLanguage.isPresent()) {
                StarBadge badge = optionalStarBadge.get();
                badge.setScore(starBadgeDto.getScore());
                badge.setLanguage(optionalLanguage.get());
                starBadgeRepository.save(badge);
                return new ApiResponse("STARBADGE edited", true);
            }
            return new ApiResponse("language ID not found", false);
        }
        return new ApiResponse("STARBADGE ID not found", false);
    }

    /**
     * DELETE STARBADGE BY ID
     *
     * @param id
     * @return Apiresponse
     */
    public ApiResponse delete(Integer id) {
        try {
            starBadgeRepository.deleteById(id);
            return new ApiResponse("STARBADGE deleted ", true);
        } catch (Exception e) {
            return new ApiResponse("ERROR!!!", false);
        }
    }

}