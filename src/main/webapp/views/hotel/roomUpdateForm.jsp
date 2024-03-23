<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.galgoda.hotel.model.vo.Option" %>
<%@ page import="com.galgoda.hotel.model.vo.Hotel" %>
<%@ page import="com.galgoda.hotel.model.vo.Room" %>

<%@ page import="java.util.List" %>  

<%
	
	
	List<Option> list = (List<Option>)request.getAttribute("list");
	List<Room> rlist = (List<Room>)request.getAttribute("rlist");


%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

        /* 메인컨텐츠  */
        #title1 {font-size: 30px; font-weight: bold;  color: rgb(115, 90, 75);}
        #title2 {font-size: 25px; font-weight: bold;  color: rgb(115, 90, 75);}
        #title3 {font-size: 20px; font-weight: bold; }
        #btnconfirm {background-color: #EBE7E3; font-size: 15px; border: 1px solid #D4D4D4 ; height: 40px; padding: 10px 20px; margin-right: 5px; }
        #btncollapse {background-color: white; font-size: 15px; border: none ; height: 40px; padding: 10px 20px; margin-right: 5px; }
        #btnname {background-color: #EBE7E3; font-size: 15px; border: 1px solid #D4D4D4 ; width: 85px; height: 40px; padding: 7px;  pointer-events : none;}
      
        #td1 {width:94px ; }
         #td2 {width:600px ; }
         #td3 {width:220px ; display: flex; align-items: center;}
         #td4 {display: inline-block;}
         
         .hotel_content td {padding-bottom: 7px;}
         .form-controller input {width: 100%;}
         .add_content { 
            display: inline-block;
            width: 100%;
            min-height: 150px;
            padding: 20px;
            height: 100%;
            border: 1px solid lightgray;
            }
        .add_content>input{ margin-right: 10px; }
        .hotel_content { width: 80%;}
        .page_content label { margin-left: 10px; margin-right: 15px; }
        #sminput {width: 220px;}
        #room_list {
            height: 70px;
            margin-bottom: 11px;
            display: flex;
            justify-content: space-between;
            align-items: center;
                    }
        .rdetail { 
            margin-bottom: 11px;
            height: 760px;
            padding-top: 10px;
            box-sizing: border-box;
            display: flex;
            align-items: center;
            justify-content: center;
			border: 1px solid lightgray;
			padding: 20px;
        }

</style>
</head>
<body>
 <div class="wrap">
 
         <%@ include file="/views/common/header.jsp"%>


        <section class="main_content">
            
            <!-- 사이드 메뉴바 영역 -->
            <div class="sideMenubar">
                <ul class="nav flex-column">
                    <li >
                        <h3 style="margin-left: 10px; margin-right: 30px; margin-top: 10px; border-bottom: 1px solid black;">마이페이지</h3>
                    </li>
                    
                    <li>
                        <a class="nav-link " style="color: white; background-color: rgb(115, 90, 75);" href="" data-toggle="collapse" data-target="#demo">호텔관리</a>
                    </li>
                    <li > <div id="demo" class="collapse show" style="margin-left: 30px;"><a href="">호텔등록</a></div> </li> 
                    <li > <div id="demo" class="collapse show" style="margin-left: 30px;"><a href="">호텔 정보 수정</a></div> </li>  
                    <li > <div id="demo" class="collapse show" style="margin-left: 30px;"><a href="">객실등록</a></div> </li>  
                    <li style="background-color: #BFB4B0;"> <div id="demo" class="collapse show" style="margin-left: 30px;"><a href="">객실수정</a></div> </li>  
                    <li> <div id="demo" class="collapse show" style="margin-left: 30px;"><a href="">리뷰 관리</a></div> </li>   
                    
                    
                    <li>
                        <a class="nav-link" style="color: black;" href="#" >예약관리</a>
                    </li>
           
                    <li>
                        <a class="nav-link" style="color: black;" href="#" data-toggle="collapse" data-target="#demo3">담당자 정보 수정</a>
                    </li>
                        
                    
                    <li>
                         <a class="nav-link" style="color: black;" href="#" data-toggle="collapse" data-target="#demo4">고객센터</a>
                    </li>
                    <li> <div id="demo4" class="collapse" style="margin-left: 30px;"><a href="">공지사항</a></div> </li>   
                    <li> <div id="demo4" class="collapse" style="margin-left: 30px;"><a href="">문의사항</a></div> </li>   
                </ul>
            </div>

           
            <!-- 사이드 메뉴바 영역  끝-->

            <!-- 메인 부분 -->
            <div class="page_content" style="margin-bottom: 150px;">

                <span id="title1">호텔관리</span> <span id="title2"> - 객실수정</span>
                <div style="border : 1px solid lightgray; margin-bottom: 16px;"></div>
                
                <div class="hotel_content" style="padding-left: 20px;">
                <span id="title3">객실목록</span>
                   
                   
                   <% for (int i=0; i<rlist.size(); i++){ %> 
                   <% Room r = rlist.get(i); %>
                    <div class="form-control" id="room_list">
                        객실 <%= i+1 %> -  <%= r.getRoomName() %> 
                            <span class="float-right" >
                                <button class="btn" id="btnconfirm" type="submit" form="roomForm" formaction="<%= contextPath %>/roomDelete.ho" onclick="deleteRoom();"> 객실 삭제하기</button>
                                <button class="btn" id="btncollapse" data-toggle="collapse" data-target="#room_detail<%= i+1 %>" onclick=checkOp();> 펼쳐 보기 <img src="resources/images/bottom.png" style="height: 25px;">  </button>
                            </span>
                    </div>
                    <div class="collapse rdetail " id="room_detail<%= i+1 %>">
                 		<form method="get"  enctype="multipart/form-data" id="roomForm">
                        
	                             <table class="table2">
	                        	 <tr>
	                                <td id="td1"><button class="btn  " id="btnname" type="button"  >객실명</button></td>
	                                <td id="td2"><input type="text" class="form-control" required  name="roomName" value="<%= r.getRoomName() %>"></td>
	                            </tr>
	                            </table>
	                            <table>
	                            <tr>
	                                <td id="td1"><button class="btn   " id="btnname" type="button" >객실크기</button></td>
	                                <td id="td3"><input type="text" class="form-control " id="sminput"  name="roomSize" required value="<%= r.getRoomSize() %>"> m<sup>2</sup></td> 
	                            </tr>
	                            <tr>
	                                <td id="td1"><button class="btn  " id="btnname" type="button" >투숙인원</button></td>
	                                <td id="td3"><input type="number" class="form-control "  id="sminput"    min="0" name="roPeople" value="<%= r.getRoPeople() %>" required> 명</td> 
	                            </tr>
	                            <tr>
	                                <td id="td1"><button class="btn " id="btnname" type="button" >욕실 수</button></td>
	                                <td id="td3"><input type="number" class="form-control "  id="sminput"    min="0" max="5" name="roBath"value="<%= r.getRoBath() %>" required></td> 
	                            </tr>
	                            <tr>
	                                <td id="td1"><button class="btn  " id="btnname" type="button" >가격</button></td>
	                                <td id="td3"><input type="text" class="form-control "  id="sminput"  name="roPrice" placeholder="숫자만 입력" required value="<%= r.getRoPrice() %>"> 원</td> 
	                            </tr>
	                            <tr>
	                                <td id="td1"><button class="btn  " id="btnname" type="button" >최대수량</button></td>
	                                <td id="td3"><input type="number" class="form-control "  id="sminput"    min="0" name="roCount" value="<%= r.getRoCount() %>" required> </td> 
	                            </tr>
	                            <tr>
	                                <td id="td4"><button class="btn  "  id="btnname"type="button" style="vertical-align: top; display:inline-block" >추가옵션</button></td>
	                                <td id="td2" >
	                                     <div class=" add_content">   
	                                     
	                                      <% for( Option o : list){ %>
	                                        <label><input type="checkbox" name="roomOption" value="<%= o.getOpNo()  %>"> &nbsp;<%= o.getOpName() %></label>
	                                        <% } %>
	             
	
	                                    </div>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td id="td1"><button class="btn   " id="btnname" type="button" >대표사진</button></td>
	                                <td id="td2"><input type="file" name="roomImgPath" class="form-control" required></td>
	                            </tr>
	                            <tr>
	                                <td id="td4"><button class="btn   "  id="btnname"type="button" >사진추가</button></td>
	                                <td id="td2">
	                                   
	                                    <input type="file" name="upload_file1" class="form-control">
	                                    <input type="file" name="upload_file2" class="form-control">
	                                    <input type="file" name="upload_file3" class="form-control">
	                               
	                                </td>
	                            </tr>
                                 <tr>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td  colspan="2" align="right"  id="td1">
                                        
                                        <button class="btn" id="btnconfirm" type="submit" formaction="<%= contextPath %>/roomUpdate.ho" onclick="updateRoom();">수정하기</button>
                                    </td>
                                </tr>
                            </table>
    
                            


                </form>
                    </div>
                    
                    <script>
                    
 						function checkOp(){
                    	
		                    	var oplist = '<%= r.getOpNo()  %>'
		                    	var l = oplist.split(",");
		                    	
		                    	console.log(<%= r.getOpNo()  %>);
		                    	for(var i =0; i<l.length; i++){
		                    		 $('input:checkbox[name=roomOption][value='+l[i]+']').attr("checked", true).parent().addClass('on');
		          	                
		                    	}
		                    	
                   		 }
                    </script>
                    
					<% } %>
                   


                   

                    <script>
                    
                   
                    
                    
                    
                        function updateRoom(){
                            if(confirm("객실을 수정하시겠습니까?")){
                                
                            }
                        }

                        function deleteRoom(){
                            if(confirm("객실을 삭제하시겠습니까?")){
                               
                            }
                        }


                    </script>
                </div>
            </div>
        
            <!-- 메인 부분  끝 -->


        

        </section>

          <%@ include file="/views/common/footer.jsp"%>
    </div>   
</body>
</html>