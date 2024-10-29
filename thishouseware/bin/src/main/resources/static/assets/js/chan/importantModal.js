var SweetAlert2Demo = (function() {
	var initDemos = function() {

		//== 중요문서 모달
		$(".chk_box input[type='checkbox']").on('change', function(e) {
			// 체크박스가 체크된 경우만 모달을 띄움
			if ($(this).is(':checked')) {
				let trTag = $(this).closest('tr'); // 체크된 체크박스가 속한 tr 요소를 가져옴
				let edmsDocNo = trTag.find('td.hidden1').text().trim(); // 문서번호
				let departmentNo = trTag.find('td.departmentNo').text().trim(); // 부서번호
				let title = trTag.find('td.title').text().trim(); // 제목
				let id = trTag.find('td.id').text().trim(); // 사용자 id
				// 문서 내용이 td.content에 있는지 확인 필요
				let content = trTag.find('td.content').length ? trTag.find('td.content').html() : ''; // 문서 내용이 있을 경우에만 가져오기

				Swal.fire({
					title: "중요 문서함으로 이동하시겠습니까?",
					text: "선택한 문서가 중요 문서함으로 이동됩니다.",
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
					if (result.isConfirmed) {
						// AJAX 요청을 통해 중요문서 이동 호출
						$.ajax({
							type: "POST",
							url: "/docBox/updateDeptImportant", // POST 요청을 위한 올바른 URL
							data: {
								edmsDocNo: edmsDocNo,
								id: id,
								departmentNo: departmentNo,
								title: title,
								content: content,
								approvalStatus: "결재완료", // 고정값
								important: true // 중요 문서로 설정
							},
							success: function(response) {
								Swal.fire({
									title: "이동 완료",
									text: "중요문서로 이동이 완료되었습니다",
									icon: "success",
									confirmButtonClass: "btn btn-success",
									buttonsStyling: false,
									customClass: {
										confirmButton: 'custom-success-btn'  // 성공 모달 버튼 스타일 클래스
									}
								}).then(function() {
									location.reload(); // 페이지 새로고침
								});
							},
							error: function(error) {
								Swal.fire({
									title: "오류 발생",
									text: "이동 중 오류가 발생했습니다.",
									icon: "error",
									buttonsStyling: false,
									customClass: {
										confirmButton: 'custom-error-btn', // 오류용 버튼 클래스
									}
								});
							}
						});
					} else {
						// 체크를 해제하기 (취소 시)
						$(this).prop('checked', false);
					}
				});
			}
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
$(document).ready(function() {
	SweetAlert2Demo.init();
});