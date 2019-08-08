package com.liba.controller.command;

import javax.servlet.http.HttpServletRequest;

public class IndexCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        return "/index.jsp";
    }
}
