$(document).ready(function() {
    $.ajax({
        url: '/student',
        method: 'GET',
        success: function (students) {
            students = students.data;
            $.each(students, function (key, value) {
                document.body.append("<p>" + value + "\n");
            });
        },
    });
})