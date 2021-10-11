<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<link href="<%= request.getContextPath() %>/resources/css/review/reviewStyle.css" rel="stylesheet">
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
}
</style>
<title>리뷰 작성</title>
</head>
<body>
<c:set var="contextPath" value="${ pageContext.servletContext.contextPath }" scope="application"/>
	<div class="wrap">
		<header id="header_review">
			<h3>상품 리뷰 작성하기</h3>
		</header>
		<h3>상품을 사용해 보셨나요?</h3>
		<form name="reviewform" class="reviewform" method="post" enctype="multipart/form-data"
		action="${ contextPath }/review/insert">
		<!-- 별점 코드 -->
		<div class="star-box">
			<span class="star star_left">
				<input type="hidden" name="star" value="0.5" />
			</span> 
			<span class="star star_right">
				<input type="hidden" name="star" value="1" />
			</span> 
			<span class="star star_left" value="1.5">
				<span style="display:none;">1.5</span>  
			</span> 
			<span class="star star_right" data-value="2"></span>
			<span class="star star_left" value="2.5"></span> 
			<span class="star star_right" value="3"></span>
			<span class="star star_left" value="3.5"></span> 
			<span class="star star_right" value="4"></span>
			<span class="star star_left" value="4.5"></span> 
			<span class="star star_right" value="5"></span>
			
			<input name= "point" type="hidden" id="sum">
			<input name= "pNo" type="hidden" value="${ param.pno }">
			<input name= "orderNo" type="hidden" value="${ param.ono }">
		</div>
		<script>
              $(".star").on('click',function(){
            	  var result = 1;
	                var idx = $(this).index();
	                $(".star").removeClass("on");
	                  for(var i=0; i<=idx; i++){
	                      $(".star").eq(i).addClass("on");
	                      result = i;
	               }
	                  console.log(result+1);
	                  document.getElementById('sum').value = (result+1)/2;
              });
        </script>
		
			<input type="hidden" name="rate" id="rate" value="0" />
			<p class="sub_title">
				어떤 점이 좋았는지 다른 사용자를 위해 후기를 남겨주세요 <br> (이미지는 1장만 첨부 가능합니다.)
			</p>
			<div class="review_contents">
				<div class="warning_msg">10자 이상의 리뷰 내용을 작성해 주세요.</div>
				<textarea rows="10" class="review_textarea" name="content"
					placeholder="10자 이상 500자 이하로 작성해주세요"></textarea>
			</div>
			<!--글자수 세기-->
			<div>
				<p>
					<span id="counter">0</span>/500
				</p>
			</div>

			<script>
                $(document).ready(function(){
                    $("textarea").keyup(function(){
                        let inputLength = $(this).val().length;
                        $("#counter").text(inputLength);
                        let remain = 500 - inputLength;
                        if(remain >= 0)
                            $("#counter").css("color","black");
                        else    
                            alert("글자수초과");
                    });
                });
            </script>

			<!-- 이미지 첨부 -->
			<div class="file_area">
			<input type="file" name="review_img" id="imgfile" accept="image/gif,image/jpeg,image/png">
			</div>
			
			<!-- 이미지 담는 영역 -->
			<div id="imgArea">
				<img src="">
			</div>
			<script>
                document.getElementById('imgfile').addEventListener('change', function(){
                    if(this.files && this.files[0]){
                        let reader = new FileReader();
                        reader.readAsDataURL(this.files[0]);
                        reader.onload = function(){
                            console.log(reader.result);
                            document.getElementById('imgArea').innerHTML = "<img src='" + reader.result + "' width='360px' height='360px'>";
                        }
                    }
                });
            </script>

			<div class="cmd">
				<input type="submit" name="save" id="save" value="등록">
			</div>

			<script>
                document.querySelector('#save').addEventListener('click', function(e){
                        //리뷰 10자 미만이면 메시지 표시
                        if(document.querySelector('.review_textarea').value.length < 10){
                            alert("내용은 10자 이상 500자 미만으로 채워주세요");
                        }
                    });
            </script>

			<script>
				
			</script>
		</form>
	</div>
</body>
</html>