package com.bjqg.web.utils.common;

import com.bjqg.web.utils.common.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author: lbj
 * @date: 2022/12/7 9:39
 */
public class MessageUtils {

    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
