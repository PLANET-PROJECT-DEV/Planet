package app.planet.application;


import app.planet.application.result.planet.GetPlanetNewUsersResult;
import app.planet.application.result.planet.GetPlanetUsersLatelyResult;
import app.planet.application.result.planet.GetPlanetUsersResult;
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
    public GetPlanetUsersResult getPlanetUsers() throws PlanetNotFoundException {
        Planet planet = getPlanet();
        Integer users = planet.getUsers();
        System.out.println(users);
        Integer usersYesterday = planetMtmUserRepository.findUsersYesterday(planet.getId());
        if (usersYesterday==0){
            return new GetPlanetUsersResult(users,0.00);
        }
        return new GetPlanetUsersResult(users,  ((users-usersYesterday)*1.00/usersYesterday));
    }
    //keypoint: 获取n天前用户数（用于绘制近期用户数变化图）
    public GetPlanetUsersLatelyResult getPlanetUsersLatelyResult(Integer daysAgo) throws PlanetNotFoundException {
        Planet planet = getPlanet();
        Integer users = planetMtmUserRepository.findUsersAnyDay(planet.getId(), daysAgo);
        return new GetPlanetUsersLatelyResult(users);
    }
    //keypoint: 获取今日新增用户数及今日新增用户数较昨日增幅
    public GetPlanetNewUsersResult getPlanetNewUsers() throws PlanetNotFoundException {
        Planet planet = getPlanet();
        Integer users = planet.getUsers();
        Integer usersYesterday;
        Integer usersBeforeYesterday;
        if (planetMtmUserRepository.findUsersAnyDay(planet.getId(), 2) == null) {
            usersBeforeYesterday = 0;
        } else {
            usersBeforeYesterday = planetMtmUserRepository.findUsersAnyDay(planet.getId(), 2);
        }
        if (planetMtmUserRepository.findUsersYesterday(planet.getId()) == null) {
            usersYesterday = 0;
        } else {
            usersYesterday = planetMtmUserRepository.findUsersYesterday(planet.getId());
        }
        if (usersYesterday - usersBeforeYesterday == 0) {
            return new GetPlanetNewUsersResult(users, 0.00);
        }
        Double amplify = ((users - usersYesterday) - (usersYesterday - usersBeforeYesterday)) * 1.00 / (usersYesterday - usersBeforeYesterday);
        return new GetPlanetNewUsersResult(users - usersYesterday, amplify);
    }
    //keypoint: 获取当前master账户下的planet
    private Planet getPlanet() throws PlanetNotFoundException {
        Long masterId = UserHolder.getUser().getId();
        Planet planet = planetRepository.findPlanetByMasterId(masterId);
        if (planet==null) {
            throw new PlanetNotFoundException();
        }
        return planet;
    }
    //keypoint: 获取今日planet登录用户数及今日登录用户数较昨日增幅


    //keypoint: 获取今日动态总数及今日动态总数较昨日增幅

}
