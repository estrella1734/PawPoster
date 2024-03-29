<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>${webName}-個人資料</title>

<jsp:include page="${component}/common_imports.jsp" />

<style>
.xmark {
	top: 10%;
	right: 7%;
}

.xmark-hide {
	display: none;
}
</style>
</head>

<body>
	<jsp:include page="${component}/header.jsp" />

	<main>
		<div class="container py-5">
			<div class="row">
				<div class="col-lg-4">
					<div class="card mb-4">
						<div class="card-body text-center">
							<img src="${m.mPhoto}" class="rounded-circle img-fluid w-50" />
							<h5 class="my-3">${m.mName }</h5>
						</div>
					</div>
					<div class="card mb-4 mb-lg-0">
						<div class="card mb-4">
							<div class="card-body">
								<div class="row">
									<div class="col-sm-3">
										<p class="mb-0">Email</p>
									</div>
									<div class="col-sm-9">
										<p class="text-muted mb-0">${m.email }</p>
									</div>
								</div>
								<hr />
								<div class="row">
									<div class="col-sm-3">
										<p class="mb-0">權限</p>
									</div>
									<div class="col-sm-9">
										<p class="text-muted mb-0">${m.level }</p>
									</div>
								</div>
								<hr />
								<div class="row">
									<div class="col-sm-3">
										<p class="mb-0">年齡</p>
									</div>
									<div class="col-sm-9">
										<p class="text-muted mb-0">${m.mAge }</p>
									</div>
								</div>
								<hr />
								<div class="row">
									<div class="col-sm-3">
										<p class="mb-0">寵物數</p>
									</div>
									<div class="col-sm-9">
										<p class="text-muted mb-0">${m.pets.size() }</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-8">
					<div class="row">

						<c:forEach items="${m.pets}" var="p">
							<div class="col-md-6 mb-4">
								<div class="card mb-4 mb-md-0">
									<div class="card-body text-center position-relative">
										<i
											class="fa-solid fa-xmark fa-beat fa-2xl position-absolute xmark xmark-hide"></i>
										<img src="${p.pPhotoBase64}" class="w-100 petPhoto" />
										<p class="card-text fs-3">${p.pName }</p>
									</div>
								</div>


							</div>
						</c:forEach>


					</div>
				</div>
			</div>
		</div>

	</main>

	<jsp:include page="${component}/footer.jsp" />
</body>

<script>
    const petPhotos = document.querySelectorAll(".petPhoto");
    const xmarks = document.querySelectorAll(".xmark");

    petPhotos.forEach((pPhoto) => {
      pPhoto.addEventListener("mouseenter", function () {
        this.previousElementSibling.classList.remove("xmark-hide");
      });
      pPhoto.addEventListener("mouseleave", function (e) {
        if (e.relatedTarget.classList.contains("xmark")) {
          return;
        }
        this.previousElementSibling.classList.add("xmark-hide");
      });
    });

    xmarks.forEach((xmark) => {
      xmark.addEventListener("click", function () {
        if (confirm("確定要刪除此寵物嗎?") == false) {
          return;
        }

        this.parentElement.parentElement.parentElement.remove();
      });
    });
  </script>
</html>
