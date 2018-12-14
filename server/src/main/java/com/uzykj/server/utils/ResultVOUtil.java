package com.uzykj.server.utils;

import com.uzykj.server.pojo.vo.ResultVO;

/**
 * 返回结果集辅助类
 * @Author: ghostxbh
 * @Date: 14/12/2018 19:34
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        return new ResultVO(0, "成功", object);
    }

    public static ResultVO fail() {
        return new ResultVO(1, "失败", null);
    }
}
