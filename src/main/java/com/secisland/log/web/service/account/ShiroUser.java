/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.account;

import com.google.common.base.Objects;
import java.io.Serializable;

public class ShiroUser implements Serializable {
    private static final long serialVersionUID = -1373760761780840081L;
    public Long id;
    public String loginName;
    public String name;

    public ShiroUser(Long id, String loginName, String name) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return loginName;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(loginName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ShiroUser other = (ShiroUser) obj;
        if (loginName == null) {
            if (other.loginName != null) {
                return false;
            }
        } else if (!loginName.equals(other.loginName)) {
            return false;
        }
        return true;
    }
}
