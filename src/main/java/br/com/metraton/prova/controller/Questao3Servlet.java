package br.com.metraton.prova.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.metraton.prova.model.User;
import br.com.metraton.prova.service.UserService;
import br.com.metraton.prova.service.UserServiceImpl;

@WebServlet(urlPatterns = { "/questao3" })
public class Questao3Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	UserService userService;
	User user;

//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("user");
//	EntityManager em = emf.createEntityManager();

	public Questao3Servlet() {
	}

	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
		user = new User();
		user.setName("");
		user.setPhone("");
		user.setRole("");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
		String i = req.getParameter("i");
		String action = req.getParameter("action");
		// TODO implentar lógica de controle i!= null com exception
		if (action != null && !action.equals("")) {
			switch (action) {
			case "edit": {
				user = userService.getUser(Integer.parseInt(i));
				break;
			}
			case "remove": {
				userService.excluir(Integer.parseInt(i));
				user = new User();
				user.setName("");
				user.setPhone("");
				user.setRole("");
				break;
			}
			}
		}
		req.setAttribute("user", user);
		req.setAttribute("userList", userService.getAllUsers());
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
		// Integer ao invés de int por haver casos em que o valor recebido for NULO
		Integer indice = Integer.valueOf(req.getParameter("id"));
		String nome = req.getParameter("name");
		String cargo = req.getParameter("role");
		String phone = req.getParameter("phone");
		String actionSearch = req.getParameter("search");
		String actionAdd = req.getParameter("add");
		user.setName(nome);
		user.setPhone(phone);
		user.setRole(cargo);
		if (actionAdd != null && actionAdd.equals("Enviar")) {
			if (indice != null && indice > 0) {
				userService.edit(user, indice);
			} else {
				userService.add(user);
			}
		}
		if (actionSearch != null && actionSearch.equals("Enviar")) {
			req.setAttribute("userList", userService.getSpecificUsers(user));
		} else {
			req.setAttribute("userList", userService.getAllUsers());
		}
		user = new User();
		user.setName("");
		user.setPhone("");
		user.setRole("");

		req.setAttribute("user", user);
		requestDispatcher.forward(req, resp);

//		resp.setCharacterEncoding("UTF-8");
	}

}
