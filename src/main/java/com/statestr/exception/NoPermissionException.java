package com.statestr.exception;

import javax.persistence.NamedQuery;

/**
 * Created by ruantianbo on 2017/4/16.
 */
public class NoPermissionException extends RuntimeException {
    public NoPermissionException(){
        super("No Authority,You need login First");
    }
}
