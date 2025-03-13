package org.sanaa.youcode.redline.unirent.seeder;

import org.sanaa.youcode.redline.unirent.model.dto.Request.UniversityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.entity.University;
import org.sanaa.youcode.redline.unirent.repository.UniversityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UniversitySeeder implements CommandLineRunner {

    private final UniversityRepository universityRepository;

    public UniversitySeeder(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public void run(String... args) {
        List<UniversityRequestDTO> universities = List.of(
            new UniversityRequestDTO("Université Mohammed V", "Rabat", 34.0224, -6.8341),
            new UniversityRequestDTO("Université Cadi Ayyad", "Marrakech", 31.6342, -8.0089),
            new UniversityRequestDTO( "Université Ibn Tofail", "Kenitra", 34.2602, -6.5797),
            new UniversityRequestDTO("Université Hassan II", "Casablanca", 33.5731, -7.5898),
            new UniversityRequestDTO("Université Sidi Mohamed Ben Abdellah", "Fes", 34.0181, -5.0078)
        );

        universities.forEach(dto -> universityRepository.save(
            new University(dto.getId(), dto.getName(), dto.getCity(), dto.getLatitude(), dto.getLongitude())
        ));
    }
}
