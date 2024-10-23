/**
 * calendar.js
 */
document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');    
  fetch('/schListAll', {
    method: "POST",
    headers: {
        "Content-Type": "application/json",            
    },
  })
  .then(result => result.json())
  .then(result => {
    console.log(result);
    allEvents = result;

    // full-calendar 생성하기
    var calendar = new FullCalendar.Calendar(calendarEl, {
      themeSystem: 'bootstrap5', // Bootstrap 5 Theming
      googleCalendarApiKey: key,// 구글캘린더 api키 입력하시면 됩니다.
      height: '600px', // calendar 높이 설정
      expandRows: true, // 화면에 맞게 높이 재설정
      slotMinTime: '00:00', // Day 캘린더에서 시작 시간
      slotMaxTime: '23:59', // Day 캘린더에서 종료 시간
      customButtons:{
        myCustomButton:{
          text:"일정추가",
          click : function(){
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
        let title = prompt('일정 추가');
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
				error : function(result){
					alert(result);
		        }
			});
        }
        calendar.unselect()
      },
      eventClick: function (event) {    // 일정 클릭 시
	      console.log(event);
	      event.jsEvent.preventDefault(); // don't let the browser navigate
	      if(event.url == undefined) { // 구글 캘린더 데이터 제외해야 하는데 안먹음
			console.log(event.extendedProps.no);
		  }
      /*        
        fetch('/schInfo', {
  		  method: 'POST', 
		  headers: { 
		    "Content-Type": 'application/json',
		  },
		  body: JSON.stringify({
		    // id: 'emp101',
		    body: arg
		  }),
		})
  		.then((response) => response.json())
  		.then((data) => console.log(data))
  		*/
        /*
        if (confirm("선택한 일정을 삭제하시겠습니까?")) {                        
          arg.event.remove();
          alert("삭제하였습니다.");
        }else{
          alert("오류가 발생하였습니다");
        }
        */
      },
      //데이터 가져오는 이벤트        
      eventSources:[
        {
        events: allEvents,
        /*
        events: async function (info, successCallback, failureCallback) {              
          //이벤트에 넣을 배열 선언하기
          const eventArray = [];
          allEvents.forEach((res) => {
            eventArray.push({
              title: res.title,
              start: res.start,
              end: res.end,
              // backgroundColor: res.backgroundColor,
            });
          });
          successCallback(eventArray);
        },
        */
        },
        {
          googleCalendarId : 'ko.south_korea.official#holiday@group.v.calendar.google.com',
          backgroundColor: 'red',
        }
      ]
    });

    //모달창 이벤트
    $("#addSchedule").on("click", function () {
      var eventData = {
        title: $("#title").val(),
        start: $("#start").val(),
        end: $("#end").val(),
        content: $("#content").val(),
        color: $("#color").val(),
      };
      //빈값입력시 오류
      if (
        eventData.title == "" ||
        eventData.start == "" ||
        eventData.end == ""
      ) {
        alert("입력하지 않은 값이 있습니다.");

        //끝나는 날짜가 시작하는 날짜보다 값이 크면 안됨
      } else if ($("#start").val() > $("#end").val()) {
        alert("시간을 잘못 입력 하셨습니다.");
      } else {
		// 서버로 data 보내기
		saveSchedule();
        // 이벤트 추가
        calendar.addEvent(eventData);
        $("#addEventModal").modal("hide");
        $("#title").val("");
        $("#start").val("");
        $("#end").val("");
        $("#content").val("");
        $("#color").val("");
      }
      
    });
    // 캘린더 랜더링
    calendar.render();
  });
});

function saveSchedule() {
	// 서버로 data 보내기
	let obj = new Object();
	obj.title = $("#title").val();
	obj.start = $("#start").val();
    obj.end = $("#end").val();
    obj.content = $("#content").val();
    obj.color = $("#color").val();
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
		error : function(result){
			alert(result);
        }
	});
}  
/*
- 일정 저장시 고려해야 할 DB 테이블
1. title : 일정 이름
2. start : 일정 시작 시간
3. end : 일정이 끝나는 시간
4. backgroundColor : 일정 배경색

- 일정 저장시 공휴일 일정은 제외하기
내가 입력한 일정의 url값은 undefined이다
예제)
exports.calendar = async (req, res) => {
  await req.body.forEach((res) => {
    if(res.url) == undefined) {
      calendar.create({
        title: res.title,
        start: res.start,
        end: res.end,
        backgroundColor: res.backgroundColor,
      });
    }
  });
}
*/