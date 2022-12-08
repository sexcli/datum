package com.bjqg.web.core;

import com.bjqg.web.core.common.HttpStatus;
import com.bjqg.web.utils.common.StringUtils;

import java.util.HashMap;

/**
 * 操作消息提醒
 * @author: lbj
 * @date: 2022/12/8 10:13
 */
public class AjaxResult extends HashMap<String,Object> {

    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";

    public AjaxResult(){}

    /**
     *
     * @param: code 状态码
     * @param: msg 返回内容
     * @return : {@link null}
     * @author : lbj
     * @description: <初始化新建的AjaxResult对象>
     * @date : 2022/12/8 10:36
     */
    public AjaxResult(int code,String msg){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
    }

    /**
     *
     * @param: code 状态码
     * @param: msg 返回内容
     * @param: data 数据对象
     * @return : {@link null}
     * @author : lbj
     * @description: <初始化新建的AjaxResult对象>
     * @date : 2022/12/8 10:44
     */
    public AjaxResult(int code,String msg,Object data){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
        if (!StringUtils.isNotNull(data)){
            super.put(DATA_TAG,data);
        }
    }

    public static AjaxResult success(){
        return AjaxResult.success("操作成功");
    }

    public static AjaxResult success(Object data)
    {
        return AjaxResult.success("操作成功", data);
    }

    public static AjaxResult success(String msg)
    {
        return AjaxResult.success(msg, null);
    }

    public static AjaxResult success(String msg, Object data)
    {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    public static AjaxResult error()
    {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String msg, Object data)
    {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(int code, String msg)
    {
        return new AjaxResult(code, msg, null);
    }

    /**
     * 方便链式调用
     *
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public AjaxResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
