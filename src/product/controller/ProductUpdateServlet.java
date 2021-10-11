package product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import product.model.service.ProductService;
import product.model.vo.Option;
import product.model.vo.OptionType;
import product.model.vo.Product;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/product/update")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateServlet() {
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
		// enctype이 multipart/form-data로 전송 되었는지 확인
	      if(!ServletFileUpload.isMultipartContent(request)) {
	         request.setAttribute("msg", "잘못 된 전송입니다");
	         request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
	         return;
	      }
	      
	      // cos.jar 라이브러리 추가

	      // 1. 전송 파일 용량 제한 : 10Mbyte 제한
	      int maxSize = 1024*1024*10;
	      
	      // 2. 웹  서버 컨테이너 경로 추출
	      String root = request.getSession().getServletContext().getRealPath("/");
	      // System.out.println(root);
	      
	      // 3. 파일 실제 저장 경로 
	      String savePath = root + "resources\\uploadFiles\\product\\";
	      
	      // HttpServletRequest => MultipartRequest 변경
	      MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());

	      // -----------------------------------------------------------------------------------------------------------------

	      Enumeration files = multi.getFileNames(); 
	      String fname1 = (String) files.nextElement(); 
	      String fname2 = (String) files.nextElement();
	      String pImg = multi.getFilesystemName(fname2);
		  String pDetail = multi.getFilesystemName(fname1);
		  
		  int pNo = Integer.parseInt(multi.getParameter("pNo"));
	      int cNo = Integer.parseInt(multi.getParameter("category"));
	      String pName = multi.getParameter("title");
	      int pPrice = Integer.parseInt(multi.getParameter("price"));
	      
	      // 기존 옵션 삭제
	      String[] deleteOtarr = multi.getParameterValues("deleteOtNo");
	      String[] opType = multi.getParameterValues("opType");
	      String[] arr = multi.getParameterValues("opPrice");		// Values는 String 타입으로만 받아올 수 있으므로 받아와서 형변환 처리
	      List<OptionType> optionTypeList = new ArrayList<>();
	      
	      int[] opPrice = null;
	      if(arr != null) {
	    	  opPrice = new int[arr.length];
	    	  for(int i = 0; i < opPrice.length; i++) {
	    		  opPrice[i] = Integer.parseInt(arr[i]);
	    	  }
	      }
	      
	      String optionType = "";
	      int count = 0;
	      int index = 0;
	      if(opType != null) {
	    	  for(int i = 0; i < opType.length; i++) {
	    		  if(!optionType.equals(opType[i])){
	    			  OptionType ot = new OptionType();
		    		  ot.setOptionType(opType[i]);
		    		  
		    		  String[] opName = multi.getParameterValues("opName" + index++);
		    		  System.out.println(Arrays.toString(opName));
		    	      if(opName != null && opPrice != null) {
		    	    	  List<Option> optionList = new ArrayList<>();
		    	    	  for(int j = 0; j < opName.length; j++) {
		    	    		  	Option o = new Option();
		    		    		o.setOptionName(opName[j]);
		    		    		o.setOptionPrice(opPrice[count++]);
		    		    		optionList.add(o);
		    		    		ot.setOptionList(optionList);
		    		    	} 
		    	      }
		    	      optionTypeList.add(ot);
		    	      optionType = opType[i];
	    		  }
	    	  }
	      }
	      
	      Product p = new Product(pNo, cNo, pName, pImg, pPrice, optionTypeList, pDetail);
	      
	      int result = new ProductService().updateProduct(p, deleteOtarr);
	      System.out.println("result : " + result);
	      System.out.println(p);
	      
	      if(result > 0) {
	    	  response.sendRedirect(request.getContextPath() + "/product/listView");
	      } else {
	    	  request.setAttribute("msg", "상품 수정에 실패하였습니다.");
	    	  request.getRequestDispatcher("WEB/INF/views/common/errorpage.jsp").forward(request, response);
	      }
	}

}
