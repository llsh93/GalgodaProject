<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Galgoda</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<style>

</style>
<script>

<!-- 호텔명, 태그명 버튼 각각 클릭 시 활성화 -->
    
	    $(document).ready(function() {
	        // 호텔명 버튼이나 태그명 버튼이 클릭되었을 때의 이벤트 처리
	        $(".hotelName_btn, .tagName_btn").click(function() {
	            // 클릭된 버튼에만 'active' 클래스를 추가하고 다른 버튼에서는 'active' 클래스를 제거
	            $(this).addClass("active").siblings().removeClass("active");
	            
	         	// 태그명 버튼이 선택되었을 때 체크박스를 보이도록 처리
	            if ($(this).hasClass("tagName_btn")) {
	                $("#tagCheckboxes").show();
	            } else {
	                $("#tagCheckboxes").hide();
	            }
	        });
	    });

	    
	    $(document).ready(function() {
	        // 호텔명, 태그명 버튼 각각 클릭 시 활성화
	        $(".hotelName_btn, .tagName_btn").click(function() {
	            // 클릭된 버튼에만 'active' 클래스를 추가하고 다른 버튼에서는 'active' 클래스를 제거
	            $(this).addClass("active").siblings().removeClass("active");
	            
	            // 호텔명 버튼이 선택되었을 때 입력칸을 보이고, 태그 선택 창을 숨깁니다.
	            if ($(this).hasClass("hotelName_btn")) {
	                $("#hotelNameInputWrapper").show();
	                $("#tagCheckboxes").hide();
	            } 
	            // 태그명 버튼이 선택되었을 때 태그 선택 창을 보이고, 입력칸을 숨깁니다.
	            else {
	                $("#hotelNameInputWrapper").hide();
	                $("#tagCheckboxes").show();
	            }
	        });
	    });
	    
	  
	    
</script>

<style>
	
</style>
</head>
<body>
	<div class="wrap">
		<%@ include file="/views/common/header.jsp" %>
		
		
		<section class="mainpage_content">
            <div class="main_image"><img src="<%=contextPath%>/resources/images/mainPageImg.jpg" width="100%" alt="메인홈페이지 이미지"></div>
            <div class="main_top">
                <form id="searchForm" action="" method="get">
                    <table class="main_searchBar">
                        <thead>
                            <tr>
                                <td width="300px">
                                	<input type="hidden" id="searchType" name="searchType"> <!-- 숨겨진 입력 필드로 검색 타입을 저장 -->
                                	<div class="d-flex search_btn">
                                            <button type="button" class="btn btn-outline-primary flex-fill mr-1 hotelName_btn" onclick="setSearchType('hotelName')">호텔명</button>
                                            <button type="button" class="btn btn-outline-primary flex-fill mr-1 tagName_btn" onclick="setSearchType('tagName')">태그명</button>
                                    </div>
                                </td>
                                <td width="150px" style="text-align: center;">체크인</td>
                                <td width="150px" style="text-align: center;">체크아웃</td>
                                <td width="100px"></td>
                                <td width="110px" align="center">인원수</td>
                                <td width="110px" align="center">객실</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr> 
                                <td>
								    <div class="position-relative">
								        <div id="hotelNameInputWrapper" style="display: block;">
								            <div class="iconInput">
								                <i class="bi bi-search"></i>
								                <input type="text" name="hotelName" id="hotelNameInput" class="form-control" style="text-align: center;" onclick="toggleCheckboxes()">
								            </div>
								        </div>
								        
								        <div id="tagCheckboxes" class="position-absolute" style="display: none; top: -40px; left: 0; background-color: white; border: 1px solid #ced4da; border-radius: 0.25rem; padding: 5px;">
								            <label><input type="checkbox" name="tagCheckbox" value="1"> 관광</label>
								            <label><input type="checkbox" name="tagCheckbox" value="2"> 휴양</label>
								            <label><input type="checkbox" name="tagCheckbox" value="3"> 바다</label>
								            <label><input type="checkbox" name="tagCheckbox" value="4"> 가족여행</label>
								            <label><input type="checkbox" name="tagCheckbox" value="5"> 제주</label>
								            <label><input type="checkbox" name="tagCheckbox" value="6"> 동부</label>
								        </div>
								    </div>
								</td>
                                
                                <td><input type="date" name="checkInDate" class="form-control"></td>
                                <td><input type="date" name="checkInDate" class="form-control"></td>
                                <td></td>
                                <td><input type="number" min="0" name="peopleCount" class="form-control"></td>
                                <td><input type="number" min="0" name="roomCount" class="form-control"></td>
                                <td colspan="2" align="right">
					                <button type="submit" class="btn main_searchButton">검색하기</button>
					            </td>
                            </tr>
                            <tr>
                                <td colspan="7" style="display: none;">
					                <div class="iconInput">
					                    <i class="bi bi-tags"></i>
					                    <input type="text" class="form-control" style="text-align: center;">
					                </div>
		           				</td>
                                
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            
            <div class="main_bottom">
                <div class="main">
                    <h2 class="mainpage_h2" style="margin-left: 200px;">인기 호텔</h2>
                    <div class="mainpage_imgContent" >
                        <div class="mainpage_hotelThumbnail">
                            <img class="mainpage_hotelImg" src="" alt="">
                        </div>
                        <div class="mainpage_hotelThumbnail">
                            <img class="mainpage_hotelImg" src="" alt="">
                        </div>
                        <div class="mainpage_hotelThumbnail">
                            <img class="mainpage_hotelImg" src="" alt="">
                        </div>
                        
                    </div>
                </div>
                
            </div>
        </section>
	
	
		<%@ include file="/views/common/footer.jsp" %>
	</div>
</body>
</html>