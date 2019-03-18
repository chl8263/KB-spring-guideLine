<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

  

<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.min.js"></script>

   
<script type="text/javascript">

 




$(window).load(function(){
	fwk.initializeForms(); //fwk_common.js 
});

</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/fwk_common.js"></script>

</head>

<body>
<div id="content-wrapper">

	<div class="container-fluid">
		<div class="container-wrapper">
			<div class="container">
				<h1>신규 직원 추가</h1>
				<p class="lead">새로운 직원 인적 사항을 입력하세요</p>
				<form
					action="${pageContext.request.contextPath}/admin/addMember?${_csrf.parameterName}=${_csrf.token}"
					method="post" 
					>
					<div class="form-group">
						<label for="name">이름 : </label>
						<input type="text" name="name" id="name" class="form-control" />
						
					</div>

					<div class="form-group">
						<label for="category">성별 : </label>
						<input type="radio" name="sex" id="sex" value="man" />
						남자
						<input type="radio" name ="sex" id="sex" value="woman" />
						여자
					</div>

					<div class="form-group">
						<label for="manufacturer">나이 : </label>
						<input type="number" name="age" id="age" class="form-control inputNumOnly" />
						

					</div>

					<div class="form-group">
						<label for="price">직책 : </label>
						<input type= "text" name = "position" id="position" class="form-control" />
						

					</div>


					<input type="submit" value="submit" class="btn btn-default">
					<a href="<c:url value="redirect:/"/>"
						class="btn btn-default">cancel</a>
				</form>

				<br />

			</div>
		</div>
	</body>		
</html>