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
public class SignInServlet extends HttpServlet {
    private AccountService accountService;

    public SignInServlet(AccountService accountService){
        this.accountService = accountService;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserProfile user = accountService.getUserByLogin(login);

        if(user == null){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Unauthorized");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.addSession(req.getSession().getId(),user);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Authorized: " + user.getLogin());
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
