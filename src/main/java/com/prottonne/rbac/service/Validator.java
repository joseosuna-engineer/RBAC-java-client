package com.prottonne.rbac.service;

import com.prottonne.rbac.dto.Permission;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Validator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String BACKEND_TYPE = "back";

    @Autowired
    private Finder finder;

    public Validator() {
        super();
    }

    public Boolean check(Integer role, String url) {

        List<Permission> permissions = finder.findByRole(role);

        for (Permission permission : permissions) {

            if (BACKEND_TYPE.equals(permission.getType())) {

                Pattern pattern = Pattern.compile(permission.getObject().trim());
                Matcher matcher = pattern.matcher(url.trim());

                if (matcher.matches()) {

                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

}
