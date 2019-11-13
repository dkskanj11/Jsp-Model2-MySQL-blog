<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="/include/nav.jsp"%>

<!--================Blog Area =================-->
<section class="blog_area single-post-area">
   <div class="container">
      <div class="row">
         <div class="col-lg-2"></div>
         <div class="col-lg-8">
            <div class="main_blog_details">
               <a href="#"><h4>${board.title}</h4></a>
               <div class="user_details">
                  <div class="float-left">
                     <c:if test="${board.userId eq sessionScope.user.id}">
                        <a href="/blog/board?cmd=updateForm&id=${board.id}">update</a>
                        <a href="/blog/board?cmd=delete&id=${board.id}">delete</a>
                     </c:if>
                  </div>
                  <div class="float-right">
                     <div class="media">
                        <div class="media-body">
                           <h5>${board.user.username}</h5>
                           <p>${board.createDate}</p>
                        </div>
                        <div class="d-flex">
                           <img style="width: 60px; height: 60px; border-radius: 50%; " src="${board.user.userProfile}" alt="">
                        </div>
                     </div>
                  </div>
               </div>
               <!-- 본문 시작 -->
               <p>${board.content}</p>
               <!-- 본문 끝 -->
               <hr />
            </div>
			<!-- 댓글 쓰기 시작 -->
            <div class="comment-form" style="margin-top: 0px;">
               <h4 style="margin-bottom: 20px">Leave a Comment</h4>
               <form id="comment-submit">
                  <input type="hidden" name="userId" value="${sessionScope.user.id}"/>
                  <input type="hidden" name="boardId" value="${board.id}"/>
                  <div class="form-group">
                     <textarea id="content" style="height: 60px" class="form-control mb-10" rows="2" name="content" placeholder="Messege" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Messege'" required=""></textarea>
                  </div>
                  <button type="button" onClick="commentWrite()" class="primary-btn submit_btn">Post Comment</button>
               </form>
            </div>
            <!-- 댓글 쓰기 끝 -->
           <!-- 댓글 시작 -->
            <!--  before -->
            <div class="comments-area" id="comments-area">
               <!-- prepend -->
               <c:forEach var="comment" items="${comments}">
                  <!-- 댓글 아이템 시작 -->
                  <div class="comment-list" id="comment-id-${comment.id}">
                     <!-- ID 동적으로 만들기 -->
                     <div class="reply-btn" style="position:relative; left:420px; top:30px;">
                     <c:if test="${comment.userId eq sessionScope.user.id}">
                           <button onClick="commentDelete(${comment.id})" class="btn-reply text-uppercase" style="display: inline-block; float: left; margin-right: 10px;">댓글삭제</button>
                           </c:if>
                           <button id="toggle" onClick="replyListShow(${comment.id})" class="btn-reply text-uppercase" style=" margin-right: 10px;" >답글</button>

                        </div>
                     <div class="single-comment justify-content-between d-flex">
                        <div class="user justify-content-between d-flex">
                           <div class="thumb">
                              <img style="width: 60px; height: 60px; border-radius: 50%;" src="${comment.user.userProfile}" alt="">
                           </div>
                           <div class="desc">
                              <h5>
                                 <a href="#">${comment.user.username}</a>
                              </h5>
                              <p class="date">${comment.createDate}</p>
                              <p class="comment">${comment.content}</p>
                           </div>
                        </div>
                     </div>
                  </div>
               <!-- 댓글 아이템 끝 -->
               </c:forEach>
               <!--  append -->
               
            </div>
            <!-- after -->
            <!-- 댓글 끝 -->

            
         </div>

         <div class="col-lg-2"></div>
      </div>
   </div>
</section>

<!--================Blog Area =================-->

<%@ include file="/include/footer.jsp"%>

<!--================Comment Script =================-->
<script>
   
function replyItemForm(id, username, content, createDate, userProfile){
    var replyItem = "<div class='reply-area'><div class='comment-list left-padding' id='reply-id-"+id+"'>";
    replyItem+= "<div class='single-comment justify-content-between d-flex'>";
    replyItem+= "<div class='user justify-content-between d-flex'>";
    replyItem+= "<div class='thumb'><img style='width: 60px; height: 60px; border-radius: 50%;' src='"+userProfile+"' alt=''></div>";
    replyItem+= "<div class='desc'><h5><a href='#'>"+username+"</a></h5>";
    replyItem+= "<p class='date'>"+createDate+"</p>";
    replyItem+= "<p style=' margin-bottom: 30px; margin-top: -5;' class='comment'>"+content+"</p>";
    replyItem+= "</div></div>";
    if("${sessionScope.user.username}" == username ) {
    	replyItem+= "<div class='reply-btn'><button onClick='replyDelete("+id+")' class='btn-reply text-uppercase'>삭제</button>";
    }else {}
    replyItem+= "</div></div></div></div>";
    
    return replyItem;
 }
   
   function commentWriteForm(id, username, content, createDate, userProfile){
       var comment_list = "<div class='comment-list' id='comment-id-"+id+"'>";
       comment_list += "<div class='reply-btn' style='position:relative; left:420px; top:30px;'>";
       comment_list += "<button onClick='commentDelete("+id+")' class='btn-reply text-uppercase' style='display: inline-block; float: left; margin-right: 10px;'>댓글삭제</button>";
       comment_list += "<button id='toggle' onClick='replyListShow("+id+")' class='btn-reply text-uppercase' style=' margin-right: 10px;' >답글</button> </div>";
       comment_list += "<div class='single-comment justify-content-between d-flex'>";
       comment_list += "<div class='user justify-content-between d-flex'>";
       comment_list += "<div class='thumb'> <img style='width: 60px; height: 60px; border-radius: 50%;' src='"+userProfile+"' alt=''> </div>";
       comment_list += "<div class='desc'><h5><a href='#'>"+username+"</a></h5>";
       comment_list += "<p class='date'>"+createDate+"</p><p class='comment' style='word-break: break-all;'>"+content+"</p></div></div>";
       comment_list += "</div></div>";
       
       
       
       console.log(comment_list);
       return comment_list;
   }
   
   //comment 쓰기
   function commentWrite(){
      var comment_submit_string = $("#comment-submit").serialize();
      console.log(comment_submit_string);
      $.ajax({
         method: "POST",
         url: "/blog/api/comment?cmd=write",
         data: comment_submit_string+"",
         contentType: "application/x-www-form-urlencoded; charset=utf-8",
         dataType: "json",
         success: function(comment){
        	 
        	 
        	 //화면에 적용
               var comment_et = commentWriteForm(comment.id, comment.user.username, comment.content, comment.createDate, comment.user.userProfile);
               $("#comments-area").append(comment_et);
               
               $("#content").val("");
            
         },
         error: function(xhr){
            console.log(xhr.status);
         }
         
      });
      
   }
   
   
   //comment 삭제
   function commentDelete(comment_id){ //자바스크립트는 하이폰 사용 불가
      $.ajax({
         method: "POST",
         url: "/blog/api/comment?cmd=delete",
         data: comment_id+"",
         contentType: "text/plain; charset=utf-8", //MIME 타입
         success: function(result){
            console.log(result);
            //해당 엘레멘트(DOM)을 찾아서 remove() 해주면 됨.
            if(result === "ok"){
               $("#comment-id-"+comment_id).remove();
            }
            
         },
         error: function(xhr){
            console.log(xhr.status);
         }
      });
   }

   //reply 보기 - ajax
   
   var temp = 1;
   function replyListShow(comment_id) {
	   
	   
	$.ajax({
		method: "POST",
		url: "/blog/api/reply?cmd=list",
		data: comment_id+"",
		contentType : "text/plain; charset=utf-8",
		dataType: "json",
		success: function(replys) {
			
			if(temp === 1) {
				
				var comment_form_inner = "<h4 style='margin-bottom:20px'>Leave a Reply</h4><form id='reply-submit-"+comment_id+"'><input type='hidden' name='commentId' value='"+comment_id+"'/><input type='hidden' name='userId' value='${sessionScope.user.id}'/><div class='form-group'><textarea style='height:60px' id='replycontent' class='form-control mb-10' rows='2' name='content' placeholder='Messege' required=''></textarea></div><button type='button' onClick='replyWrite("+comment_id+")' class='primary-btn submit_btn'>Post Comment</button></form>";

			      //<div class="comment-form" style="margin-top:0px;"></div>
			      var reply_form = document.createElement("div"); //div 빈 박스 생성
			      reply_form.id = "reply-form-"+comment_id;
			      reply_form.className = "reply-form-area"; //div에 클래스 이름을 주고
			      reply_form.style = "margin-top:0px"; //div에 style을 준다.

			      reply_form.innerHTML = comment_form_inner;
			      console.log(reply_form);

			      $("#comment-id-"+comment_id).append(reply_form); //after와 append, before와 prepend 
				
				for(reply of replys) {
					var reply_et = replyItemForm(reply.id, reply.user.username, reply.content, reply.createDate, reply.user.userProfile, reply.user.id);
					$("#comment-id-"+reply.commentId).after(reply_et);	
				}
				temp = 0;
				console.log("temp : " +temp);
			}else{
				$('.reply-area').remove();
				$('.reply-form-area').remove();
				temp = 1;
				console.log("temp : " +temp);
			}
					
		},
		error: function(xhr) {
			console.log(xhr);
		}
	});
   }
   
   function replyDelete(reply_id) {
	   $.ajax({
		   method: "POST",
		   url: "/blog/api/reply?cmd=delete",
		   data: reply_id+"",
		   contentType : "text/plain; charset=utf-8",
		   success: function(result) {
			   if(result== "ok") {
				   $("#reply-id-"+reply_id).remove();
			   }
		   },
		   error: function(result) {
			   console.log(result);
		   }
	   });
   }

   //reply Form 만들기  - 화면에 로딩!!

   function replyForm(comment_id) {
      
   }
   
   //reply 쓰기
   function replyWrite(comment_id){
	   
      var reply_submit_string = $("#reply-submit-"+comment_id).serialize();
      console.log(reply_submit_string);
      $.ajax({
         method: "POST",
         url: "/blog/api/reply?cmd=write",
         data: reply_submit_string+"",
         contentType: "application/x-www-form-urlencoded; charset=utf-8",
         dataType: "json",
         success: function(reply){
        	 //화면에 적용
              var reply_et = replyItemForm(reply.id, reply.user.username, reply.content, reply.createDate, reply.user.userProfile);
						$("#comment-id-"+reply.commentId).after(reply_et);
						
						$("#replycontent").val("");
						
            
         },	
         error: function(reply){
            console.log(reply.status);
         }
         
      });
      
   }
</script>
</body>
</html>



