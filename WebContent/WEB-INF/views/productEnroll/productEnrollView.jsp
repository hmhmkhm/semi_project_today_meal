<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<link href="<%= request.getContextPath() %>/resources/css/productEnroll/productEnrollView.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" 
integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<jsp:include page="/WEB-INF/views/common/top.jsp" />
	<div id="container">
		<nav class="snb">
			<jsp:include page="/WEB-INF/views/common/adminPage/subMenu.jsp"/>
		</nav>
		<div class="contents">
			<form method="post" action="${ contextPath }/product/enroll"
			 enctype="multipart/form-data">
			<div class="inner">
				<div class="product_title">
			 		상품 등록
			 	</div>
			 	<div class="subject_category">
				 	<div class="category_div">카테고리<b>*</b></div>
				 	<select name="category" id="category">
 						<option selected="selected">- 선택 -</option>
 						<option value="1">한식</option>
 						<option value="2">양식</option>
 						<option value="3">중식</option>
 						<option value="4">일식</option>
 						<option value="5">분식</option>
 						<option value="6">야식</option>
 						<option value="7">샐러드</option>
 					</select>			
 				</div>
 				<div class="subject_title">
				 	<div class="title_div">상품명<b>*</b></div>
				 	<input type="text" class="title" name="title" required>
				</div>
				<div class="subject_thumbnail">
					<div class="thumbnail_div">대표 이미지<b>*</b></div>
					<input type="file" id="thumbnail" name="thumbnail" accept="image/gif,image/jpeg,image/png">
				</div>
				<div id="product_thumbnail" style="display:none;">
					<!-- 대표이미지 담는 div -->
				</div>
				<div class="subject_price">
				 	<div class="price_div">금액<b>*</b></div>
				 	<input type="text" name="price" required>&nbsp;원
				</div>
				<div class="option_cnt">
					<div class="cnt_div">옵션명 개수</div>
					<input type="number" class="cnt" id="cnt" name="count" min="0" max="5">
				</div>
				<div class="option_input">
					<div class="input_div">옵션 입력</div>
						<table>
							<thead>
								<th>옵션명</th>
								<th>&nbsp;옵션값</th>
							</thead>
							<tbody id="op_list">
								<!-- 옵션 입력 list 행 생성 -->		
							</tbody>
						</table>											
				</div>
				<button type="button" id="op_btn" onclick="listApply()">옵션 목록으로 적용</button>
				
				<div class="option_list">
					<div class="list_div">옵션 목록</div>
					<div class="op_list">
						<button type="button" id="chdel_btn" onclick="checkDelete()">선택 삭제</button>
						<table class="option_table">
							<thead>
								<th class="option_th"><input type="checkbox" id="check_list" onclick="selectAll()"></th>
								<th class="option_th">옵션명</th>
								<th class="option_th">옵션값</th>
								<th class="option_th">옵션가(원)</th>
								<th class="option_th">관리</th>
							</thead>
							<tbody id="op_apply">
								<!-- 옵션 목록 -->
							</tbody>
						</table>
					</div>
				</div>
				<div class="subject_content">	
				 	<div class="content_div">상세 설명<b>*</b></div>
				 	<input type="file" id="productImg" name="productImg">
			 	</div>
				<div id="product_img">
                	<!-- 이미지 담는 div-->
              	</div>
			 	<div class="btn_area">
					<button type="submit" id="btn1">등록</button>
					<button type="button" id="btn2">취소</button>
				</div>
			</div><!-- inner -->
			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
	<script>
		// 상품 대표이미지 담는 div
		document.getElementById('thumbnail').addEventListener('change', function(){
			if(this.files && this.files[0]){
				
				let reader = new FileReader();
				reader.readAsDataURL(this.files[0]);
				reader.onload = function(){
					document.all.product_thumbnail.style.display = "";
					document.getElementById('product_thumbnail').innerHTML = "<img src='" + reader.result + "' id='pro_thumbnail'>";
				}
			}
		})
		
		// 상품상세페이지 이미지 적용
        document.getElementById('productImg').addEventListener('change', function(){
            if(this.files && this.files[0]){
                let reader = new FileReader();
                reader.readAsDataURL(this.files[0]);
                reader.onload = function(){
                    console.log(reader.result);
                    document.getElementById('product_img').innerHTML = "<img src='" + reader.result + "' id='pro_img'>";
                }
            }
        });
        
		// 옵션명 개수만큼 테이블 행 추가
        var cnt =  document.getElementById('cnt');
       	cnt.addEventListener('change', function(){   			
       		var table = document.getElementById("op_list");
       		var c = cnt.value;
       		var elem = '';
       		
			for(let i = 0; i < c; i++){
				elem = elem + "<tr><td><input type='text' class='op_type' id='op_type"
							+ "' name='op_type'></td><td><input type='text' class='op_name' id='op_name'"
							+ "class='op_name' name='op_name'></td></tr>";
			}	
			table.innerHTML = elem;
       	})
       	
       	// '옵션 목록으로 적용' 버튼 클릭 시 listApply() 실행
       	function listApply(){
       		var table = document.getElementById("op_apply");
       		var nameArr = [];
			
       		let op_type = document.getElementsByName("op_type");	// 옵션명 inputbox
       		let op_name = document.getElementsByName("op_name");	// 옵션값 inputbox
       	
       		var elem = '';
       		for(let i = 0; i < op_type.length; i++){
       			var nameArr = op_name[i].value.split(",");
       			for(let j = 0; j < nameArr.length; j++){
       				elem = elem + "<tr><td class='option_td'><input type='checkbox' name='chk'></td>" 
       							+ "<td class='option_td opType' name='opType'>" + op_type[i].value + "</td>"
       							+ "<input type='hidden' name='opType' value='" + op_type[i].value + "'>"
       							+ "<td class='option_td opName' name='opName'>" + nameArr[j] + "</td>"
       							+ "<input type='hidden' name='opName"+ i +"' value='" + nameArr[j] + "'>"
       							+ "<td class='option_td'><input type='text' id='op_price' name='opPrice'></td>" 
       							+ "<td class='option_td'><button type='button' id='del_btn' onclick='rowDelete(this)'>삭제</button></td></tr>";
       			}
       			table.innerHTML = elem;
       		}       		
       	}
       	
       	// 옵션 삭제
       	function rowDelete(obj){
       		$(obj).parent().parent().remove();
       	}
       	
       	// 옵션 체크박스 전체 선택/해제
       	function selectAll(){
       		if($("#check_list").is(":checked")){
       			$("input[type='checkbox']").prop("checked", true);
       		} else {
       			$("input[type='checkbox']").prop("checked", false);
       		}		
       	}
       	
       	// 옵션 체크박스 선택 삭제
       	function checkDelete(){
       		let obj = $("input[name='chk']");
       		for(let i = 0; i < obj.length; i++){
       			if(obj.eq(i).is(":checked")){
       				obj.eq(i).parent().parent().remove();
       			}
       		}
       	}
       
    </script>
</body>
</html>