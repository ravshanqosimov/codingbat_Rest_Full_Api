package uz.pdp.java.service;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.java.entity.Language;
import uz.pdp.java.payload.LanguageDto;

import uz.pdp.java.payload.response.ApiResponse;
import uz.pdp.java.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    // METHODS

    /**
     * ADD NEW PROGRAMMING LANGUAGE
     *
     * @param languageDto
     * @return ApiResponse
     */


    public ApiResponse add(LanguageDto languageDto) {
        boolean existsByName = languageRepository.existsByName(languageDto.getName());
        if (existsByName)
            return new ApiResponse("language already exists", false);
        Language language = new Language();
        language.setName(languageDto.getName());
        language.setDescription(languageDto.getDescription());
        languageRepository.save(language);
        return new ApiResponse("new language saved", true);

    }

    /**
     * GET ALL PROGRAMMING LANGUAGES
     *
     * @return List<Language>
     */

    public List<Language> getAll() {
        return languageRepository.findAll();
    }


    /**
     * GET ONE PROGRAMMING LANGUAGE BY ID
     *
     * @param id
     * @return Language or null
     */
    public Language getOneById(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);

    }

    /**
     * EDIT PROGRAMMING LANGUAGE
     * @param id,LanguageDto
     * @return Apiresponse
     */

    //update
    public ApiResponse edit(Integer id, LanguageDto languageDto) {

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) {
            boolean exists = languageRepository.existsByNameAndIdNot(languageDto.getName(), id);
            if (!exists) {
                Language editinglanguage = optionalLanguage.get();
                editinglanguage.setName(languageDto.getName());
                editinglanguage.setDescription(languageDto.getDescription());
                languageRepository.save(editinglanguage);
                return new ApiResponse("Laguage successful edited", true);
            }
            return new ApiResponse("language already exists", false);
        }
        return new ApiResponse("ID not found", false);
    }

    /**
     * DELETE LANGUAGE BY ID
     * @param id
     * @return Apiresponse
     */
    public ApiResponse delete(Integer id) {
        try {
            languageRepository.deleteById(id);
            return new ApiResponse("language deleted ", true);
        } catch (Exception e) {
            return new ApiResponse("ERROR!!!", false);
        }
    }

}
