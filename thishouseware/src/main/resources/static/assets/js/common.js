//=================================함수모음======================================
// 오늘 날짜를 YYYY-MM-DD 형식으로 반환하는 함수
function getTodayDate() {
    return new Date().toISOString().slice(0, 10);
}

function getTomorrowDate() {
    let today = new Date();
    today.setDate(today.getDate() + 1); // 오늘 날짜에 하루를 더함
    return today.toISOString().slice(0, 10);
}
//================================END 함수모음====================================