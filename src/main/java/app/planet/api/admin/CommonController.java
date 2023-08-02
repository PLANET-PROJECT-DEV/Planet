package app.planet.api.admin;

import app.planet.application.result.upload.UploadResult;
import app.planet.core.constant.UploadConstant;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import app.planet.utils.AliOssUtil;

/**
 * 通用接口
 */
@RestController
@Slf4j
@RequestMapping("/admin/common")
public class CommonController {
    @Resource
    private AliOssUtil aliOssUtil;

    /**
     * alioss文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public UploadResult upload(MultipartFile file){
        log.info("上传的文件:{}",file);
        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            String objectName = UUID.randomUUID().toString() + extension;
            //文件请求路径
            String filePath = aliOssUtil.upload(file.getBytes(),objectName);
            return new UploadResult(UploadConstant.SUCCESS,filePath,UploadConstant.UPLOAD_SUCCESS);
        } catch (IOException e) {
            log.error("文件上传失败:{}",file);
            e.printStackTrace();

        }
        return new UploadResult(UploadConstant.FAIL,"",UploadConstant.UPLOAD_FAIL);
    }
}
