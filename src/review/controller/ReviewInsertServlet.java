package review.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.MyFileRenamePolicy;
import login.model.vo.Member;
import review.model.service.ReviewService;
import review.model.vo.Review;

/**
 * Servlet implementation class ReviewInsertServlet
 */
@WebServlet("/review/insert")
public class ReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/reviewInsert/ReviewInsert.jsp").forward(request, response);
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
		
		//String img = multi.getParameter("review_img");
		String content = multi.getParameter("content");
		// 로그인한 사용자 아이디 추가하기
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		double point = Double.parseDouble(multi.getParameter("point"));
		
		int pNo = Integer.parseInt(multi.getParameter("pNo"));
		int orderNo = Integer.parseInt(multi.getParameter("orderNo"));
		

		Review r = new Review(point, content, img, userNo, pNo, orderNo);
		
		System.out.println(r);
		
		int result = new ReviewService().insertReview(r);
		
		System.out.println(result);
		
		PrintWriter out = response.getWriter();
		
		
		
		
		if(result > 0) {
			// 등록을 마친 뒤 (마이페이지 화면)을 응답하고자 할때
			// 새로 갱신 된 목록을 불러오는 요청을 해야하므로 다시 마이페이지 재호출
			request.getSession().setAttribute("msg", "리뷰가 성공적으로 등록되었습니다.");
			String str="";
			   str = "<script language='javascript'>";
			   str += "opener.window.location.reload();";  //오프너 새로고침
			   str += "self.close();";   // 창닫기
			    str += "</script>";
			   out.print(str);
			//response.sendRedirect(request.getContextPath() + "/user/mypage");
		} else {
			request.setAttribute("msg", "리뷰 등록에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
