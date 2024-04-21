package controllers;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import models.Message;
import utils.DBUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// http://localhost:8080/message_board/index

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(description = "IndexServlet", urlPatterns = { "/index" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        
        // �J���y�[�W�����擾�i�f�t�H���g��1�y�[�W�ځj
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();
        
        // �S�������擾
        long messages_count = (long)em.createNamedQuery("getMessagesCount", Long.class)
                                      .getSingleResult();        
        em.close();

        request.setAttribute("messages", messages);
        request.setAttribute("messages_count", messages_count);     // �S����
        request.setAttribute("page", page);                         // �y�[�W��
        
        // �t���b�V�����b�Z�[�W���Z�b�V�����X�R�[�v�ɃZ�b�g����Ă�����
        // ���N�G�X�g�X�R�[�v�ɕۑ�����i�Z�b�V�����X�R�[�v����͍폜�j
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        
        var rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);
        
        
        
    }
}
