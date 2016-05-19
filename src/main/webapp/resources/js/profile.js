$(document).ready(function () {
    var locale = $('#datepickerBirthday').attr("locale");

    $('#birthdayDatepicker').datepicker({
        language: locale,
        format: 'dd.mm.yyyy',
        orientation: 'bottom'
    });
});