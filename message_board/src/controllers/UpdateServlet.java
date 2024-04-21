package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Message;
import utils.DBUtil;
import models.validators.MessageValidator;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet(description = "UpdateServlet", urlPatterns = { "/update" })
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            var em = DBUtil.createEntityManager();

            // �Z�b�V�����X�R�[�v���烁�b�Z�[�W��ID���擾����
            // �Y����ID�̃��b�Z�[�W1���݂̂��f�[�^�x�[�X����擾
            var m = em.find(Message.class, (Integer) (request.getSession().getAttribute("message_id")));

            // �t�H�[���̓��e���e�t�B�[���h�ɏ㏑��
            var title = request.getParameter("title");
            m.setTitle(title);

            var content = request.getParameter("content");
            m.setContent(content);

            var currentTime = new Timestamp(System.currentTimeMillis());
            m.setUpdated_at(currentTime); // �X�V�����̂ݏ㏑��
            
            List<String> errors = MessageValidator.validate(m);
            if(errors.size() > 0) {
                em.close();

                // �t�H�[���ɏ����l��ݒ�A����ɃG���[���b�Z�[�W�𑗂�
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("message", m);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/edit.jsp");
                rd.forward(request, response);
            } else {
            	// �f�[�^�x�[�X���X�V
                em.getTransaction().begin();
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "�X�V���������܂����B");  
                em.close();

                // �Z�b�V�����X�R�[�v��̕s�v�ɂȂ����f�[�^���폜
                request.getSession().removeAttribute("message_id");

                // index�y�[�W�փ��_�C���N�g
                response.sendRedirect(request.getContextPath() + "/index");
            }            
        }
    }

}
