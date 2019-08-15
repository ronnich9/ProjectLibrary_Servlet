package com.liba.utils;


import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class SessionUtils {

    public static boolean checkUserIsLogged(HttpServletRequest request, String username){
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if(loggedUsers.stream().anyMatch(username::equals)){
            return true;
        }
        loggedUsers.add(username);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }

}
