package com.den.service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Denis on 30.01.2015.
 */

public class RoleService {

    public static final String ROLE_USER = "ROLE_USER";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    public static final Set<String> ROLE_USER_SET = new HashSet<String>(){{
        add(ROLE_USER);
    }};

    public static final Set<String> ROLE_ADMIN_SET = new HashSet<String>(){{
        add(ROLE_ADMIN);
    }};

    public static final Set<String> ROLE_ANONYMOUS_SET = new HashSet<String>(){{
        add(ROLE_ANONYMOUS);
    }};

    public static  Set<String> ALL_RIGHTS_SET = new HashSet<>(2);

    static {
        ALL_RIGHTS_SET.addAll(ROLE_ADMIN_SET);
        ALL_RIGHTS_SET.addAll(ROLE_USER_SET);
    }


}
