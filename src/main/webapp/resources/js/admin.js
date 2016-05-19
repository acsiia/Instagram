$(document).ready(function () {
    $('input[id^=banBtn]').click(function () {
        var enabledBtnId = $(this)[0].id;
        var id = enabledBtnId.substring('banBtn'.length);
        banUser(id);
    });

    $('input[id^=askedPopup]').click(function () {
        var askedPopupShowBtnId = $(this)[0].id;
        var id = askedPopupShowBtnId.substring('askedPopup'.length);
        $('#popupDeleteBtn').attr("id-parameter", id);
    });

    $('#popupDeleteBtn').click(function () {
        var id = $(this).attr("id-parameter");
        deleteUser(id);
    });
});

function banUser(id) {
    var user = {};
    user["id"] = id;

    $.ajax({
        type: "GET",
        url: contextPath + "/admin/banUser",
        data: user,
        success: function (data) {
            if (data) {
                $('#banBtn' + id).attr("class",'btn btn-success');
                $('#banBtn' + id).attr("value", $('#banLabel').attr('ban'));
            } else {
                $('#banBtn' + id).attr("class",'btn btn-warning');
                $('#banBtn' + id).attr("value", $('#unbanLabel').attr('unban'));
            }
        }
    });
}

function deleteUser(id) {
    var user = {};
    user["id"] = id;

    $.ajax({
        type: "GET",
        url: contextPath + "/admin/deleteUser",
        data: user,

        success: function (data) {
            var user = $('#userInf' + id);
            user.remove();
        }
    });
}