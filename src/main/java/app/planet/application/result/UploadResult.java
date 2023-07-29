package app.planet.application.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UploadResult {
    Integer code;
    String filePath;
    String msg;
    public UploadResult(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
