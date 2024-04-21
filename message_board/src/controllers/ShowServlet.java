package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet(description = "ShowServlet", urlPatterns = { "/show" })
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var em = DBUtil.createEntityManager();

        // �Y����ID�̃��b�Z�[�W1���݂̂��f�[�^�x�[�X����擾
        var m = em.find(Message.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        // ���b�Z�[�W�f�[�^�����N�G�X�g�X�R�[�v�ɃZ�b�g����show.jsp���Ăяo��
        request.setAttribute("message", m);

        var rd = request.getRequestDispatcher("/WEB-INF/views/messages/show.jsp");
        rd.forward(request, response);
    }

}
