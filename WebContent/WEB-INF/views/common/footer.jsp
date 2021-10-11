<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>footer</title>

    <!-- 외부 스타일 시트 -->
    <link href="<%= request.getContextPath() %>/resources/css/common/footerStyle.css"  rel="stylesheet">
    <!-- 구글 웹 폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
    <footer>
        <div class="todaymealLogo">
            <img src="<%= request.getContextPath() %>/resources/images/common/footer/logo.png">
        </div>
        <div class="footerWrapper1">
                <sapn id="companyName">오늘의 밀<br><br></sapn>
                <span id="companyInfo">주소 : 서울특별시 금천구 가산디지털1로(가산동) 219, 12층<br>
                대표 : 김형민, 노승희, 유혜원, 장영재, 황세빈, 황재윤<br>
                팩스 : 070 - 0000 - 0000  이메일 : khkh@todaymeal.com
                </span>
        </div>
        <div class="footerWrapper2">
            <span id="etc">이용 약관 | 개인정보처리방침<br><br></span>
            <span id="customercenter">CUSTOMER CENTER<br><br></span>
            <sapn id="customercenterInfo">070 - 1234 - 5678<br>
            MON - FRI 09:00 ~ 18:00<br>SAT, SUN, HOLIDAY OFF</sapn>
        </div>
    </footer>
</body>
</html>