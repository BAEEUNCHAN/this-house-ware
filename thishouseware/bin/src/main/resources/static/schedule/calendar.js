/**
 * calendar.js
 * 
 * FullCalendar 이벤트 속성
 * 1) title : 해당 이벤트의 제목을 나타낸다.
 * 2) color : 이벤트의 색상을 변경한다.
 * 3) textColor : 이벤트 내용의 텍스트의 색상을 변경한다.
 * 4) backgroundColor : 이벤트 배경색의 색상만을 변경한다.
 * 5) borderColor : 이벤트 테두리의 색상만을 변경한다.
 * 6) rendering : "bakground"라고 입력하면 color, backgroundColor의 색상으로 해당일 전체의 내용이 채워진다.
 */
var calendar;

 
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendardiv');    
  	/*fetch('/schListAll', {
    	method: "POST",
    	headers: {
        	"Content-Type": "application/json",            
    	},
  	})
  	.then(result => result.json())
  	.then(result => {
    	*/
			allEvents = [];
			console.log(allEvents);
    	// full-calendar 생성하기
    	 	calendar =  new FullCalendar.Calendar(calendarEl, {
			themeSystem: 'bootstrap5', // Bootstrap 5 Theming
      		googleCalendarApiKey: key, // 구글캘린더 api키 입력하시면 됩니다.
      		height: '600px', // calendar 높이 설정
      		expandRows: true, // 화면에 맞게 높이 재설정
      		slotMinTime: '00:00', // Day 캘린더에서 시작 시간
      		slotMaxTime: '23:59', // Day 캘린더에서 종료 시간
      		// eventTextColor: 'black', // 이벤트 텍스트 컬러
      		customButtons:{
        		myCustomButton:{
          			text:"일정추가",
          			click: function() {
            			//부트스트랩 모달 열기
            			$("#addEventModal").modal("show");              
          			}
        		},        
      		},
      		// 해더에 표시할 툴바
      		headerToolbar: {
        		left: 'prev,next today myCustomButton',
        		center: 'title',
        		right: 'dayGridMonth,timeGridWeek,timeGridDay listWeek'
      		},
      		buttonText: {
				// 해더 툴바 텍스트 변경시
			},
      		initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
      		// initialDate: '2024-10-15', // 초기 날짜 설정 (설정하지 않으면 오늘 날짜가 보인다.)
      		// slotDuration: '01:00:00', // 기본 30분 단위가 아니라 1시간 단위로 일정(일) 설정
      		navLinks: true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
      		editable: true, // default : false 이벤트 드래그 등의 편집여부를 설정함
      		selectable: true, // 달력 일자 드래그 설정가능
      		nowIndicator: true, // 현재 시간 마크
      		allDaySlot:false, // timegrid 화면에서  allday 슬롯 제외하기
      		dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
      		locale: 'ko', // 한국어 설정

      		eventAdd: function (obj) {      // 이벤트 추가 시 발생하는 이벤트
        		console.log("eventAdd : " + obj);
      		},
      		
      		eventChange: function (obj) {    // 이벤트 수정 시 발생하는 이벤트
        		console.log("eventChange : " + obj);
      		},
      		
      		eventRemove: function (obj) {     // 이벤트 삭제 시 발생하는 이벤트
        		console.log("eventRemove : " + obj);
      		},
      		
      		select: function(arg) { // 캘린더에서 드래그로 이벤트를 생성할 수 있다.
        		/*let title = prompt('일정 추가');
        		if (title) {
          			calendar.addEvent({
            			title: title,
            			start: arg.start,
            			end: arg.end,
            			allDay: arg.allDay
          			});
          			let obj = new Object();
					obj.title = title;
					obj.start = arg.start;
		    		obj.end = arg.end;
				    obj.id = 'emp101';
				    obj.sheduleCode = 'f1';
		    		obj.departmentNo = 13;
				    console.log(obj);
				    
		    		$.ajax({
						type: 'post',
						url: '/schAdd',
						data: JSON.stringify(obj),
						contentType: 'application/json',
						success: function(result) {
							alert("저장 성공");
						},
						error: function(result) {
							alert(result);
		        		}
					});
        		}*/
        		calendar.unselect()
      		},
      		
      		eventClick: function (info) {      // 일정 클릭 시	      			      		
	      		info.jsEvent.preventDefault(); // 브라우저 네이게이션 비활성화
	      		
	      		// 구글 캘린더 일정 과 다른 사람의 이벤트 제외
	      		if(info.event.extendedProps.scheduleNo == undefined || info.event.id != id) { 	      
					return;	
				}
				
				/*console.log(info.event.extendedProps.scheduleNo);
				console.log(info.event.title);
				console.log(info.event.start);
				console.log(info.event.end);
				console.log(info.event.extendedProps.content);
				console.log(info.event.backgroundColor);
				console.log(info.event.id);*/
				
				// 모달창에 현재 이벤트 정보 출력
				let startDt = new Date(info.event.start - new Date().getTimezoneOffset() * 60000).toISOString().slice(0, -5);
				let endDt = new Date(info.event.end - new Date().getTimezoneOffset() * 60000).toISOString().slice(0, -5);								
				$("#eventInfoModal #title").val(info.event.title);    		
				$("#eventInfoModal #start").val(startDt);    		
				$("#eventInfoModal #end").val(endDt);    		
				$("#eventInfoModal #content").val(info.event.extendedProps.content);   
				$("#eventInfoModal #color").val(info.event.backgroundColor).prop("selected",true); 		
				$("#eventInfoModal").modal("show");
				
				//일정 수정 이벤트
				$("#modSchedule").get(0).onclick = function(target) {
					let eventData = {
				        title: $("#eventInfoModal #title").val(),
				        start: $("#eventInfoModal #start").val(),
				        end: $("#eventInfoModal #end").val(),						        
				    };
					//빈값입력시 오류
					if (eventData.title == "" || eventData.start == "" || eventData.end == "") {
						alert("일정이름, 시작시간, 종료시간은 필수 입력값입니다.");
					} 
					//끝나는 날짜가 시작하는 날짜보다 값이 크면 안됨
					else if ($("#eventInfoModal #start").val() > $("#eventInfoModal #end").val()) {
						alert("시간을 잘못 입력 하셨습니다.");
					}
					else {
						// 서버로 data 보내기
						let event = updateSchedule(info);
						if(event != null) {
							info.event.remove(); 
							calendar.addEvent(event);
						}
						$("#eventInfoModal").modal("hide");														
					}
				};
				
				// 일정 삭제 이벤트
				$("#delSchedule").get(0).onclick = function() {
					if (confirm("선택한 일정을 삭제하시겠습니까?")) {
						deleteSchedule(info);
						$("#eventInfoModal").modal("hide");
					}				
				};
    		},
      
  			//데이터 가져오는 이벤트        
  			eventSources:[
    			{
    				events: allEvents,        
    			},
    			{
      				// Google Calendar에서 공휴일 정보 가져오기
      				googleCalendarId : 'ko.south_korea.official#holiday@group.v.calendar.google.com',
      				backgroundColor: 'red',
    			}
  			]
    	});
    	
		//일정 추가 모달창 이벤트
		$("#addSchedule").get(0).onclick = function() {
			let eventData = {
		        title: $("#addEventModal #title").val(),
		        start: $("#addEventModal #start").val(),
		        end: $("#addEventModal #end").val(),	        
			};
			console.log(eventData.title);
			console.log(eventData.start);
			console.log(eventData.end);
			//빈값 입력시 오류
			if (eventData.title == "" || eventData.start == "" || eventData.end == "") {
				alert("일정이름, 시작시간, 종료시간은 필수 입력값입니다.");
			} 
			//끝나는 날짜가 시작하는 날짜보다 값이 크면 안됨
			else if ($("#addEventModal #start").val() > $("#addEventModal #end").val()) {
				alert("시간을 잘못 입력 하셨습니다.");
			}
			else {
				// 서버로 data 보내기
				let event = saveSchedule();
				// 이벤트 추가
				if (event != null) {
					calendar.addEvent(event);
				}
		        
		        $("#addEventModal").modal("hide");	        
			}
		};
		
		// 캘린더 랜더링
    	calendar.render();
  	//});
});

// 일정 저장
function saveSchedule() {
	// 서버로 data 보내기
	let obj = new Object();
	obj.title = $("#addEventModal #title").val();
	obj.start = $("#addEventModal #start").val();
    obj.end = $("#addEventModal #end").val();
    obj.content = $("#addEventModal #content").val();
    obj.color = $("#addEventModal #color").val();
    obj.id = id;
    obj.sheduleCode = 'f1';
    obj.departmentNo = 13;    
    $.ajax({
		type: 'post',
		url: '/schAdd',
		data: JSON.stringify(obj),
		contentType: 'application/json',
		async: false,
		success: function(result) {
			if(result.success) {
				obj.scheduleNo = result.scheduleNo;
				alert("저장했습니다");
		        // location.reload();
			}
			else {
				alert("저장에 실패했습니다");
				return null;
			}
		},
		error: function(result) {
			alert(result);
			return null;
        }
	});
	return obj;
}

// 일정 수정
function updateSchedule(info) {
	// 서버로 data 보내기
	let obj = new Object();
	obj.scheduleNo = info.event.extendedProps.scheduleNo;
	obj.title = $("#eventInfoModal #title").val();
	obj.start = $("#eventInfoModal #start").val();
    obj.end = $("#eventInfoModal #end").val();
    obj.content = $("#eventInfoModal #content").val();
    obj.color = $("#eventInfoModal #color").val();
    obj.id = id;
    obj.sheduleCode = 'f1';
    obj.departmentNo = 13;
    $.ajax({
		type: 'post',
		url: '/schUpdate',
		data: JSON.stringify(obj),
		contentType: 'application/json',
		success: function(result) {
			if(result.success) {
				alert("수정했습니다");
		        // location.reload();		        
			}
			else {
				alert("수정 중 오류가 발생했습니다");
				return null;
			}
		},
		error: function(result) {
			alert(result);
			return null;
        }
	});
	return obj;
}

// 일정 삭제
function deleteSchedule(info) {	
	$.ajax({
		type: 'post',
		url: '/schDelete?no='+info.event.extendedProps.scheduleNo,
		success: function(result) {
      		if(result.success) {
        		info.event.remove();
        		alert("삭제하였습니다");
        		// location.reload();							
      		}                        
      		else {
        		alert("오류가 발생하였습니다");
      		}
		},
		error: function(result) {
			alert(result);
		}
	})
}
 
/*
- 일정 저장시 고려해야 할 DB 테이블
1. title : 일정 이름
2. start : 일정 시작 시간
3. end : 일정이 끝나는 시간
4. backgroundColor : 일정 배경색
*/