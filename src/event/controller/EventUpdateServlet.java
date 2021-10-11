package event.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import event.model.service.EventService;
import event.model.vo.Event;

/**
 * Servlet implementation class EventUpdateServlet
 */
@WebServlet("/event/update")
public class EventUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못 된 전송입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			return;
		}
		
		int maxSize = 1024*1024*10;
		
		String root = request.getSession().getServletContext().getRealPath("/");
		//System.out.println(root);
		
		// 3. 파일 실제 저장 경로
		String savePath = root + "resources\\uploadFiles\\event\\";
		
		// HttpServletReqeust => MultipartRequest로 변경
		MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		//MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		Enumeration files = multi.getFileNames();
		String fname = (String) files.nextElement();
		String img = multi.getFilesystemName(fname);
		
		int eno = Integer.parseInt(multi.getParameter("eno"));
		String title = multi.getParameter("title");
		String term = multi.getParameter("term");
		
		//String writer = request.getSession().getId();
		
		//Review r = new Review(point, content, fileName, writer);
		Event e = new Event(eno, title, term , img);
		
		int result = new EventService().udpateEvent(e);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "이벤트가 성공적으로 등록되었습니다.");
			response.sendRedirect(request.getContextPath() + "/admin/eventlist");
		} else {
			request.setAttribute("msg", "이벤트 글 등록에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
		
		//request.getRequestDispatcher("/WEB-INF/views/event/eventUpdateView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
