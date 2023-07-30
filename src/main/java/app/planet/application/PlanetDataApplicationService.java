package app.planet.application;


import app.planet.domain.Repository.PlanetRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PlanetDataApplicationService {

    @Resource
    private PlanetRepository planetRepository;


    //keypoint: 获取当前Planet用户数

    //keypoint: 获取Planet用户数较昨日增幅

    //keypoint: 获取今日新增用户数

    //keypoint: 获取今日新增用户数较昨日增幅

    //keypoint: 获取今日登录用户数

    //keypoint: 获取今日登录用户数较昨日增幅

    //keypoint: 获取今日动态总数

    //keypoint: 获取今日动态总数较昨日增幅
}
