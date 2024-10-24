//임시저장 버튼.js
$(document).ready(function() {
    $('#edmsFormNo').on('change', function() {
        let selectedValue = $(this).val();
        
        if (selectedValue) {
            $('.btnSave').show(); // 양식이 선택되었으면 버튼을 보여줌
        } else {
            $('.btnSave').hide(); // 선택이 없으면 버튼을 숨김
        }

        let url = '/template/' + selectedValue + '.html';
        $('#edmsContent').load(url);
    });
});

