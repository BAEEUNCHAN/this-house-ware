//임시저장 모달.js

//== Class definition
var SweetAlert2Demo = (function() {
	//== Demos
	var initDemos = function() {
/*		$("#alert_demo_7").click(function(e) {
			Swal.fire({
				title: "Are you sure?",
				text: "You won't be able to revert this!",
				icon: "warning",
				showCancelButton: true,
				confirmButtonText: "Yes, delete it!",
				cancelButtonText: "No, cancel!",
				confirmButtonClass: "btn btn-success",
				cancelButtonClass: "btn btn-danger",
				buttonsStyling: false,
			}).then((result) => {
				if (result.isConfirmed) {
					Swal.fire({
						title: "Deleted!",
						text: "Your file has been deleted.",
						icon: "success",
						confirmButtonClass: "btn btn-success",
						buttonsStyling: false,
					});
				}
			});
		});
*/
		//== 임시저장 모달
		$(".btnSave").click(function(e) {
			Swal.fire({
				title: "임시저장 하시겠습니까?",
				text: "저장 후에는 임시저장 문서함으로 이동됩니다.",
				icon: "warning",
				showCancelButton: true,
				confirmButtonText: "예",
				cancelButtonText: "아니오",
				buttonsStyling: false,
				customClass: {
			        confirmButton: 'custom-confirm-btn',  // 클래스 추가
			        cancelButton: 'custom-cancel-btn',    // 클래스 추가
   				 }
			}).then((result) => {
				console.log(result);
				if (result.isConfirmed) {
					// AJAX 요청을 통해 임시저장 기능 호출
					$.ajax({
						type: "POST",
						url: "/edms/edmsInsert", // 서버의 임시 저장 URL
						data: {
							// 필요한 데이터 여기에 추가
							id: $("#id").val(),
							title: $("#edmsTitle").val(), // 제목
							edmsFormNo: $("#edmsFormNo").val(), // 결재양식
							content: $("#edmsContent").html(), // 내용 (HTML)
							approvalStatus: "임시저장",
							shareStatus: $("input[name='shareStatus']:checked").val(), // 공유폴더 여부
							shareFolderNo: $("#shareFolderNo").val(), // 공유 폴더 번호
						},
						success: function(response) {
							Swal.fire({
								title: "임시저장 완료",
								text: "저장이 완료되었습니다",
								icon: "success",
								confirmButtonClass: "btn btn-success",
								buttonsStyling: false,
							});
						},
						error: function(error) {
							Swal.fire({
								title: "오류 발생",
								text: "임시저장 중 오류가 발생했습니다.",
								icon: "error",
								confirmButtonClass: "btn btn-danger",
								buttonsStyling: false,
							});
						}
					});
				}
			});
		});
	};

	return {
		//== Init
		init: function() {
			initDemos();
		},
	};
})();

//== Class Initialization
jQuery(document).ready(function() {
	SweetAlert2Demo.init();
});