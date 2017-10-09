package com.wb4.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Commands {
    String execute(HttpServletRequest request,
                   HttpServletResponse response) throws ServletException, IOException;

}
