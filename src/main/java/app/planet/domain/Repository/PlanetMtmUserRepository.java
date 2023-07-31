package app.planet.domain.Repository;

import app.planet.domain.model.planet_mtm_user.PlanetMtmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlanetMtmUserRepository extends JpaRepository<PlanetMtmUser,Long> {

    @Query(nativeQuery = true,value = "select count(*) from planet_mtm_user " +
            "where update_time < :anyDay AND planet_id=:planetId group by planet_id")
    Integer findUsersAnyDay(@Param("planetId") Long planetId ,@Param("anyDay") String anyDay);

    @Query(nativeQuery = true,value = "select count(*) from planet_mtm_user " +
            "where update_time < current_date AND planet_id=:planetId group by planet_id")
    Integer findUsersYesterday(@Param("planetId") Long planetId );
}
