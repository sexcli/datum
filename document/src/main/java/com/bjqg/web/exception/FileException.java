package com.bjqg.web.exception;

import com.bjqg.web.exception.BaseException;

/**
 * @author: lbj
 * @date: 2022/12/7 9:53
 */
public class FileException extends BaseException {

    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }
}
