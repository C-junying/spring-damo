package jmu.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<E> implements Serializable {
    /**
     * 状态码
     */
    private Integer state;
    /**
     * 描述信息
     */
    private String message;
    /**
     * 数据
     */
    private E data;
    public JsonResult() {
        super();
    }

    public JsonResult(Integer state){
        super();
        this.state = state;
    }
    /** 出现异常时调用 */
    public JsonResult(Throwable e){
        super();
        this.message = e.getMessage();
    }
    public JsonResult(Integer state,E data){
        super();
        this.state = state;
        this.data = data;
    }
}
