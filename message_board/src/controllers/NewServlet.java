package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import javax.persistence.EntityManager;
import models.Message;
import utils.DBUtil;


/**
 * Servlet implementation class NewServlet
 */
@WebServlet(description = "NewServlet", urlPatterns = { "/new" })
public class NewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        // Message�̃C���X�^���X�𐶐�
        Message m = new Message();

        // m�̊e�t�B�[���h�Ƀf�[�^����
        String title = "taro";
        m.setTitle(title);

        String content = "hello";
        m.setContent(content);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis()); // ���݂̓������擾
        m.setCreated_at(currentTime);
        m.setUpdated_at(currentTime);

        // �f�[�^�x�[�X�ɕۑ�
        em.persist(m);
        em.getTransaction().commit();

        // �����̔Ԃ��ꂽID�̒l��\��
        response.getWriter().append(Integer.valueOf(m.getId()).toString());

        em.close();
    }

}
