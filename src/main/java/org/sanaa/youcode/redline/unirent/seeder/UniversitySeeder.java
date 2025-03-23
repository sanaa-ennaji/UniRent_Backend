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
//        List<UniversityRequestDTO> universities = List.of(
//            new UniversityRequestDTO("Université Mohammed V", "Rabat", 34.0224, -6.8341),
//            new UniversityRequestDTO("Université Cadi Ayyad", "Marrakech", 31.6342, -8.0089),
//            new UniversityRequestDTO("Université Ibn Tofail", "Kenitra", 34.2602, -6.5797),
//            new UniversityRequestDTO("Université Hassan II", "Casablanca", 33.5731, -7.5898),
//            new UniversityRequestDTO("Université Sidi Mohamed Ben Abdellah", "Fes", 34.0181, -5.0078),
//            new UniversityRequestDTO("Université Abdelmalek Essaâdi", "Tétouan", 35.5733, -5.3684),
//            new UniversityRequestDTO("Université Chouaib Doukkali", "El Jadida", 33.2532, -8.5060),
//            new UniversityRequestDTO("Université Moulay Ismail", "Meknès", 33.8817, -5.5736),
//            new UniversityRequestDTO("Université Ibn Zohr", "Agadir", 30.4241, -9.5982),
//            new UniversityRequestDTO("Université Sultan Moulay Slimane", "Béni Mellal", 32.3395, -6.3498),
//            new UniversityRequestDTO("Université Al Akhawayn", "Ifrane", 33.5399, -5.1065)
//            );
//
//        universities.forEach(dto -> universityRepository.save(
//            new University(dto.getName(), dto.getCity(), dto.getLatitude(), dto.getLongitude())
//        ));
    }
}
