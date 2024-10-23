//임시저장 모달.js
//== Class definition
var SweetAlert2Demo = (function() {
	//== Demos
	var initDemos = function() {
		$("#alert_demo_7").click(function(e) {
			swal({
				title: "Are you sure?",
				text: "You won't be able to revert this!",
				type: "warning",
				buttons: {
					confirm: {
						text: "Yes, delete it!",
						className: "btn btn-success",
					},
					cancel: {
						visible: true,
						className: "btn btn-danger",
					},
				},
			}).then((Delete) => {
				if (Delete) {
					swal({
						title: "Deleted!",
						text: "Your file has been deleted.",
						type: "success",
						buttons: {
							confirm: {
								className: "btn btn-success",
							},
						},
					});
				} else {
					swal.close();
				}
			});
		});

		//== 임시저장 모달
		$("#save_demo").click(function(e) {
			swal({
				title: "임시저장 하시겠습니까?",
				text: "저장 후에도 언제든지 수정 가능합니다.",
				icon: "warning",
				buttons: {
					confirm: {
						text: "네, 임시저장합니다.",
						className: "btn btn-success",
					},
					cancel: {
						visible: true,
						className: "btn btn-danger",
					},
				},
			}).then((save) => {
				if (save) {
					// AJAX 요청을 통해 임시저장 기능 호출
					$.ajax({
						type: "POST",
						url: "docBox/insertSaveDoc", // 서버의 임시 저장 URL
						data: JSON.stringify({
							// 필요한 데이터 여기에 추가해야됨
							title: $("#edmsTitle").val(), // 제목
						    edmsFormNo: $("#edmsFormNo").val(), // 결재양식
						    content: $("#edmsContent").html(), // 내용 (HTML)
						    shareStatus: $("input[name='shareStatus']:checked").val(), // 공유폴더 여부
						    shareFolderNo: $("#shareFolderNo").val(), // 공유 폴더 번호
						}),
						contentType: "application/json",
						success: function(response) {
							swal({
								title: "임시저장 완료",
								text: "저장이 완료되었습니다",
								icon: "success",
								buttons: {
									confirm: {
										className: "btn btn-success",
									},
								},
							});
						},
						error: function(error) {
							swal({
								title: "오류 발생",
								text: "임시저장 중 오류가 발생했습니다.",
								icon: "error",
								buttons: {
									confirm: {
										className: "btn btn-danger",
									},
								},
							});
						}
					});
				} else {
					swal.close();
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
