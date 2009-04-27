package de.escidoc.core.resources.aa.useraccount;

import java.util.Collection;
import java.util.LinkedList;

public class UserAccounts {
public UserAccounts() {
        
    }
    private final Collection<UserAccount> userAccounts = new LinkedList<UserAccount>();
    public Collection<UserAccount> getUserAccounts() {
        return this.userAccounts;
    }
}
