package app.planet.application;


import app.planet.application.result.GetPlanetUsersResult;
import app.planet.core.context.UserHolder;
import app.planet.domain.Repository.PlanetMtmUserRepository;
import app.planet.domain.Repository.PlanetRepository;
import app.planet.domain.exception.PlanetNotFoundException;
import app.planet.domain.model.planet.Planet;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PlanetDataApplicationService {

    @Resource
    private PlanetRepository planetRepository;

    @Resource
    private PlanetMtmUserRepository planetMtmUserRepository;


    //keypoint: 获取当前Planet用户数及用户数较昨日增幅
    public GetPlanetUsersResult getPlanetUsersResult() throws PlanetNotFoundException {
        Long masterId = UserHolder.getUser().getId();
        Planet planet = planetRepository.findPlanetByMasterId(masterId);
        if (planet==null) {
            throw new PlanetNotFoundException();
        }
        Integer users = planet.getUsers();
        Integer usersYesterday = planetMtmUserRepository.findUsersYesterday(planet.getId());
        return new GetPlanetUsersResult(users,  ((users-usersYesterday)*1.00/usersYesterday));
    }
    //keypoint: 获取今日新增用户数及今日新增用户数较昨日增幅

    //keypoint: 获取今日登录用户数及今日登录用户数较昨日增幅

    //keypoint: 获取今日动态总数及今日动态总数较昨日增幅

}
