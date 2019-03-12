/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 */
package com.secisland.core.exception;

import org.apache.shiro.authc.DisabledAccountException;

public class NewAccountException extends DisabledAccountException {
	private static final long serialVersionUID = -4899691277670655785L;

    public NewAccountException() {
        super();
    }

    public NewAccountException(String message) {
        super(message);
    }

    public NewAccountException(Throwable cause) {
        super(cause);
    }

    public NewAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}