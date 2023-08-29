package app.planet.domain.Repository;

import app.planet.domain.model.planet.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet,Long> {


    Planet findPlanetByMasterId(Long id);

}
