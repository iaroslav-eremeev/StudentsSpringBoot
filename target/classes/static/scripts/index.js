$(document).ready(function() {

    $("#studentBtn").on("click", function() {
        window.location.href = "students.html";
    });

    $("#carBtn").on("click", function () {
        window.location.href = "cars.html";
    })

    /*$.ajax({
        url: '/student',
        method: 'GET',
        success: function (students) {
            students = students.data;
            var ul = $('<ul>');
            $.each(students, function (key, value) {
                var li = $('<li>').text(value.id + '. ' + value.name + ', ' +
                    value.age + ' years, number ' + value.num + ', salary ' + value.salary);
                ul.append(li);
            });
            $('body').append(ul);
        },
    });*/

})