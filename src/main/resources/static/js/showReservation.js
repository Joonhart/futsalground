function changeTimeInView() {
    var reservationShowDto = {
        id: $('#grdId').val(),
        date: $('#day').val()
    }
    $.ajax({
        url: "/ground/showReservation",
        data: reservationShowDto,
        type: "POST"
    }).done(function (result) {
        $("#timeWrap").replaceWith(result);
        open();
    });
}

function changeTimeInList(val) {
    var reservationShowDto = {
        id: val.name,
        date: val.value
    }
    $.ajax({
        url: "/ground/showReservationList",
        data: reservationShowDto,
        type: "POST"
    }).done(function (result) {
        var timeWrap = "timeWrap" + reservationShowDto.id;
        $('.' + timeWrap).replaceWith(result);
    });
}