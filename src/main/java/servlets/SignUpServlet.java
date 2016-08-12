package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Элеонора on 31.07.2016.
 */
public class SignUpServlet extends HttpServlet {

    private final AccountService accountService;

    public SignUpServlet(AccountService accountService){
        this.accountService = accountService;
    }
    // add new user
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserProfile user = new UserProfile(login);
        accountService.addNewUser(user);

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
