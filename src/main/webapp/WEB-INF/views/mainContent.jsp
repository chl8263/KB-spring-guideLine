<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<!-- <script type="text/javascript">
	$(document).ready(function(){
		$("#sendMemberpage").on('click',function(e){
			e.preventDefault();
			$.get("admin/gotoMemberpage");
		});
	});
</script> -->

<!-- Begin Page Content -->
        <div class="container-fluid">
        
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary" style="display:inline;">직원 목록</h6>
              <a href="admin/addMember" class="btn btn-secondary btn-icon-split" style="float:right;">
                    <span class="icon text-white-50">
                      <i class="fas fa-arrow-right"></i>
                    </span>
                    <span class="text">직원 추가하기</span>
                  </a>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr class="px-3 py-5 bg-gradient-success text-white">
                      <th>Name</th>
                      <th>Sex</th>
                      <th>Age</th>
                      <th>Position</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr class="px-3 py-5 bg-gradient-success text-white">
                      <th>Name</th>
                      <th>Sex</th>
                      <th>Age</th>
                      <th>Position</th>
                      <th>Action</th>
                    </tr>
                  </tfoot>
                  <tbody>
                    <c:forEach var="list" items="${list}">
						<tr>
							<td>${list['NAME']}</td>
							<td>${list['SEX']}</td>
							<td>${list['AGE']}</td>
							<td>${list['POSITION']}</td>
							<td>
							
							<a href="<c:url value="/admin/updateMember/${list['MEMBER_ID']}"/>" class="btn btn-info btn-icon-split">
                    		<span class="icon text-white-50">
                      		<i class="fas fa-info-circle"></i>
                    		</span>
                    		<span class="text">수정</span>
                  			</a>
							
							<a href="<c:url value="/admin/deleteMember/${list['MEMBER_ID']}"/>" class="btn btn-danger btn-icon-split">
                    		<span class="icon text-white-50">
                      		<i class="fas fa-trash"></i>
                    		</span>
                    		<span class="text">해고</span>
                  			</a>
                  			
                  			</td>
						</tr>

					</c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->