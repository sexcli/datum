package com.bjqg.web.exception;

/**
 * @author: lbj
 * @date: 2022/12/7 9:50
 */
public class FileNameLengthLimitExceededException extends FileException {

    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}
