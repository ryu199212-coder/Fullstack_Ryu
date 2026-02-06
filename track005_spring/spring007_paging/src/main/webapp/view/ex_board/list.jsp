<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>

   <div class="container card  my-5 p-4  userTable">
      <h3 class="card-header"> 관리자 MBTI USER BOARD</h3>  
      <table class="table table-striped table-bordered table-hover">
      	<caption></caption>   	
      	<thead>
      		<tr>
      			<th scope="col">NO</th>
      			<th scope="col">PROFILE</th> 
      			<th scope="col">USERNO</th>
      			<th scope="col">EMAIL</th>
      			<th scope="col">MBTI TYPE</th>
      			<th scope="col">DATE</th>  
      			<th scope="col">수정</th>
      			<th scope="col">삭제</th> 
      		</tr>	
      	</thead>
      	<tbody>   
      	</tbody>
      </table>        
   </div>
   <div class="show-result container card  my-5 p-4">   
   	<form>
		  <div class="mb-3"> 
		    <img src=""   alt="" style="width:80px"  id="img" />
		  </div>			
		<div class="mb-3 mt-3">
			<label  for="email" class="form-label">Email:</label> 
			<input  type="email" class="form-control" id="email"
					placeholder="이메일을 입력해주세요" required  name="email" readonly>
		</div>			
		<div class="mb-3">
			<label class="form-check-label"  for="mbtiTypeId">MBTI TYPE : </label>  
			<select   name="mbtiTypeId"  id="mbtiTypeId"  class="form-control">
				<option value="1">ISTJ</option>
				<option value="2">ISFJ</option>
				<option value="3">INFJ</option>
			</select>
			
		</div>
		<div class="userhidden_no"></div>
		<input type="button"  value="USER 데이터 수정"   
			   class="btn btn-primary"   title="USER 데이터수정"  id="updateUser" />
	</form>
   </div>	
<script>
	$(function() {
	    userList();
	    userSelect();
	    userUpdate();
	    userDelete();
	});
	function userList(){
		$.ajax({
			url :"selectAll",
			type :"GET",
			/* success : function(json){console.log(json);}, */
			success : userListResult,
			error : function(xhr, ststus, msg){alert(status + "/" + msg);}
		});
	}
	function userListResult(json){
	    console.log(json);
	    $(".userTable tbody").empty(); // 테이블의 tbody
	    let total = json.length; // 전체갯수 확인
	    
	    $.each(json, function(idx, user){
	        $("<tr>")
	            .append($("<td>").html(total - idx))
	            .append($("<td>").html('<img src="upload/' + user.ufile + '" alt="" style="width:80px" />'))
	            .append($("<td>").html(user.appUserId))
	            .append($("<td>").html(user.email))
	            .append($("<td>").html(user.mbtiTypeId))
	            .append($("<td>").html(user.createdAt))
	            .append($("<td>").html("<input type='button' class='btn btn-primary selectUser' value='수정' />"))
	            .append($("<td>").html("<input type='button' class='btn btn-danger deleteUser' value='삭제' />"))
	            .append($("<td>").append($("<input type='hidden' class='hidden_id' />").val(user.appUserId)))
	            .append($("<td>").append($("<input type='hidden' class='hidden_email' />").val(user.email)))
	            .appendTo(".userTable tbody");
	    });
	}

	function userSelect(){
	    $("body").on("click", ".selectUser", function(){
	        let appUserId = $(this).closest("tr").find(".hidden_id").val(); 
	        $.ajax({
	            url  : "select",
	            type : "GET",
	            data : { appUserId: appUserId },
	            success: function(json){ 
	                console.log(json);
	                $("#img").attr("src", "upload/" + json.result.ufile);
	                $("#email").val(json.result.email);
	                $("#mbtiTypeId").val(json.result.mbtiTypeId);
	                $(".userhidden_no")
	                	.html( $('<input type="hidden" class="hidden_id" />').val(json.result.appUserId) );
	            },
	            error: function(xhr, status, error){ 
	                alert("error"); 
	            }
	        });
	    });
	}

	function userUpdate(){$("#updateUser").on("click", function(){
	 	let  appUserId = $(".userhidden_no .hidden_id").val();
        let mbtiTypeId = $("#mbtiTypeId").val();

        if(confirm("정말 수정하시겠습니까?")){
            $.ajax({
                url  : "adminEdit",   
                type : "POST",      
                data : { appUserId  : appUserId,
	                     mbtiTypeId : mbtiTypeId },	                     
                success: function(json){ 
                    alert("수정 완료되었습니다."); 
                    $(".hidden_id").val(json.result.hidden_id);
                    $("#mbtiTypeId").val(json.result.mbtiTypeId);                    
                    userList(); 
                },
                error: function(json){ alert("수정 실패: " + status + " / " + msg); }
            });
        }
    });
		
	}


	function userDelete(){
		 $("body").on("click", ".deleteUser", function(){
			 	let appUserId = $(this).closest("tr").find(".hidden_id").val();
		        let     email = $(this).closest("tr").find(".hidden_email").val();
	
		        if(confirm(email + "정말 삭제하시겠습니까?")){
		            $.ajax({
		                url  : "adminDelete",   
		                type : "POST",      
		                data : { appUserId:appUserId },
		                success: function(json){ alert("삭제 완료되었습니다."); userList(); },
		                error: function(json){ alert("삭제 실패: " + status + " / " + msg); }
		            });
		        }
		    });
	}
</script>   
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->