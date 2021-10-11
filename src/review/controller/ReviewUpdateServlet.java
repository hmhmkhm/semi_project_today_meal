package review.controller;

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

import review.model.service.ReviewService;
import review.model.vo.Review;

/**
 * Servlet implementation class ReviewUpdateServlet
 */
@WebServlet("/review/update")
public class ReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못 된 전송입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			return;
		}
		
		int maxSize = 1024*1024*10;
		
		String root = request.getSession().getServletContext().getRealPath("/");
		
		
		// 3. 파일 실제 저장 경로
		String savePath = root + "resources\\uploadFiles\\review\\";
		
		// HttpServletReqeust => MultipartRequest로 변경
		MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		//MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		Enumeration files = multi.getFileNames();
		String fname = (String) files.nextElement();
		String img = multi.getFilesystemName(fname);
		
		int rno = Integer.parseInt(multi.getParameter("rno"));
		//String img = multi.getParameter("review_img");
		String content = multi.getParameter("content");
		// 로그인한 사용자 아이디 추가하기
		//String writer = request.getSession().getId();
		
		//Review r = new Review(point, content, fileName, writer);
		Review r = new Review(rno, content, img);
		
		int result = new ReviewService().udpateReview(r);
		
		if(result > 0) {
			// 등록을 마친 뒤 (마이페이지 화면)을 응답하고자 할때
			// 새로 갱신 된 목록을 불러오는 요청을 해야하므로 다시 마이페이지 재호출
			//request.getSession().setAttribute("msg", "리뷰가 성공적으로 등록되었습니다.");
			response.sendRedirect(request.getContextPath() + "/mypage/review");
		} else {
			request.setAttribute("msg", "리뷰 등록에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
