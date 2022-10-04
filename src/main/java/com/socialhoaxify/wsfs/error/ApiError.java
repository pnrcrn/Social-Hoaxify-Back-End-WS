package com.socialhoaxify.wsfs.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.socialhoaxify.wsfs.sharedGenericResponse.Views;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
//burada geriye dönen validation için eğer null dönüşümüz varsa gösterme ,yoksa responseun body oluştur demek
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    @JsonView(Views.Base.class)//response body for loginPage
    private int status;

    @JsonView(Views.Base.class)
    private String message;

    @JsonView(Views.Base.class)
    private String path;

    @JsonView(Views.Base.class)
    private long timestamp=new Date().getTime();

    private Map<String, String> validationErrors;
    public ApiError(int status,String message,String path){
        this.message=message;
        this.path=path;
        this.status=status;
    }
}
