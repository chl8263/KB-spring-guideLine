<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>




<script type="text/javascript">

	$(document).ready(function(){
		
		setDataTable(10,1);
		
	   $('table tr').mouseover(function(){ 
	      $(this).css("backgroundColor","#ccc"); 
	   }); 
	   $('table tr').mouseout(function(){ 
	      $(this).css("backgroundColor","#fff"); 
	   }); 
	   
	   
	   
	   $('#dataTable_length').change(function(){
		   var length = $('#dataTable_length option:selected').val();
		   setDataTable(length,1);
		   });
	});
	
	function setDataTable(length,currentPage){
		$.ajax({
			url : "paging/setDataTable",
			type : "GET",
			dataType : 'json',
			contentType : "application/json; charset=UTF-8",
			data : {length : length,currentPage : currentPage },
			success : function(){
				console.log($this.attr(list));
			}
		});
	}


	function deleteMember(id) {
		var result = confirm("직원을 해고하시겠습니까 ?");
		
		if(result){
			$.ajax({
				url : "admin/deleteMember/"+id,
				type : "GET",
				success : function(){
					document.location.href = "/demo/";
				}
			});
		} 
	}
</script>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-body">
			<div class="table-responsive">
				<div class="row">
					<div class="col-sm-12 col-md-6">
						<div class="dataTables_length" id="dataTable_length">
							<label>Show <select name="dataTable_length"
								id="dataTable_length">
									<option value="10">10</option>
									<option value="25">25</option>
									<option value="50">50</option>
									<option value="100">100</option>
							</select> entries
							</label>
						</div>
					</div>
					<label>Search: <input type="search" placeholder=""></label>
					<a href="admin/addMember" class="btn btn-secondary btn-icon-split"
						style="float: right;"> <span class="icon text-white-50">
							<i class="fas fa-arrow-right"></i>
					</span> <span class="text">직원 추가하기</span>
					</a>

				</div>
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr class="px-3 py-5 bg-gradient-success text-white">
							<th>Id</th>
							<th>Name</th>
							<th>Sex</th>
							<th>Age</th>
							<th>Position</th>
							<th>Action</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="list" items="${list}">
							<tr id="tableData">
								<td>${list['MEMBER_ID']}</td>
								<td>${list['NAME']}</td>
								<td>${list['SEX']}</td>
								<td>${list['AGE']}</td>
								<td>${list['POSITION']}</td>
								<td><a
									href="<c:url value="/admin/updateMember/${list['MEMBER_ID']}"/>"
									class="btn btn-info btn-icon-split"> <span
										class="icon text-white-50"> <i
											class="fas fa-info-circle"></i>
									</span> <span class="text">수정</span>
								</a>

									<div onclick="deleteMember(${list['MEMBER_ID']})"
										class="btn btn-danger btn-icon-split" "/> <span
									class="icon text-white-50"> <i class="fas fa-trash"></i>
								</span> <span class="text">해고</span> </a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>

				<div class="row">
					<div class="col-sm-12 col-md-5">
						<div class="dataTables_info" id="dataTable_info" role="status"
							aria-live="polite">Showing 1 to 1 of 1 entries</div>
					</div>
					<div class="col-sm-12 col-md-7">
						<div class="dataTables_paginate paging_simple_numbers"
							id="dataTable_paginate">
							<ul class="pagination">
								<li class="paginate_button page-item previous disabled"
									id="dataTable_startPage"><a href="#"
									aria-controls="dataTable" data-dt-idx="0" tabindex="0"
									class="page-link"><<</a></li>
								<li class="paginate_button page-item previous disabled"
									id="dataTable_previous"><a href="#"
									aria-controls="dataTable" data-dt-idx="0" tabindex="0"
									class="page-link"><</a></li>
								<li class="paginate_button page-item active"><a href="#"
									aria-controls="dataTable" data-dt-idx="1" tabindex="0"
									class="page-link">1</a></li>
								<li class="paginate_button page-item next disabled"
									id="dataTable_next"><a href="#" aria-controls="dataTable"
									data-dt-idx="2" tabindex="0" class="page-link">></a></li>
								<li class="paginate_button page-item previous disabled"
									id="dataTable_lastPage"><a href="#"
									aria-controls="dataTable" data-dt-idx="0" tabindex="0"
									class="page-link">>></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->