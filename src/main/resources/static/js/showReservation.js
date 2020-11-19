function inputData() {
    var reservationShowDto = {
        id: $('#grdId').val(),
        date: $('#day').val()
    }
    $.ajax({
        url: "/ground/showReservation",
        data: reservationShowDto,
        type: "POST"
    }).done(function (fragment) {
        $("#timeWrap").replaceWith(fragment);
    });
}